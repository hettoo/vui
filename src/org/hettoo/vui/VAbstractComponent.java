package org.hettoo.vui;

public abstract class VAbstractComponent implements VComponent {
    private static final int STATUS_MARGIN = 5;
    protected static final int CONTENT_MARGIN = STATUS_MARGIN + 2;

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
        drawStatus();
    }

    protected void drawStatus() {
        Vector size = parent.getSize();
        parent.drawRectangle(new VRectangle(new Rectangle(new Vector(0, 0),
                        new Vector(STATUS_MARGIN, size.getY())),
                    getStatusColor()));
        parent.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(size.getX() - STATUS_MARGIN, 0),
                        new Vector(STATUS_MARGIN, size.getY())),
                    getStatusColor()));
        parent.drawRectangle(new VRectangle(new Rectangle(new Vector(0, 0),
                        new Vector(size.getX(), STATUS_MARGIN)),
                    getStatusColor()));
        parent.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(0, size.getY() - STATUS_MARGIN),
                        new Vector(size.getX(), STATUS_MARGIN)),
                    getStatusColor()));
    }

    public void keyPressed(KeyPress key) {
    }
}
