package org.hettoo.vui;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Observer;
import java.util.Observable;

public class VFrame {
    private static final Color ROOT_COLOR = new Color(0.72f, 0.66f, 0.5f, 1);

    private JFrame jFrame;
    private VFrameCanvas canvas;

    public VFrame(Size size) {
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
        canvas = new VFrameCanvas(panel, keyListener);
        jFrame.addKeyListener(keyListener);
    }

    public void show() {
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        while (!jFrame.isVisible());
        draw();
    }

    private class VFrameCanvas extends VCanvas {
        private VComponent component;

        private JPanel panel;
        private Canvas canvas;

        private BufferStrategy strategy;
        private Graphics graphics;

        public VFrameCanvas(JPanel panel, KeyListener keyListener) {
            super(new Size(1, 1));
            this.panel = panel;
            canvas = new Canvas();
            canvas.addKeyListener(keyListener);
            setActualSize(new Size(panel.getWidth(), panel.getHeight()));
            panel.add(canvas);

            canvas.setIgnoreRepaint(true);
            canvas.createBufferStrategy(2);
            strategy = canvas.getBufferStrategy();
            graphics = strategy.getDrawGraphics();
        }

        @Override
        public void setActualSize(Size actualSize) {
            super.setActualSize(actualSize);
            panel.setPreferredSize(new java.awt.Dimension(actualSize.getWidth(),
                        actualSize.getHeight()));
            canvas.setBounds(0, 0, actualSize.getWidth(),
                    actualSize.getHeight());
        }

        public void setComponent(VComponent component) {
            this.component = component;
        }

        @Override
        public void drawRectangle(VRectangle rectangle) {
            Color color = rectangle.getColor();
            graphics.setColor(new java.awt.Color(color.getRed(),
                        color.getGreen(), color.getBlue(), color.getAlpha()));
            Rectangle rect = rectangle.getRectangle();
            graphics.fillRect(rect.getX(), rect.getY(), rect.getWidth(),
                    rect.getHeight());
        }

        @Override
        public void draw(VDrawable parent) {
            drawRectangle(new VRectangle(new Rectangle(0, 0, canvas.getWidth(),
                            canvas.getHeight()), ROOT_COLOR));
            super.draw(parent);
            if (component != null)
                component.draw(parent);
        }

        public void draw() {
            draw(this);
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
