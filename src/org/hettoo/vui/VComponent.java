package org.hettoo.vui;

public interface VComponent {
    public VDrawer getParent();
    public void setParent(VDrawer parent);

    public void activate();
    public void disactivate();
    public VStatus getStatus();

    public void draw();

    public void keyPressed(KeyPress key);
}
