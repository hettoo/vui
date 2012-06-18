package org.hettoo.vui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class VCanvas implements VDrawable {
    private Rectangle virtualRectangle;
    private Rectangle virtualLimit;
    private Rectangle actualRectangle;

    public VCanvas(Rectangle virtualRectangle) {
        this.virtualRectangle = virtualRectangle;
    }

    public void setActualRectangle(Rectangle actualRectangle) {
        this.actualRectangle = actualRectangle;
    }

    public void draw(VSubCanvas parent) {
    }
}
