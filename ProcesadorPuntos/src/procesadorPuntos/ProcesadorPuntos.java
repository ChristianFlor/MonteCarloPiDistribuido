import servicios.*;

import java.util.Random;

public class ProcesadorPuntos implements Runnable, ServicioIniciar {

    @Reference
    private ServicioAsignarPuntos servicioAsignarPuntos;

    @Reference
    private ServicioRespuesta servicioRespuesta;

    @Reference
    private ServicioRestarNodo servicioRestarNodo;

    @Reference
    private ServicioSumarNodo servicioSumarNodo;

    long[] parametros;

    public void run() {
        System.out.println("Procesador Running");
        try {
            servicioSumarNodo.sumarNodo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularPuntosAdentro() {
        while (parametros[0] > 0) {
            while (parametros[0] > 0) {
                long puntosTotal = parametros[0];
                long puntosAdentro = 0;
                int seed = (int) parametros[1];
                Random rng = new Random(seed);

                for (long i = 0; i < puntosTotal; i++) {
                    float currentX = rng.nextFloat();
                    float currentY = rng.nextFloat();
                    float position = (currentX * currentX) + (currentY * currentY);
                    if (position <= 1) {
                        puntosAdentro += 1;
                    }
                }
                servicioRespuesta.respuesta(puntosAdentro);
                parametros = servicioAsignarPuntos.asignarPuntos();
            }
            parametros = servicioAsignarPuntos.asignarPuntos();
//        if (parametros[0] > 0 ) {
//            calcularPuntosAdentro();
//        }
        }
    }

    @Override
    public void iniciar() {
        parametros = servicioAsignarPuntos.asignarPuntos();
        calcularPuntosAdentro();
        servicioRestarNodo.restarNodo();
    }
}
