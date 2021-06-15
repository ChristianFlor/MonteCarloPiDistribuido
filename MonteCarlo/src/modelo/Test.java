package modelo;

import java.io.Serializable;

public class Test implements Serializable {

    private String id;
    private int seed;
    private long points;


    public Test(String id, int seed, long points) {
        super();
        this.id = id;
        this.seed = seed;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

}
