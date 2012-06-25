package org.hettoo.vui;

public class Test {
    public static void main(String[] args) {
        VFrame frame = new VFrame(new Size(400, 300));
        VGrid grid = new VGrid(new Size(2, 3));
        frame.setComponent(grid);
        VButton button1 = new VButton();
        button1.addActionListener(new ActionListener() {
            public void action() {
                System.out.println("hi");
            }
        });
        button1.setLabel("hoidaar");
        grid.addComponent(new VLimitedComponent(button1,
                    new Rectangle(new Size(1, 1), new Size(1, 1))));
        VButton button2 = new VButton();
        button2.setLabel("test");
        grid.addComponent(new VLimitedComponent(button2,
                    new Rectangle(new Size(0, 0), new Size(2, 1))));
        VButton button3 = new VButton();
        button3.setLabel("hallo");
        grid.addComponent(new VLimitedComponent(button3,
                    new Rectangle(new Size(0, 1), new Size(1, 1))));
        VCombobox<String> box = new VCombobox<String>();
        box.addItem("item1");
        box.addItem("item2");
        grid.addComponent(new VLimitedComponent(box,
                    new Rectangle(new Size(0, 2), new Size(2, 1))));
        frame.show();
    }
}
