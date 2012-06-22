package org.hettoo.vui;

public class VButton extends VAbstractComponent {
    public VButton() {
    }

    @Override
    public void draw() {
        parent.drawRectangle(new VRectangle(new Rectangle(new Size(0, 0),
                        parent.getSize()), new Color(1, 1, 1)));
        super.draw();
    }
}
