import java.util.concurrent.Semaphore;

public class ProxySincro {

    private static Semaphore sem = new Semaphore(1);

    public void asignarPuntos(){
        try{
            sem.acquire();
            //TODO: llama al metodo assignPoints que esta en MonteCarlo.
            sem.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sumarNodo(){
        //TODO:
    }

    public void restarNodo(){

    }

    public void respuesta(){

    }


}
