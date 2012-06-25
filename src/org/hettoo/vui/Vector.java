package org.hettoo.vui;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector limit(Vector grid, Vector limit) {
        return new Vector(x * limit.getX() / grid.getX(),
                y * limit.getY() / grid.getY());
    }
}
