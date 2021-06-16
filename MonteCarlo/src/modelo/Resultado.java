package modelo;

public class Resultado {

    private final static String COMA = ",";

    private final int ejecuciones;
    private final int nodos;

    private double totalPi = 0;
    private double totalTiempo = 0;

    private String output = "";

    public Resultado(int id, int seed, long puntos, int ejecuciones, int nodos) {
        this.ejecuciones = ejecuciones;
        this.nodos = nodos;

        output += "Test: " + id + "\n";
        output += "Seed: " + seed + COMA + "Puntos: " + puntos + "\n";
        output += "Valor Pi" + COMA + "Tiempo de Ejecucion\n";
    }

    public void agregarIteracion(double valorPi, double tiempoEjec) {
        totalPi += valorPi;
        totalTiempo += tiempoEjec;
        output += valorPi + COMA + tiempoEjec + "\n";
    }

    public String terminarTest() {
        double promedioPi = totalPi / ejecuciones;
        double promedioTiempo = totalTiempo / ejecuciones;
        output += "Promedio Pi" + COMA + promedioPi + "\n";
        output += "Promedio Tiempo" + COMA + promedioTiempo + "ms\n";
        output += "Nodos" + COMA + nodos + "\n\n";
        return output;
    }
}