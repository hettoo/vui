package org.hettoo.vui;

public abstract class VAbstractComponent implements VComponent {
    protected VDrawer parent;

    public VAbstractComponent() {
    }

    public VDrawer getParent() {
        return parent;
    }

    public void setParent(VDrawer parent) {
        this.parent = parent;
    }
}
