package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VCombobox<E> extends VAbstractComponent {
    private List<E> items;
    private int index;

    private List<ItemListener<E>> listeners;

    public VCombobox() {
        items = new ArrayList<E>();
        index = 0;
        listeners = new ArrayList<ItemListener<E>>();
    }

    public void addItemListener(ItemListener<E> listener) {
        listeners.add(listener);
    }

    public E getItem() {
        if (items.size() == 0)
            return null;
        return items.get(index);
    }

    public void addItem(E item) {
        items.add(item);
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
        E item = getItem();
        if (item != null) {
            FontStyle style = parent.getTheme().getComponentFontStyle();
            int textWidth = parent.getTextWidth(item.toString(), style);
            int textHeight = parent.getTextHeight(style);
            parent.drawText(item.toString(),
                    new Size(textHeight,
                        (size.getHeight() + textHeight / 2) / 2), style);
        }
    }
    
    private class ItemSelector implements ActionListener {
        private int i;
        private E item;
        private VFrame frame;

        public ItemSelector(int i, E item, VFrame frame) {
            this.i = i;
            this.item = item;
            this.frame = frame;
        }

        public void action() {
            if (index != i) {
                index = i;
                for (ItemListener<E> listener : listeners)
                    listener.select(item);
                draw();
            }
            frame.destroy();
        }
    }

    @Override
    public void keyPressed(KeyPress key) {
        super.keyPressed(key);
        if (items.isEmpty())
            return;
        if (key.getKey() == Key.VK_ENTER) {
            VFrame frame = new VFrame(new Size(50 * items.size(), 150));
            VGrid grid = new VGrid(new Size(1, items.size()));
            frame.setComponent(grid);
            int i = 0;
            for (E item : items) {
                VButton button = new VButton();
                button.addActionListener(new ItemSelector(i, item, frame));
                button.setLabel(item.toString());
                grid.addComponent(new VLimitedComponent(button,
                            new Rectangle(new Size(0, i), new Size(1, 1))),
                        i == index);
                i++;
            }
            frame.show();
        }
    }
}
