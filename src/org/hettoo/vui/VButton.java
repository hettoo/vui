package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VButton extends VAbstractComponent {
    private String label;

    private List<ActionListener> listeners;

    public VButton() {
        listeners = new ArrayList<ActionListener>();
    }

    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void draw() {
        super.draw();
        Vector size = parent.getSize();
        parent.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(CONTENT_MARGIN, CONTENT_MARGIN),
                        new Vector(size.getX() - CONTENT_MARGIN * 2,
                            size.getY() - CONTENT_MARGIN * 2)),
                    parent.getTheme().getComponentColor()));
        if (label != null) {
            FontStyle style = parent.getTheme().getComponentFontStyle();
            int textWidth = parent.getTextWidth(label, style);
            int textHeight = parent.getTextHeight(style);
            parent.drawText(label, new Vector((size.getX() - textWidth) / 2,
                        (size.getY() + textHeight / 2) / 2), style);
        }
    }

    @Override
    public void keyPressed(KeyPress key) {
        super.keyPressed(key);
        if (key.getKey().isActivator()) {
            for (ActionListener listener : listeners)
                listener.action();
        }
    }
}
