package org.hettoo.vui;

public interface VDrawer extends VComponent {
    public Vector getSize();
    public VTheme getTheme();

    public int getTextWidth(String text, FontStyle fontStyle);
    public int getTextHeight(FontStyle fontStyle);

    public void drawPolygon(VPolygon polygon);
    public void drawText(String text, Vector position, FontStyle fontStyle);

    public void redraw();
}
