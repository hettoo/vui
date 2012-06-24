package org.hettoo.vui;

public abstract class VAbstractComponent implements VComponent {
    private static final int STATUS_MARGIN = 8;
    protected static final int CONTENT_MARGIN = STATUS_MARGIN + 4;

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
        Size size = parent.getSize();
        parent.drawRectangle(new VRectangle(new Rectangle(new Size(0, 0),
                        new Size(STATUS_MARGIN, size.getHeight())),
                    getStatusColor()));
        parent.drawRectangle(new VRectangle(new Rectangle(
                        new Size(size.getWidth() - STATUS_MARGIN, 0),
                        new Size(STATUS_MARGIN, size.getHeight())),
                    getStatusColor()));
        parent.drawRectangle(new VRectangle(new Rectangle(new Size(0, 0),
                        new Size(size.getWidth(), STATUS_MARGIN)),
                    getStatusColor()));
        parent.drawRectangle(new VRectangle(new Rectangle(
                        new Size(0, size.getHeight() - STATUS_MARGIN),
                        new Size(size.getWidth(), STATUS_MARGIN)),
                    getStatusColor()));
    }

    public void keyPressed(KeyPress key) {
    }
}
