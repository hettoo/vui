package org.hettoo.vui;

public class FontStyle {
    private FontType type;
    private Color color;

    public FontStyle(FontType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public FontType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
