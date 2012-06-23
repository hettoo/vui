package org.hettoo.vui;

public class VLimitedComponent {
    private VComponent component;
    private Rectangle limit;

    public VLimitedComponent(VComponent component, Rectangle limit) {
        this.component = component;
        this.limit = limit;
    }

    public VComponent getComponent() {
        return component;
    }

    public Rectangle getLimit() {
        return limit;
    }
}
