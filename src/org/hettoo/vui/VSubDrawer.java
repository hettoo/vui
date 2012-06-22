package org.hettoo.vui;

public class VSubDrawer implements VDrawer {
    private VDrawer drawer;
    private Size grid;
    private Rectangle limit;

    public VSubDrawer(VDrawer drawer, Size grid, Rectangle limit) {
        this.drawer = drawer;
        this.grid = grid;
        this.limit = limit;
    }

    public int getTextWidth(String text, int size, FontStyle fontStyle) {
        return drawer.getTextWidth(text, size, fontStyle);
    }

    public int getTextHeight(int size, FontStyle fontStyle) {
        return drawer.getTextHeight(size, fontStyle);
    }

    public void drawRectangle(VRectangle rectangle) {
        Rectangle rect = rectangle.getRectangle();
        Size offset = rect.getOffset();
        offset.add(drawer.getSize().limit(grid, limit.getOffset()));
        rectangle.setRectangle(new Rectangle(offset, rect.getSize()));
        drawer.drawRectangle(rectangle);
    }

    public void drawText(String text, int size, Size position,
            FontStyle fontStyle) {
        position.add(drawer.getSize().limit(grid, limit.getOffset()));
        drawer.drawText(text, size, position, fontStyle);
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

    public Size getSize() {
        return drawer.getSize().limit(grid, limit.getSize());
    }
}
