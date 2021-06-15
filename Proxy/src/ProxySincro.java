import servicios.*;

import java.util.concurrent.Semaphore;

public class ProxySincro implements ServicioAsignarPuntos,ServicioRespuesta,ServicioRestarNodo,ServicioSumarNodo{

    private static final Semaphore sem = new Semaphore(1);

    @Reference
    private ServicioAsignarPuntos servicioAsignarPuntos;

    @Reference
    private ServicioRespuesta servicioRespuesta;

    @Reference
    private ServicioRestarNodo servicioRestarNodo;

    @Reference
    private ServicioSumarNodo servicioSumarNodo;

    @Override
    public long asignarPuntos(){
        long puntos = 0;
        try{
            sem.acquire();
            puntos = servicioAsignarPuntos.asignarPuntos();
            sem.release();
        }catch(Exception e){
            e.printStackTrace();
        }
        return puntos;
    }

    @Override
    public void sumarNodo(){
        try{
            sem.acquire();
            servicioSumarNodo.sumarNodo();
            sem.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void restarNodo(){
        try{
            sem.acquire();
            servicioRestarNodo.restarNodo();
            sem.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void respuesta(long puntosAdentro){
        try{
            sem.acquire();
            servicioRespuesta.respuesta(puntosAdentro);
            sem.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
