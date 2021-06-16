package procesadorPuntos;

import servicios.*;
import org.osoa.sca.annotations.*;
import java.util.Random;

public class ProcesadorPuntos implements Runnable {

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
            parametros = servicioAsignarPuntos.asignarPuntos();
            while (parametros[0] != -1) {
                calcularPuntosAdentro();
                parametros = servicioAsignarPuntos.asignarPuntos();
                System.out.println("Trabajo terminado");
            }
            System.out.println("Terminado!");
            servicioRestarNodo.restarNodo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calcularPuntosAdentro() {
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
    }
}
