package modelo;

import java.util.Random;

public class CalculadorPi {

    private final int semilla;
    private final long puntosTotal;

    public CalculadorPi(int semilla, long puntosTotal) {
        super();
        this.semilla = semilla;
        this.puntosTotal = puntosTotal;
    }

    public long calcularPuntosAdentro() {
        long puntosAdentro = 0;
        Random rng = new Random(semilla);
        for (long i = 0; i < puntosTotal; i++) {
            float currentX = rng.nextFloat();
            float currentY = rng.nextFloat();
            float position = (currentX * currentX) + (currentY * currentY);
            if (position <= 1) {
                puntosAdentro += 1;
            }
        }
        return puntosAdentro;
    }
}
