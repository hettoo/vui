package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VGrid extends VAbstractComponent {
    private Size size;
    private VLimitedComponent active;
    private List<VLimitedComponent> components;

    public VGrid(Size size) {
        super();
        this.size = size;
        components = new ArrayList<VLimitedComponent>();
    }

    public void addComponent(VLimitedComponent component) {
        component.getComponent().setParent(new VSubDrawer(parent, size,
                    component.getLimit()));
        components.add(component);
        if (components.size() == 1) {
            setFocus(component);
        } else {
            component.getComponent().disactivate();
        }
    }

    private void setFocus(VLimitedComponent component) {
        if (active != null)
            active.getComponent().disactivate();
        component.getComponent().activate();
        active = component;
    }

    public void draw() {
        for (VLimitedComponent component : components)
            component.getComponent().draw();
    }

    private Integer next(int offset, int size, int otherOffset, int otherSize,
            int offset2, int otherOffset2, int otherSize2) {
        if (((otherOffset >= offset && otherOffset < offset + size)
                    || (offset >= otherOffset && offset < otherOffset + otherSize))
                && otherOffset2 + otherSize2 <= offset2) {
            return offset2 - (otherOffset2 + otherSize2);
                }
        return null;
    }

    @Override
    public void keyPressed(KeyPress key) {
        super.keyPressed(key);
        if (active == null)
            return;
        Key k = key.getKey();
        VLimitedComponent next = null;
        Rectangle current = active.getLimit();
        Size offset = current.getOffset();
        Size size = current.getSize();
        int closest = 0;
        for (VLimitedComponent other : components) {
            if (other != active) {
                Rectangle otherLimit = other.getLimit();
                Size otherOffset = otherLimit.getOffset();
                Size otherSize = otherLimit.getSize();
                Integer distance = null;
                switch (k) {
                    case VK_H:
                        distance = next(offset.getHeight(),
                                size.getHeight(),
                                otherOffset.getHeight(),
                                otherSize.getHeight(),
                                offset.getWidth(),
                                otherOffset.getWidth(),
                                otherSize.getWidth());
                        break;
                    case VK_J:
                        distance = next(otherOffset.getWidth(),
                                otherSize.getWidth(),
                                offset.getWidth(),
                                size.getWidth(),
                                otherOffset.getHeight(),
                                offset.getHeight(),
                                size.getHeight());
                        break;
                    case VK_K:
                        distance = next(offset.getWidth(),
                                size.getWidth(),
                                otherOffset.getWidth(),
                                otherSize.getWidth(),
                                offset.getHeight(),
                                otherOffset.getHeight(),
                                otherSize.getHeight());
                        break;
                    case VK_L:
                        distance = next(otherOffset.getHeight(),
                                otherSize.getHeight(),
                                offset.getHeight(),
                                size.getHeight(),
                                otherOffset.getWidth(),
                                offset.getWidth(),
                                size.getWidth());
                        break;
                }
                if (distance != null && (next == null || distance < closest)) {
                    next = other;
                    closest = distance;
                }
            }
        }
        if (next != null) {
            active.getComponent().draw();
            setFocus(next);
            next.getComponent().draw();
            parent.show();
            return;
        }
        active.getComponent().keyPressed(key);
    }

    @Override
    public void keyTyped(KeyPress key) {
        super.keyTyped(key);
        if (active == null)
            return;
        active.getComponent().keyTyped(key);
    }
}
