package org.hettoo.vui;

public class VSubCanvas implements VDrawable {
    private VCanvas canvas;
    private Rectangle limit;

    public VSubCanvas(VCanvas canvas) {
        this.canvas = canvas;
    }

    public VSubCanvas(VCanvas canvas, Rectangle limit) {
        this(canvas);
        this.limit = limit;
    }

    public void drawRectangle(VRectangle rectangle) {
        canvas.drawRectangle(rectangle);
    }

    public void draw(VDrawable parent) {
        canvas.draw(parent);
    }
}
