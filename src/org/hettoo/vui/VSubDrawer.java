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

    public void drawPolygon(VPolygon polygon) {
        Vector[] points = polygon.getPolygon().getPoints();
        for (int i = 0; i < points.length; i++)
            points[i] = points[i].add(drawer.getSize().limit(grid,
                        limit.getOffset()));
        polygon.setPolygon(new Polygon(points));
        drawer.drawPolygon(polygon);
    }

    public void drawText(String text, Vector position, FontStyle fontStyle) {
        position = position.add(drawer.getSize().limit(grid,
                    limit.getOffset()));
        drawer.drawText(text, position, fontStyle);
    }

    public void redraw() {
        Vector size = getSize();
        // FIXME: this depends on the drawer drawing only the root color.
        drawPolygon(new VPolygon(new Rectangle(new Vector(0, 0),
                        new Vector(size.getX(), size.getY())),
                    getTheme().getRootColor()));
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
