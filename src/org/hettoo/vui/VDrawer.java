package org.hettoo.vui;

public interface VDrawer extends VComponent {
    public Size getSize();
    public VTheme getTheme();

    public int getTextWidth(String text, FontStyle fontStyle);
    public int getTextHeight(FontStyle fontStyle);

    public void drawRectangle(VRectangle rectangle);
    public void drawText(String text, Size position, FontStyle fontStyle);

    public void show();
}
