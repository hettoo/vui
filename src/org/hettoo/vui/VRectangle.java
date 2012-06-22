package org.hettoo.vui;

public class VRectangle {
    private Rectangle rectangle;
    private Color color;

    public VRectangle(Rectangle rectangle, Color color) {
        setRectangle(rectangle);
        this.color = color;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Color getColor() {
        return color;
    }
}
