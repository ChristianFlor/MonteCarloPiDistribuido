package modelo;

import java.io.Serializable;

public class Test implements Serializable {

    private final int id, seed;
    private final long points, batchSize;

    private long remainingPoints,pointsInside, timeBeforeTest;

    private int iterations;

    public Test(int id, int seed, long points, long batchSize) {
        super();
        this.id = id;
        this.seed = seed;
        this.points = points;
        this.remainingPoints = points;
        this.batchSize = batchSize;
        pointsInside = 0;
        iterations = 0;
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

    public void setRemainingPoints(long remainingPoints) {
        this.remainingPoints = remainingPoints;
    }

    public long getPointsInside() {
        return pointsInside;
    }

    public void addPointsInside(long pointsInside) {
        this.pointsInside += pointsInside;
        remainingPoints -= batchSize;
    }

    public void setTimeBeforeTest(long timeBeforeTest) {
        this.timeBeforeTest = timeBeforeTest;
    }

    public long execTime(long timeAfterTest) {
        return timeAfterTest - timeBeforeTest;
    }

    public int getIterations() {
        return iterations;
    }

    public void addIterations() {
        iterations++;
    }
}
