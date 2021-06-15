import servicios.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class ProcesadorPuntos implements Runnable{

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
        System.out.println("Run");
        try {
            parametros = servicioAsignarPuntos.asignarPuntos();
            while (true) {
                if (parametros[0] != 0) {
                    servicioSumarNodo.sumarNodo();
                    calcularPuntosAdentro();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularPuntosAdentro() {

        if (parametros[0] == 0) {
            servicioRestarNodo.restarNodo();
        }else{
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



}
