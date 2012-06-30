package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VGrid extends VAbstractComponent {
    private Vector size;
    private VLimitedComponent active;
    private Vector activeSquare;
    private List<VLimitedComponent> components;

    public VGrid(Vector size) {
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
            component.getComponent().setStatus(VStatus.INACTIVE);
        }
    }

    public void addComponent(VLimitedComponent component) {
        addComponent(component, false);
    }

    private void setFocus(VLimitedComponent component) {
        if (active != null) {
            active.getComponent().setStatus(VStatus.INACTIVE);
            active.getComponent().draw();
        }
        active = component;
        if (status == VStatus.ACTIVE || status == VStatus.OVERACTIVE)
            active.getComponent().setStatus(VStatus.ACTIVE);
        else
            active.getComponent().setStatus(VStatus.INACTIVE);
        active.getComponent().draw();
    }

    public void draw() {
        for (VLimitedComponent component : components)
            component.getComponent().draw();
        if (status != VStatus.OVERACTIVE || components.isEmpty())
            super.draw();
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
    public void setStatus(VStatus status) {
        switch (status) {
            case ACTIVE:
                if (active != null)
                    active.getComponent().setStatus(VStatus.INACTIVE);
                break;
            case OVERACTIVE:
                if (active != null)
                    active.getComponent().setStatus(VStatus.ACTIVE);
                break;
        }
        super.setStatus(status);
    }

    @Override
    public boolean keyPressed(KeyPress key) {
        if (active == null
                || active.getComponent().getStatus() != VStatus.OVERACTIVE) {
            if (super.keyPressed(key))
                return true;
            if (active == null)
                return false;
            if (active.getComponent().getStatus() != VStatus.OVERACTIVE) {
                Key k = key.getKey();
                boolean handled = true;
                switch (k) {
                    case VK_I:
                        active.getComponent().setStatus(VStatus.OVERACTIVE);
                        break;
                    default:
                        handled = false;
                        break;
                }
                if (handled)
                    return true;
                VLimitedComponent next = null;
                boolean nice = false;
                Rectangle current = active.getLimit();
                Vector offset = current.getOffset();
                Vector size = current.getSize();
                int closest = 0;
                for (VLimitedComponent other : components) {
                    if (other != active) {
                        Rectangle otherLimit = other.getLimit();
                        Vector otherOffset = otherLimit.getOffset();
                        Vector otherSize = otherLimit.getSize();
                        Integer niceDistance = null;
                        Integer distance = null;
                        switch (k) {
                            case VK_H:
                                niceDistance = nextDistance(activeSquare.getY(),
                                        1,
                                        otherOffset.getY(),
                                        otherSize.getY(),
                                        activeSquare.getX(),
                                        otherOffset.getX(),
                                        otherSize.getX());
                                if (niceDistance == null)
                                    distance = nextDistance(offset.getY(),
                                            size.getY(),
                                            otherOffset.getY(),
                                            otherSize.getY(),
                                            offset.getX(),
                                            otherOffset.getX(),
                                            otherSize.getX());
                                break;
                            case VK_J:
                                niceDistance = nextDistance(otherOffset.getX(),
                                        otherSize.getX(),
                                        activeSquare.getX(),
                                        1,
                                        otherOffset.getY(),
                                        activeSquare.getY(),
                                        1);
                                if (niceDistance == null)
                                    distance = nextDistance(otherOffset.getX(),
                                            otherSize.getX(),
                                            offset.getX(),
                                            size.getX(),
                                            otherOffset.getY(),
                                            offset.getY(),
                                            size.getY());
                                break;
                            case VK_K:
                                niceDistance = nextDistance(activeSquare.getX(),
                                        1,
                                        otherOffset.getX(),
                                        otherSize.getX(),
                                        activeSquare.getY(),
                                        otherOffset.getY(),
                                        otherSize.getY());
                                if (niceDistance == null)
                                    distance = nextDistance(offset.getX(),
                                            size.getX(),
                                            otherOffset.getX(),
                                            otherSize.getX(),
                                            offset.getY(),
                                            otherOffset.getY(),
                                            otherSize.getY());
                                break;
                            case VK_L:
                                niceDistance = nextDistance(otherOffset.getY(),
                                        otherSize.getY(),
                                        activeSquare.getY(),
                                        1,
                                        otherOffset.getX(),
                                        activeSquare.getX(),
                                        1);
                                if (niceDistance == null)
                                    distance = nextDistance(otherOffset.getY(),
                                            otherSize.getY(),
                                            offset.getY(),
                                            size.getY(),
                                            otherOffset.getX(),
                                            offset.getX(),
                                            size.getX());
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
                Vector activeMinimumOffset = activeLimit.getOffset();
                Vector activeMaximumOffset = activeLimit.getOffset().add(
                        activeLimit.getSize()).add(new Vector(-1, -1));
                switch (k) {
                    case VK_H:
                        activeSquare = new Vector(activeMinimumOffset.getX(),
                                activeSquare.getY());
                        break;
                    case VK_J:
                        activeSquare = new Vector(activeSquare.getX(),
                                activeMaximumOffset.getY());
                        break;
                    case VK_K:
                        activeSquare = new Vector(activeSquare.getX(),
                                activeMinimumOffset.getY());
                        break;
                    case VK_L:
                        activeSquare = new Vector(activeMaximumOffset.getX(),
                                activeSquare.getY());
                        break;
                }
                if (next != null)
                    return true;
            }
        }
        if (active != null)
            return active.getComponent().keyPressed(key);
        return false;
    }
}
