package org.hettoo.vui;

public interface VDrawer extends VComponent {
    public Size getSize();
    public VTheme getTheme();

    public int getTextWidth(String text, int size, FontStyle fontStyle);
    public int getTextHeight(int size, FontStyle fontStyle);

    public void drawRectangle(VRectangle rectangle);
    public void drawText(String text, int size, Size position,
            FontStyle fontStyle);
}
