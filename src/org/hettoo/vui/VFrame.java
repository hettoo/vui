package org.hettoo.vui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Observer;
import java.util.Observable;

public class VFrame {
    private JFrame jFrame;
    private VFrameCanvas canvas;

    private boolean invisible;

    public VFrame(Size size) {
        invisible = true;

        jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setResizable(false);
        setTitle("VUI application");
        JPanel panel = (JPanel)jFrame.getContentPane();
        panel.setPreferredSize(new java.awt.Dimension(size.getWidth(),
                    size.getHeight()));
        panel.setLayout(null);

        jFrame.pack();

        KeyListener keyListener = new KeyTransformer();
        canvas = new VFrameCanvas(new VTheme(), panel, keyListener);
        jFrame.addKeyListener(keyListener);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent event) {
                if (invisible) {
                    draw();
                    invisible = false;
                }
            }
        });
    }

    public void show() {
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private class VFrameCanvas extends VCanvas {
        private VTheme theme;
        private VComponent component;

        private JPanel panel;
        private Canvas canvas;

        private BufferStrategy strategy;
        private Graphics graphics;

        public VFrameCanvas(VTheme theme, JPanel panel,
                KeyListener keyListener) {
            super();
            this.theme = theme;
            this.panel = panel;
            canvas = new Canvas();
            canvas.addKeyListener(keyListener);
            setSize(new Size(panel.getWidth(), panel.getHeight()));
            panel.add(canvas);

            canvas.setIgnoreRepaint(true);
            canvas.createBufferStrategy(2);
            strategy = canvas.getBufferStrategy();
            graphics = strategy.getDrawGraphics();

            ((Graphics2D)graphics).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }

        @Override
        public void setSize(Size size) {
            super.setSize(size);
            panel.setPreferredSize(new java.awt.Dimension(size.getWidth(),
                        size.getHeight()));
            canvas.setBounds(0, 0, size.getWidth(), size.getHeight());
        }

        public void setComponent(VComponent component) {
            this.component = component;
            component.setParent(this);
            component.activate();
        }

        @Override
        public VTheme getTheme() {
            return theme;
        }

        private void setColor(Color color) {
            graphics.setColor(new java.awt.Color(color.getRed(),
                        color.getGreen(), color.getBlue(), color.getAlpha()));
        }

        @Override
        public int getTextWidth(String text, int size, FontStyle fontStyle) {
            return graphics.getFontMetrics(new Font("Monospaced",
                        convertFontStyle(fontStyle.getType()),
                        size)).stringWidth(text);
        }

        @Override
        public int getTextHeight(int size, FontStyle fontStyle) {
            return graphics.getFontMetrics(new Font("Monospaced",
                        convertFontStyle(fontStyle.getType()),
                        size)).getHeight();
        }

        @Override
        public void drawRectangle(VRectangle rectangle) {
            setColor(rectangle.getColor());
            Rectangle rect = rectangle.getRectangle();
            Size offset = rect.getOffset();
            Size size = rect.getSize();
            graphics.fillRect(offset.getWidth(), offset.getHeight(),
                    size.getWidth(), size.getHeight());
        }

        private int convertFontStyle(FontType style) {
            switch (style) {
                case BOLD:
                    return Font.BOLD;
            }
            return Font.PLAIN;
        }

        @Override
        public void drawText(String text, int size, Size position,
                FontStyle fontStyle) {
            setColor(fontStyle.getColor());
            graphics.setFont(new Font("Monospaced",
                        convertFontStyle(fontStyle.getType()), size));
            graphics.drawString(text, position.getWidth(),
                    position.getHeight());
        }

        @Override
        public void draw() {
            drawRectangle(new VRectangle(new Rectangle(new Size(0, 0),
                            new Size(canvas.getWidth(), canvas.getHeight())),
                        theme.getRootColor()));
            if (component != null)
                component.draw();
            strategy.show();
        }
    }

    public void setComponent(VComponent component) {
        canvas.setComponent(component);
    }

    public void draw() {
        canvas.draw();
    }

    public void setTitle(String title) {
        jFrame.setTitle(title);
    }

    public void destroy() {
        jFrame.dispose();
    }

    private class KeyTransformer implements KeyListener {
        public void keyReleased(KeyEvent event) {
        }

        public void keyPressed(KeyEvent event) {
        }

        public void keyTyped(KeyEvent event) {
            switch (event.getKeyChar()) {
                case KeyEvent.VK_ESCAPE:
                    destroy();
                    break;
            }
        }
    }
}
