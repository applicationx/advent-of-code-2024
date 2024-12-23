package com.appx.model;


public enum Direction {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

    private final int dx;
    private final int dy;
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public int dx() { return dx; }
    public int dy() { return dy; }

    public Direction turnRight() {
        return switch(this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }
}
