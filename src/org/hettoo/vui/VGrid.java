package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VGrid extends VAbstractComponent {
    private Size size;
    private List<VComponentPositioner> components;

    public VGrid(Size size) {
        super();
        this.size = size;
        components = new ArrayList<VComponentPositioner>();
    }

    public void addComponent(VComponentPositioner component, Rectangle area) {
        component.setParent(new VSubDrawer(parent, size, area));
        components.add(component);
    }

    public void draw() {
        for (VComponentPositioner component : components)
            component.draw();
    }
}
