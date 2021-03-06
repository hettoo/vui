package org.hettoo.vui;

public abstract class VAbstractComponent implements VComponent {
    protected VDrawer parent;
    protected VStatus status;
    protected boolean isRoot;

    public VAbstractComponent() {
        isRoot = false;
    }

    public void setRoot() {
        isRoot = true;
    }

    public VDrawer getParent() {
        return parent;
    }

    public void setParent(VDrawer parent) {
        this.parent = parent;
    }

    public void setStatus(VStatus status) {
        this.status = status;
        draw();
    }

    public VStatus getStatus() {
        return status;
    }

    protected Color getStatusColor() {
        return parent.getTheme().getStatusColor(getStatus());
    }

    public void draw() {
        if (!isRoot)
            parent.getTheme().drawStatus(this);
    }

    public boolean keyPressed(KeyPress key) {
        boolean handled = false;
        if (key.isEscaper() && !isRoot) {
            setStatus(VStatus.ACTIVE);
            handled = true;
        }
        return handled;
    }
}
