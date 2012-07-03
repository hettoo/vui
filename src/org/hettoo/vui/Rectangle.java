package org.hettoo.vui;

public class Rectangle extends Polygon {
    private Vector offset;
    private Vector size;

    public Rectangle(Vector offset, Vector size) {
        super(createVertices(offset, size));
        this.offset = offset;
        this.size = size;
    }

    private static Vector[] createVertices(Vector offset, Vector size) {
        return new Vector[] {offset, new Vector(offset.getX() + size.getX(),
                offset.getY()), offset.add(size), new Vector(offset.getX(),
                offset.getY() + size.getY())};
    }

    public Vector getOffset() {
        return offset;
    }

    public Vector getSize() {
        return size;
    }
}
