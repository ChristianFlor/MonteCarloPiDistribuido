package repositorios;

import modelo.Output;

public class OutputRepositorio extends Repositorio<String,Output> {
    
    private static OutputRepositorio instance;

    public static OutputRepositorio getInstance(){
        if(instance==null){
            instance=new OutputRepositorio();
        }
        return instance;
    }
    
    private OutputRepositorio(){
        super("output.bd");
    }
    public  void loadDataP(){

    }
}
