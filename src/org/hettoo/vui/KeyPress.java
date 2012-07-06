package org.hettoo.vui;

import java.util.List;

public class KeyPress {
    private Key key;
    private List<Key> modifiers;

    public KeyPress(Key key, List<Key> modifiers) {
        this.key = key;
        this.modifiers = modifiers;
    }

    public Key getKey() {
        return key;
    }

    public List<Key> getModifiers() {
        return modifiers;
    }

    public boolean isEscaper() {
        return getKey() == Key.VK_OPEN_BRACKET
            && getModifiers().contains(Key.VK_CONTROL);
    }
}
