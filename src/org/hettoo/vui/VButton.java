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
                    new Color(0.94f, 0.94f, 0.72f)));
        if (label != null) {
            FontStyle style = new FontStyle(FontType.PLAIN, 18,
                    new Color(0, 0, 0));
            int textWidth = parent.getTextWidth(label, style);
            int textHeight = parent.getTextHeight(style);
            parent.drawText(label, new Size((size.getWidth() - textWidth) / 2,
                        size.getHeight() / 2 + textHeight / 5), style);
            // /5? wtf
        }
    }
}
