package org.hettoo.vui;

import java.util.List;
import java.util.ArrayList;

public class VCombobox<E> extends VAbstractComponent {
    private List<E> items;
    private int index;

    private List<ItemListener<E>> listeners;

    private boolean selecting;

    public VCombobox() {
        items = new ArrayList<E>();
        index = 0;
        listeners = new ArrayList<ItemListener<E>>();
        selecting = false;
    }

    public void addItemListener(ItemListener<E> listener) {
        listeners.add(listener);
    }

    public E getItem() {
        if (items.size() == 0)
            return null;
        return items.get(index);
    }

    public String getLabel() {
        E item = getItem();
        return item == null ? null : item.toString();
    }

    public void addItem(E item) {
        items.add(item);
    }

    public void removeItem(E item) {
        items.remove(item);
    }

    @Override
    public void draw() {
        super.draw();
        E item = getItem();
        parent.getTheme().drawCombobox(this);
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
            selecting = false;
        }
    }

    @Override
    public void keyPressed(KeyPress key) {
        super.keyPressed(key);
        if (items.isEmpty())
            return;
        if (key.getKey().isActivator() && !selecting) {
            VFrame frame = new VFrame(new Vector(300, 80 * items.size())) {
                @Override
                public void destroy() {
                    super.destroy();
                    selecting = false;
                }
            };
            frame.setTitle("Select an item");
            VGrid grid = new VGrid(new Vector(1, items.size()));
            frame.setComponent(grid);
            int i = 0;
            for (E item : items) {
                VButton button = new VButton();
                button.addActionListener(new ItemSelector(i, item, frame));
                button.setLabel(item.toString());
                grid.addComponent(new VLimitedComponent(button,
                            new Rectangle(new Vector(0, i), new Vector(1, 1))),
                        i == index);
                i++;
            }
            frame.show();
            selecting = true;
        }
    }
}
