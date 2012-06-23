package org.hettoo.vui;

public class FontStyle {
    private FontType type;
    private int size;
    private Color color;

    public FontStyle(FontType type, int size, Color color) {
        this.type = type;
        this.size = size;
        this.color = color;
    }

    public FontType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}
