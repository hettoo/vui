package org.hettoo.vui;

public class VPolygon {
    private Polygon polygon;
    private Color color;
    private boolean fill;

    public VPolygon(Polygon polygon, Color color, boolean fill) {
        setPolygon(polygon);
        this.color = color;
        this.fill = fill;
    }

    public VPolygon(Polygon polygon, Color color) {
        this(polygon, color, true);
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Color getColor() {
        return color;
    }

    public boolean mustFill() {
        return fill;
    }
}
