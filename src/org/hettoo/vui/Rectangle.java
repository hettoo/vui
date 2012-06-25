package org.hettoo.vui;

public class Rectangle {
    private Vector offset;
    private Vector size;

    public Rectangle(Vector offset, Vector size) {
        this.offset = offset;
        this.size = size;
    }

    public Vector getOffset() {
        return offset;
    }

    public Vector getSize() {
        return size;
    }
}
