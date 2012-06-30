package org.hettoo.vui;

public class Test {
    public static void main(String[] args) {
        VFrame frame = new VFrame(new Vector(400, 400));
        VGrid grid = new VGrid(new Vector(2, 4));
        frame.setComponent(grid);
        VButton button1 = new VButton();
        button1.addActionListener(new ActionListener() {
            public void action() {
                System.out.println("hi");
            }
        });
        button1.setLabel("hoidaar");
        grid.addComponent(new VLimitedComponent(button1,
                    new Rectangle(new Vector(1, 1), new Vector(1, 1))));
        VButton button2 = new VButton();
        button2.setLabel("test");
        grid.addComponent(new VLimitedComponent(button2,
                    new Rectangle(new Vector(0, 0), new Vector(2, 1))));
        VButton button3 = new VButton();
        button3.setLabel("hallo");
        grid.addComponent(new VLimitedComponent(button3,
                    new Rectangle(new Vector(0, 1), new Vector(1, 1))));
        VCombobox<String> box = new VCombobox<String>();
        box.addItem("item1");
        box.addItem("item2");
        grid.addComponent(new VLimitedComponent(box,
                    new Rectangle(new Vector(0, 2), new Vector(2, 1))));
        VLabel label = new VLabel();
        label.setLabel("testlabel");
        grid.addComponent(new VLimitedComponent(label,
                    new Rectangle(new Vector(0, 3), new Vector(1, 1))));
        VGrid subGrid = new VGrid(new Vector(2, 1));
        grid.addComponent(new VLimitedComponent(subGrid,
                    new Rectangle(new Vector(1, 3), new Vector(1, 1))));
        VLabel a = new VLabel();
        a.setLabel("a");
        subGrid.addComponent(new VLimitedComponent(a,
                    new Rectangle(new Vector(0, 0), new Vector(1, 1))));
        VLabel b = new VLabel();
        b.setLabel("b");
        subGrid.addComponent(new VLimitedComponent(b,
                    new Rectangle(new Vector(1, 0), new Vector(1, 1))));
        frame.show();
    }
}
