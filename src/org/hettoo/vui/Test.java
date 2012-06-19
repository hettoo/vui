package org.hettoo.vui;

public class Test {
    public static void main(String[] args) {
        VFrame frame = new VFrame(new Size(800, 600));
        VGrid grid = new VGrid(3, 3);
        frame.setComponent(grid);
        frame.show();
    }
}
