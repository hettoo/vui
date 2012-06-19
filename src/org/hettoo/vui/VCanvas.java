package org.hettoo.vui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VCanvas extends VAbstractComponent implements VDrawer {
    private Size virtualSize;
    private Size actualSize;

    public VCanvas(Size virtualSize) {
        super();
        this.virtualSize = virtualSize;
    }

    public void setActualSize(Size actualSize) {
        this.actualSize = actualSize;
    }

    public void drawRectangle(VRectangle rectangle) {
    }

    public void draw() {
    }
}
