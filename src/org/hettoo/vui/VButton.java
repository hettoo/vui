package org.hettoo.vui;

public class VButton extends VAbstractComponent {
    private String label;

    public VButton() {
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
            FontStyle style = new FontStyle(FontType.PLAIN,
                    parent.getTheme().getComponentTextColor());
            int fontSize = 18;
            int textWidth = parent.getTextWidth(label, fontSize, style);
            int textHeight = parent.getTextHeight(fontSize, style);
            parent.drawText(label, fontSize,
                    new Size((size.getWidth() - textWidth) / 2,
                        size.getHeight() / 2 + textHeight / 5), style);
            // /5? wtf
        }
    }
}
