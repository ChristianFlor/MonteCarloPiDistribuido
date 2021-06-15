package modelo;

import java.io.Serializable;

public class Output implements Serializable {

    private String id;
    private double valorPi, time, timeAverage;
    private int nodes;

    public Output(String id, double valorPi, double time, double timeAverage, int nodes) {
        super();
        this.id = id;
        this.valorPi = valorPi;
        this.time = time;
        this.timeAverage = timeAverage;
        this.nodes = nodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValorPi() {
        return valorPi;
    }

    public void setValorPi(double valorPi) {
        this.valorPi = valorPi;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTimeAverage() {
        return timeAverage;
    }

    public void setTimeAverage(double timeAverage) {
        this.timeAverage = timeAverage;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

}
