package org.hettoo.vui;

public class VCanvas extends VAbstractComponent implements VDrawer {
    private Vector size;

    public VCanvas() {
        super();
    }

    public Vector getSize() {
        return size;
    }

    public void setSize(Vector size) {
        this.size = size;
    }

    public VTheme getTheme() {
        return parent.getTheme();
    }

    public int getTextWidth(String text, FontStyle fontStyle) {
        return parent.getTextWidth(text, fontStyle);
    }

    public int getTextHeight(FontStyle fontStyle) {
        return parent.getTextHeight(fontStyle);
    }

    public void drawRectangle(VRectangle rectangle) {
        parent.drawRectangle(rectangle);
    }

    public void drawText(String text, Vector position, FontStyle fontStyle) {
        parent.drawText(text, position, fontStyle);
    }
}
