package repositorios;

import modelo.Test;

public class TestRepositorio extends Repositorio<String,Test> {
    
    private static TestRepositorio instance;

    public static TestRepositorio getInstance(){
        if(instance==null){
            instance=new TestRepositorio();
        }
        return instance;
    }
    
    private TestRepositorio(){
        super("test.bd");
    }
    public  void loadDataP(){

    }
}
