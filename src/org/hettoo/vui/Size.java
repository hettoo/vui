package org.hettoo.vui;

public class Size {
    private int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void add(Size other) {
        width += other.width;
        height += other.height;
    }

    public Size limit(Size grid, Size limit) {
        return new Size(width * limit.getWidth() / grid.getWidth(),
                height * limit.getHeight() / grid.getHeight());
    }
}
