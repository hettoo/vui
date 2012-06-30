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

    public void redraw() {
        Vector size = getSize();
        drawRectangle(new VRectangle(new Rectangle(new Vector(0, 0),
                        new Vector(size.getX(), size.getY())),
                    getTheme().getRootColor()));
        //drawer.redraw();
    }

    public void draw() {
        drawer.draw();
    }

    public void setStatus(VStatus status) {
        drawer.setStatus(status);
    }

    public VTheme getTheme() {
        return drawer.getTheme();
    }

    public VStatus getStatus() {
        return drawer.getStatus();
    }

    public void setRoot() {
        drawer.setRoot();
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

    public boolean keyPressed(KeyPress key) {
        return drawer.keyPressed(key);
    }
}
