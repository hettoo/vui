package org.hettoo.vui;

public class Test {
    public static void main(String[] args) {
        VFrame frame = new VFrame(new Size(400, 300));
        VGrid grid = new VGrid(new Size(2, 2));
        frame.setComponent(grid);
        VButton button1 = new VButton();
        button1.setLabel("hoidaar");
        grid.addComponent(button1, new Rectangle(new Size(1, 1),
                    new Size(1, 1)));
        VButton button2 = new VButton();
        button2.setLabel("test");
        grid.addComponent(button2, new Rectangle(new Size(0, 0),
                    new Size(1, 1)));
        frame.show();
    }
}
