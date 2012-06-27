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
        parent.getTheme().drawButton(this);
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
