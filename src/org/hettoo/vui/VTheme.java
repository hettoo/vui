package org.hettoo.vui;

public class VTheme {
    public VTheme() {
    }

    public Color getRootColor() {
        return new Color(0.05f, 0.03f, 0.01f);
    }

    public Color getComponentColor() {
        return new Color(0.94f, 0.94f, 0.72f);
    }

    public Color getComponentTextColor() {
        return new Color(0, 0, 0);
    }

    public Color getStatusColor(VStatus status) {
        switch (status) {
            case BUSY:
                return new Color(0.4f, 0.4f, 0.4f);
            case WAITING:
                return new Color(1.0f, 0.4f, 0.0f);
            case ACTIVE:
                return new Color(0.0f, 0.8f, 0.8f);
            case OVERACTIVE:
                return new Color(0.0f, 1.0f, 0.0f);
        }
        return getRootColor();
    }
}
