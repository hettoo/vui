package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VGrid extends VAbstractComponent {
    private Size size;
    private VLimitedComponent active;
    private Size activeSquare;
    private List<VLimitedComponent> components;

    public VGrid(Size size) {
        super();
        this.size = size;
        components = new ArrayList<VLimitedComponent>();
    }

    public void addComponent(VLimitedComponent component, boolean activate) {
        Rectangle limit = component.getLimit();
        component.getComponent().setParent(new VSubDrawer(parent, size, limit));
        components.add(component);
        if (activate || components.size() == 1) {
            setFocus(component);
            activeSquare = limit.getOffset();
        } else {
            component.getComponent().disactivate();
        }
    }

    public void addComponent(VLimitedComponent component) {
        addComponent(component, false);
    }

    private void setFocus(VLimitedComponent component) {
        if (active != null) {
            active.getComponent().disactivate();
            active.getComponent().draw();
        }
        component.getComponent().activate();
        active = component;
        active.getComponent().draw();
    }

    public void draw() {
        for (VLimitedComponent component : components)
            component.getComponent().draw();
    }

    private Integer nextDistance(int offset, int size, int otherOffset,
            int otherSize, int offset2, int otherOffset2, int otherSize2) {
        if (((otherOffset >= offset && otherOffset < offset + size)
                    || (offset >= otherOffset
                        && offset < otherOffset + otherSize))
                && otherOffset2 + otherSize2 <= offset2)
            return offset2 - (otherOffset2 + otherSize2);
        return null;
    }

    @Override
    public void keyPressed(KeyPress key) {
        super.keyPressed(key);
        if (active == null)
            return;
        Key k = key.getKey();
        VLimitedComponent next = null;
        boolean nice = false;
        Rectangle current = active.getLimit();
        Size offset = current.getOffset();
        Size size = current.getSize();
        int closest = 0;
        for (VLimitedComponent other : components) {
            if (other != active) {
                Rectangle otherLimit = other.getLimit();
                Size otherOffset = otherLimit.getOffset();
                Size otherSize = otherLimit.getSize();
                Integer niceDistance = null;
                Integer distance = null;
                switch (k) {
                    case VK_H:
                        niceDistance = nextDistance(activeSquare.getHeight(),
                                1,
                                otherOffset.getHeight(),
                                otherSize.getHeight(),
                                activeSquare.getWidth(),
                                otherOffset.getWidth(),
                                otherSize.getWidth());
                        if (niceDistance == null)
                            distance = nextDistance(offset.getHeight(),
                                    size.getHeight(),
                                    otherOffset.getHeight(),
                                    otherSize.getHeight(),
                                    offset.getWidth(),
                                    otherOffset.getWidth(),
                                    otherSize.getWidth());
                        break;
                    case VK_J:
                        niceDistance = nextDistance(otherOffset.getWidth(),
                                otherSize.getWidth(),
                                activeSquare.getWidth(),
                                1,
                                otherOffset.getHeight(),
                                activeSquare.getHeight(),
                                1);
                        if (niceDistance == null)
                            distance = nextDistance(otherOffset.getWidth(),
                                    otherSize.getWidth(),
                                    offset.getWidth(),
                                    size.getWidth(),
                                    otherOffset.getHeight(),
                                    offset.getHeight(),
                                    size.getHeight());
                        break;
                    case VK_K:
                        niceDistance = nextDistance(activeSquare.getWidth(),
                                1,
                                otherOffset.getWidth(),
                                otherSize.getWidth(),
                                activeSquare.getHeight(),
                                otherOffset.getHeight(),
                                otherSize.getHeight());
                        if (niceDistance == null)
                            distance = nextDistance(offset.getWidth(),
                                    size.getWidth(),
                                    otherOffset.getWidth(),
                                    otherSize.getWidth(),
                                    offset.getHeight(),
                                    otherOffset.getHeight(),
                                    otherSize.getHeight());
                        break;
                    case VK_L:
                        niceDistance = nextDistance(otherOffset.getHeight(),
                                otherSize.getHeight(),
                                activeSquare.getHeight(),
                                1,
                                otherOffset.getWidth(),
                                activeSquare.getWidth(),
                                1);
                        if (niceDistance == null)
                            distance = nextDistance(otherOffset.getHeight(),
                                    otherSize.getHeight(),
                                    offset.getHeight(),
                                    size.getHeight(),
                                    otherOffset.getWidth(),
                                    offset.getWidth(),
                                    size.getWidth());
                        break;
                }
                if (niceDistance != null
                        && (next == null || niceDistance < closest
                            || (!nice && niceDistance == closest))) {
                    next = other;
                    closest = niceDistance;
                    nice = true;
                } else if (distance != null && (next == null
                            || (distance < closest && !nice))) {
                    next = other;
                    closest = distance;
                    nice = false;
                }
            }
        }
        if (next != null)
            setFocus(next);
        Rectangle activeLimit = active.getLimit();
        Size activeMinimumOffset = activeLimit.getOffset();
        Size activeMaximumOffset = activeLimit.getOffset().add(
                activeLimit.getSize()).add(new Size(-1, -1));
        switch (k) {
            case VK_H:
                activeSquare = new Size(activeMinimumOffset.getWidth(),
                        activeSquare.getHeight());
                break;
            case VK_J:
                activeSquare = new Size(activeSquare.getWidth(),
                        activeMaximumOffset.getHeight());
                break;
            case VK_K:
                activeSquare = new Size(activeSquare.getWidth(),
                        activeMinimumOffset.getHeight());
                break;
            case VK_L:
                activeSquare = new Size(activeMaximumOffset.getWidth(),
                        activeSquare.getHeight());
                break;
        }
        if (next != null)
            return;
        active.getComponent().keyPressed(key);
    }
}
