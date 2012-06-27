package org.hettoo.vui;

public class VTheme {
    protected static final int STATUS_MARGIN = 5;
    protected static final int CONTENT_MARGIN = STATUS_MARGIN + 2;
    protected static final int NORMAL_MARGIN = 18;

    public VTheme() {
    }

    public Color getRootColor() {
        return new Color(0.05f, 0.03f, 0.01f);
    }

    public Color getComponentColor() {
        return new Color(0.94f, 0.94f, 0.72f);
    }

    public Color getComponentTextColor() {
        return new Color(0, 0, 0);
    }

    public FontStyle getComponentFontStyle() {
        return new FontStyle(FontType.PLAIN, 18, getComponentTextColor());
    }

    public Color getStatusColor(VStatus status) {
        switch (status) {
            case BUSY:
                return new Color(0.4f, 0.4f, 0.4f);
            case WAITING:
                return new Color(1.0f, 0.4f, 0.0f);
            case ACTIVE:
                return new Color(0.0f, 0.8f, 0.8f);
            case OVERACTIVE:
                return new Color(0.0f, 1.0f, 0.0f);
        }
        return getRootColor();
    }

    public void drawStatus(VComponent component) {
        VDrawer drawer = component.getParent();
        Vector size = drawer.getSize();
        Color statusColor = getStatusColor(component.getStatus());
        drawer.drawRectangle(new VRectangle(new Rectangle(new Vector(0, 0),
                        new Vector(STATUS_MARGIN, size.getY())),
                    statusColor));
        drawer.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(size.getX() - STATUS_MARGIN, 0),
                        new Vector(STATUS_MARGIN, size.getY())),
                    statusColor));
        drawer.drawRectangle(new VRectangle(new Rectangle(new Vector(0, 0),
                        new Vector(size.getX(), STATUS_MARGIN)),
                    statusColor));
        drawer.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(0, size.getY() - STATUS_MARGIN),
                        new Vector(size.getX(), STATUS_MARGIN)),
                    statusColor));
    }

    public void drawButton(VButton button) {
        VDrawer drawer = button.getParent();
        Vector size = drawer.getSize();
        drawer.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(CONTENT_MARGIN, CONTENT_MARGIN),
                        new Vector(size.getX() - CONTENT_MARGIN * 2,
                            size.getY() - CONTENT_MARGIN * 2)),
                    getComponentColor()));
        String label = button.getLabel();
        if (label != null) {
            FontStyle style = getComponentFontStyle();
            int textWidth = drawer.getTextWidth(label, style);
            int textHeight = drawer.getTextHeight(style);
            drawer.drawText(label, new Vector((size.getX() - textWidth) / 2,
                        (size.getY() + textHeight / 2) / 2), style);
        }
    }

    public void drawCombobox(VCombobox combobox) {
        VDrawer drawer = combobox.getParent();
        Vector size = drawer.getSize();
        drawer.drawRectangle(new VRectangle(new Rectangle(
                        new Vector(CONTENT_MARGIN, CONTENT_MARGIN),
                        new Vector(size.getX() - CONTENT_MARGIN * 2,
                            size.getY() - CONTENT_MARGIN * 2)),
                    drawer.getTheme().getComponentColor()));
        String item = combobox.getLabel();
        if (item != null) {
            FontStyle style = getComponentFontStyle();
            int textWidth = drawer.getTextWidth(item, style);
            int textHeight = drawer.getTextHeight(style);
            drawer.drawText(item, new Vector(NORMAL_MARGIN,
                        (size.getY() + textHeight / 2) / 2), style);
        }
    }
}
