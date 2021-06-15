package modelo;

public class Resultado {

    private double totalPi = 0;
    private int ejecuciones = 0;

    private double totalTiempo = 0;

    private String output = "";

    public Resultado(double totalPi) {
        this.totalPi = totalPi;
    }

    public void agregarIteracion() {

    }

    public double promedioPi() {
        return totalPi / ejecuciones;
    }

    public double promedioTiempo() {
        return totalTiempo / ejecuciones;
    }

    public void agregarTotalPi(double totalPi) {
        this.totalPi += totalPi;
    }

    public void agregarEjecucion() {
        this.ejecuciones++;
    }

    public void agregarTotalTiempo(double totalTiempo) {
        this.totalTiempo += totalTiempo;
    }

    public void idk() {
        String encabezado = "Valor Pi" + COMA + "Tiempo de Ejecucion\n";
        String output = "";
        for (int i = 0; i < tests.size(); i++) {
            int filasPorTest = 15;
            int filaComienzoTestActual = filasPorTest * i + 1;
            output += "Test " + (i + 1) + "\n" + encabezado;
            long totalPuntos = tests.get(i).getPoints();
            int seed = tests.get(i).getSeed();
            frame.getTabla().setValueAt(("Test: " + (i + 1) + "-").split("-"), filaComienzoTestActual, 0);
            frame.getTabla().setValueAt(("Semilla: " + seed + "-Num Puntos: " + totalPuntos).split("-"), filaComienzoTestActual + 1, 0);
            long average = 0;
            for (int j = 0; j < 10; j++) {
                long timeNow = System.currentTimeMillis();
                double pi = calcularPi(seed, totalPuntos);
                long timeAfter = System.currentTimeMillis();
                long totalTime = timeAfter - timeNow;

                average += totalTime;
                output += pi + COMA + totalTime + "\n";
                frame.getTabla().setValueAt(("Pi: " + pi + "-Tiempo Ejec (ms): " + totalTime).split("-"), filaComienzoTestActual + 2 + j, 0);
            }
            average = average / 10;
            output += "Promedio" + COMA + average + "\n";
            output += "Nodos" + COMA + "1\n\n";
            frame.getTabla().setValueAt(("Tiempo promedio (ms): " + average + "-Nodos: 1").split("-"), filaComienzoTestActual + 12, 0);
        }
        escribirResultados(output);
    }
}