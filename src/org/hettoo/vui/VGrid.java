package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VGrid extends VAbstractComponent {
    private int cols;
    private int rows;
    private List<VComponentPositioner> components;

    public VGrid(int cols, int rows) {
        super();
        this.cols = cols;
        this.rows = rows;
        components = new ArrayList<VComponentPositioner>();
    }

    public void addComponent(VComponentPositioner component) {
        components.add(component);
    }

    @Override
    public void setParent(VDrawer parent) {
        super.setParent(parent);
        for (VComponentPositioner component : components)
            component.setParent(parent);
    }

    public void draw() {
        for (VComponentPositioner component : components)
            component.draw();
    }
}
