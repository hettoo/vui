package org.hettoo.vui;

public class Rectangle {
    private Size offset;
    private Size size;

    public Rectangle(Size offset, Size size) {
        this.offset = offset;
        this.size = size;
    }

    public Size getOffset() {
        return offset;
    }

    public Size getSize() {
        return size;
    }
}
