package org.hettoo.vui;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Observer;
import java.util.Observable;

public class VFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JFrame jFrame;
    private VFrameCanvas canvas;
    private BufferStrategy strategy;
    private Graphics graphics;

    public VFrame() {
        jFrame = new JFrame();
        setTitle("VUI application");
        JPanel panel = (JPanel)jFrame.getContentPane();
        panel.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        KeyListener keyListener = new KeyTransformer();
        canvas = new VFrameCanvas(panel, keyListener);
        jFrame.addKeyListener(keyListener);

        jFrame.pack();
        jFrame.setVisible(true);

        canvas.init();
        draw();
    }

    private class VFrameCanvas extends VCanvas {
        private JPanel panel;
        private Canvas canvas;

        public VFrameCanvas(JPanel panel, KeyListener keyListener) {
            super(new Rectangle(0, 0, 1, 1));
            this.panel = panel;
            canvas = new Canvas();
            panel.add(canvas);
            canvas.setIgnoreRepaint(true);
            canvas.addKeyListener(keyListener);
        }

        public void init() {
            canvas.createBufferStrategy(2);
            strategy = canvas.getBufferStrategy();
            graphics = strategy.getDrawGraphics();
        }

        @Override
        public void setActualRectangle(Rectangle actualRectangle) {
            super.setActualRectangle(actualRectangle);
            panel.setPreferredSize(new java.awt.Dimension(
                        actualRectangle.getWidth(),
                        actualRectangle.getHeight()));
        }

        @Override
        public void draw(VSubCanvas parent) {
            if (parent == null)
                super.draw(new VSubCanvas(this));
            else
                super.draw(parent);
        }
    }

    public void draw() {
        canvas.draw(null);
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
