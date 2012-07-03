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

import java.util.List;
import java.util.ArrayList;

public class VFrame {
    private JFrame jFrame;
    private VFrameCanvas canvas;

    public VFrame(Vector size) {
        jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setResizable(false);
        setTitle("VUI application");
        JPanel panel = (JPanel)jFrame.getContentPane();
        panel.setPreferredSize(new java.awt.Dimension(size.getX(),
                    size.getY()));
        panel.setLayout(null);

        jFrame.pack();

        canvas = new VFrameCanvas(new VTheme(), panel);
        jFrame.addKeyListener(canvas);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                draw();
                jFrame.setLocationRelativeTo(null);
            }
        });
    }

    public void show() {
        jFrame.setVisible(true);
    }

    private class VFrameCanvas implements VDrawer, KeyListener {
        private VTheme theme;
        private VComponent component;
        private RenderThread renderer;
        private Vector size;

        private JPanel panel;
        private Canvas canvas;

        private BufferStrategy strategy;
        private Graphics graphics;

        private List<Key> modifierKeys;

        public VFrameCanvas(VTheme theme, JPanel panel) {
            super();
            this.theme = theme;
            this.panel = panel;
            canvas = new Canvas();
            canvas.addKeyListener(this);
            setSize(new Vector(panel.getWidth(), panel.getHeight()));
            panel.add(canvas);

            modifierKeys = new ArrayList<Key>();

            canvas.setIgnoreRepaint(true);
            canvas.createBufferStrategy(2);
            strategy = canvas.getBufferStrategy();
            getGraphics();

            renderer = new RenderThread();
            renderer.start();
        }

        private void getGraphics() {
            graphics = strategy.getDrawGraphics();
            ((Graphics2D)graphics).setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }

        private class RenderThread extends Thread {
            private static final int DELAY = 40;

            private boolean stop;
            private boolean stopped;

            public RenderThread() {
            }

            public void run() {
                stop = false;
                stopped = false;
                while (!stop) {
                    strategy.show();
                    try {
                        Thread.sleep(DELAY);
                    } catch (Exception e) {
                    }
                }
                synchronized (this) {
                    stopped = true;
                }
            }

            public void stopRunning() {
                stop = true;
                while (true) {
                    synchronized (this) {
                        if (stopped)
                            return;
                    }
                }
            }
        }

        public void stop() {
            renderer.stopRunning();
        }

        public void setSize(Vector size) {
            this.size = size;
            panel.setPreferredSize(new java.awt.Dimension(size.getX(),
                        size.getY()));
            canvas.setBounds(0, 0, size.getX(), size.getY());
        }

        public Vector getSize() {
            return size;
        }

        public void setComponent(VComponent component) {
            this.component = component;
            component.setRoot();
            component.setParent(this);
            component.setStatus(VStatus.OVERACTIVE);
        }

        public VTheme getTheme() {
            return theme;
        }

        private void setColor(Color color) {
            graphics.setColor(new java.awt.Color(color.getRed(),
                        color.getGreen(), color.getBlue(), color.getAlpha()));
        }

        public int getTextWidth(String text, FontStyle fontStyle) {
            return graphics.getFontMetrics(
                    createFont(fontStyle)).stringWidth(text);
        }

        public int getTextHeight(FontStyle fontStyle) {
            return graphics.getFontMetrics(createFont(fontStyle)).getHeight();
        }

        public void drawPolygon(VPolygon polygon) {
            setColor(polygon.getColor());
            Polygon poly = polygon.getPolygon();
            Vector[] points = poly.getPoints();
            int[] xPoints = new int[points.length];
            int[] yPoints = new int[points.length];
            for (int i = 0; i < points.length; i++) {
                xPoints[i] = points[i].getX();
                yPoints[i] = points[i].getY();
            }
            if (polygon.mustFill())
                graphics.fillPolygon(xPoints, yPoints, points.length);
            else
                graphics.drawPolygon(xPoints, yPoints, points.length);
        }

        private int convertFontStyle(FontType style) {
            switch (style) {
                case BOLD:
                    return Font.BOLD;
            }
            return Font.PLAIN;
        }

        private Font createFont(FontStyle style) {
            return new Font("Monospaced", convertFontStyle(style.getType()),
                    style.getSize());
        }

        public void drawText(String text, Vector position,
                FontStyle fontStyle) {
            setColor(fontStyle.getColor());
            graphics.setFont(createFont(fontStyle));
            graphics.drawString(text, position.getX(),
                    position.getY());
        }

        public void draw() {
            drawPolygon(new VPolygon(new Rectangle(new Vector(0, 0),
                            new Vector(canvas.getWidth(), canvas.getHeight())),
                        theme.getRootColor()));
            if (component != null)
                component.draw();
        }

        public void redraw() {
            draw();
        }

        private void show() {
            graphics.dispose();
            strategy.show();
            getGraphics();
        }

        public void keyPressed(KeyEvent event) {
            Key key = Key.get(event.getKeyCode());
            if (key.isModifier() && !modifierKeys.contains(key))
                modifierKeys.add(key);
            else
                keyPressed(new KeyPress(key, modifierKeys));
        }

        public void keyReleased(KeyEvent event) {
            Key key = Key.get(event.getKeyCode());
            if (key.isModifier())
                modifierKeys.remove(key);
        }

        public void keyTyped(KeyEvent event) {
        }

        public boolean keyPressed(KeyPress key) {
            switch (key.getKey()) {
                case VK_ESCAPE:
                    destroy();
                    return true;
            }
            if (component == null)
                return false;
            return component.keyPressed(key);
        }

        public VStatus getStatus() {
            return null;
        }

        public void setStatus(VStatus status) {
        }

        public void setRoot() {
        }

        public void setParent(VDrawer parent) {
        }

        public VDrawer getParent() {
            return null;
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

    public String getTitle() {
        return jFrame.getTitle();
    }

    public void destroy() {
        canvas.stop();
        jFrame.dispose();
    }
}
