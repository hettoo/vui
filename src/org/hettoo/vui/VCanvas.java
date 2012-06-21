package org.hettoo.vui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VCanvas extends VAbstractComponent implements VDrawer {
    private Size size;

    public VCanvas() {
        super();
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void drawRectangle(VRectangle rectangle) {
    }

    public void draw() {
    }

    public Size getSize() {
        return size;
    }
}
