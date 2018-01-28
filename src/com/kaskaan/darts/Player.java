package com.kaskaan.darts;

public class Player {
    private final String name;
    private int points;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
