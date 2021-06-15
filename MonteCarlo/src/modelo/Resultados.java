package modelo;

public class Resultados{

    private double totalPi = 0;
    private int ejecuciones = 0;

    private double totalTiempo = 0;

    public double promedioPi(){
        return totalPi/ejecuciones;
    }

}