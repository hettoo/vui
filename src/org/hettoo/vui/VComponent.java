package org.hettoo.vui;

public interface VComponent {
    public void setRoot();
    public VDrawer getParent();
    public void setParent(VDrawer parent);

    public VStatus getStatus();
    public void setStatus(VStatus status);

    public void draw();

    public boolean keyPressed(KeyPress key);
}
