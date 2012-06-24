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
        Size size = parent.getSize();
        parent.drawRectangle(new VRectangle(new Rectangle(
                        new Size(CONTENT_MARGIN, CONTENT_MARGIN),
                        new Size(size.getWidth() - CONTENT_MARGIN * 2,
                            size.getHeight() - CONTENT_MARGIN * 2)),
                    parent.getTheme().getComponentColor()));
        if (label != null) {
            FontStyle style = new FontStyle(FontType.PLAIN, 18,
                    parent.getTheme().getComponentTextColor());
            int textWidth = parent.getTextWidth(label, style);
            int textHeight = parent.getTextHeight(style);
            parent.drawText(label, new Size((size.getWidth() - textWidth) / 2,
                        size.getHeight() / 2 + textHeight / 5), style);
            // /5? wtf
        }
    }

    @Override
    public void keyTyped(KeyPress key) {
        super.keyTyped(key);
        if (key.getKey() == Key.VK_ENTER) {
            for (ActionListener listener : listeners)
                listener.action();
        }
    }
}
