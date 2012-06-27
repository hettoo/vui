package org.hettoo.vui;

public abstract class VAbstractComponent implements VComponent {
    protected VDrawer parent;
    protected VStatus status;

    public VAbstractComponent() {
    }

    public VDrawer getParent() {
        return parent;
    }

    public void setParent(VDrawer parent) {
        this.parent = parent;
    }

    public void activate() {
        setStatus(VStatus.ACTIVE);
    }

    public void disactivate() {
        setStatus(VStatus.INACTIVE);
    }

    protected void setStatus(VStatus status) {
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
        parent.getTheme().drawStatus(this);
    }

    public void keyPressed(KeyPress key) {
    }
}
