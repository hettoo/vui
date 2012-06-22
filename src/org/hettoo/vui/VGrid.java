package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VGrid extends VAbstractComponent {
    private Size size;
    private List<VComponent> components;

    public VGrid(Size size) {
        super();
        this.size = size;
        components = new ArrayList<VComponent>();
    }

    public void addComponent(VComponent component, Rectangle area) {
        component.setParent(new VSubDrawer(parent, size, area));
        components.add(component);
        if (components.size() == 1)
            component.activate();
        else
            component.disactivate();
    }

    public void draw() {
        for (VComponent component : components)
            component.draw();
    }
}
