package modelo;

import java.io.Serializable;

public class Test implements Serializable {

    private final int seed;
    private final long points;
    private final long batchSize;

    private long remainingPoints;
    private long pointsInside = 0;
    private int connectedNodes = 0;

    private long timeBeforeTest;

    private final int id;

    public Test(int id, int seed, long points, long batchSize) {
        super();
        this.id = id;
        this.seed = seed;
        this.points = points;
        this.remainingPoints = points;
        this.batchSize = batchSize;
    }

    public int getId() {
        return id;
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

    public void addPointsInside(long pointsInside) {
        this.pointsInside += pointsInside;
    }

    public int getConnectedNodes() {
        return connectedNodes;
    }

    public void addConnectedNodes() {
        connectedNodes++;
//        if (connectedNodes > mostConnectedNodes)
//            mostConnectedNodes = connectedNodes;
    }

//    public int getMostConnectedNodes() {
//        return mostConnectedNodes;
//    }

    public void reduceConnectedNodes() {
        connectedNodes--;
    }

    public void setTimeBeforeTest(long timeBeforeTest) {
        this.timeBeforeTest = timeBeforeTest;
    }

    public long execTime(long timeAfterTest) {
        return timeAfterTest - timeBeforeTest;
    }
}
