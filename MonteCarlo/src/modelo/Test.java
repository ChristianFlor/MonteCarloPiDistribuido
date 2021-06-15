package modelo;

import java.io.Serializable;

public class Test implements Serializable {

    private int seed;
    private long points;

    private long remainingPoints;
    private long pointsInside = 0;
    private long batchSize;

    private int connectedNodes = 0;

    public Test(int seed, long points, long batchSize) {
        super();
        this.seed = seed;
        this.points = points;
        this.remainingPoints = points;
        this.batchSize = batchSize;
    }

    public int getSeed() {
        return seed;
    }

    public long getPoints() {
        return points;
    }

    public long getRemainingPoints() {
        return remainingPoints;
    }

    public void reduceRemainingPoints() {
        this.remainingPoints -= batchSize;
    }

    public long getPointsInside() {
        return pointsInside;
    }

    public void setPointsInside(long pointsInside) {
        this.pointsInside = pointsInside;
    }

    public int getConnectedNodes() {
        return connectedNodes;
    }

    public void addConnectedNodes() {
        connectedNodes++;
    }

    public void reduceConnectedNodes() {
        connectedNodes--;
    }
}
