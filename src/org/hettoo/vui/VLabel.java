package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VLabel extends VAbstractComponent {
    private String label;

    private boolean drawing;

    public VLabel() {
        this.label = "";
        drawing = false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
        draw();
    }

    @Override
    public void draw() {
        if (parent == null || drawing)
            return;
        drawing = true;
        parent.redraw();
        super.draw();
        parent.getTheme().drawLabel(this);
        drawing = false;
    }
}
