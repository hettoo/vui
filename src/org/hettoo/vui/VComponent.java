package org.hettoo.vui;

public interface VComponent {
    public VDrawer getParent();
    public void setParent(VDrawer parent);

    public void draw();
}
