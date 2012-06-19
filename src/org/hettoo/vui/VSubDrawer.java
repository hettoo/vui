package org.hettoo.vui;

public class VSubDrawer implements VDrawer {
    private VDrawer drawer;
    private Rectangle limit;

    public VSubDrawer(VDrawer drawer, Rectangle limit) {
        this.drawer = drawer;
        this.limit = limit;
    }

    public VSubDrawer(VDrawer drawer) {
        this(drawer, null);
    }

    public void drawRectangle(VRectangle rectangle) {
        drawer.drawRectangle(rectangle);
    }

    public void draw() {
        drawer.draw();
    }

    public VDrawer getParent() {
        return drawer.getParent();
    }

    public void setParent(VDrawer parent) {
        drawer.setParent(parent);
    }
}
