package org.hettoo.vui;

public class VSubDrawer implements VDrawer {
    private VDrawer drawer;
    private Vector grid;
    private Rectangle limit;

    public VSubDrawer(VDrawer drawer, Vector grid, Rectangle limit) {
        this.drawer = drawer;
        this.grid = grid;
        this.limit = limit;
    }

    public int getTextWidth(String text, FontStyle fontStyle) {
        return drawer.getTextWidth(text, fontStyle);
    }

    public int getTextHeight(FontStyle fontStyle) {
        return drawer.getTextHeight(fontStyle);
    }

    public void drawRectangle(VRectangle rectangle) {
        Rectangle rect = rectangle.getRectangle();
        Vector offset = rect.getOffset();
        offset = offset.add(drawer.getSize().limit(grid, limit.getOffset()));
        rectangle.setRectangle(new Rectangle(offset, rect.getSize()));
        drawer.drawRectangle(rectangle);
    }

    public void drawText(String text, Vector position, FontStyle fontStyle) {
        position = position.add(drawer.getSize().limit(grid,
                    limit.getOffset()));
        drawer.drawText(text, position, fontStyle);
    }

    public void draw() {
        drawer.draw();
    }

    public void activate() {
        drawer.activate();
    }

    public void disactivate() {
        drawer.disactivate();
    }

    public VTheme getTheme() {
        return drawer.getTheme();
    }

    public VStatus getStatus() {
        return drawer.getStatus();
    }

    public VDrawer getParent() {
        return drawer.getParent();
    }

    public void setParent(VDrawer parent) {
        drawer.setParent(parent);
    }

    public Vector getSize() {
        return drawer.getSize().limit(grid, limit.getSize());
    }

    public void keyPressed(KeyPress key) {
        drawer.keyPressed(key);
    }
}
