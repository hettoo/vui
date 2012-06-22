package org.hettoo.vui;

public interface VDrawer extends VComponent {
    public Size getSize();
    public VTheme getTheme();

    public void drawRectangle(VRectangle rectangle);
}
