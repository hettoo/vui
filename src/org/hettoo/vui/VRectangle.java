package org.hettoo.vui;

public class VRectangle {
    private Rectangle rectangle;
    private Color color;

    public VRectangle(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Color getColor() {
        return color;
    }
}
