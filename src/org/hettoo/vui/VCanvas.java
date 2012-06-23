package org.hettoo.vui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VCanvas extends VAbstractComponent implements VDrawer {
    private Size size;

    public VCanvas() {
        super();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public VTheme getTheme() {
        return parent.getTheme();
    }

    public int getTextWidth(String text, int size, FontStyle fontStyle) {
        return parent.getTextWidth(text, size, fontStyle);
    }

    public int getTextHeight(int size, FontStyle fontStyle) {
        return parent.getTextHeight(size, fontStyle);
    }

    public void drawRectangle(VRectangle rectangle) {
        parent.drawRectangle(rectangle);
    }

    public void drawText(String text, int size, Size position,
            FontStyle fontStyle) {
    }

    public void show() {
        parent.show();
    }
}
