package pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingPanel extends JPanel{
    private static final long serialVersionUTD = 1L;

    public DrawingPanel() {
        this.setBackground(Color.white);

        MouseHandler mouseHandler = new MouseHandler();
        // button
        this.addMouseListener(mouseHandler);
        // position
        this.addMouseMotionListener(mouseHandler);
        // wheel
        this.addMouseWheelListener(mouseHandler);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    Rectangle rectangle;

    private void prepareDrawing(int x, int y) {
        this.rectangle = new Rectangle(x, y);
    }

    private void keepDrawing(int x, int y) {
        // erase (배경색을 다시 그린다)
        this.rectangle.draw(this.getGraphics());
        // draw (그림을 그리는 역할은?? : 그래픽스!)
        this.rectangle.resize(x, y);
        this.rectangle.draw(this.getGraphics()); // 패널이 가지고 있는 그래픽스를 가져옴
    }

    private void finishDrawing(int x, int y) {
    }

    private class Rectangle {
        private int x, y, width, height;

        public Rectangle(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 0;
            this.height = 0;
        }

        public void resize(int x, int y) {
            this.width = x - this.x;
            this.height = y - this.y;
        }

        public void draw(Graphics graphics) {
            graphics.drawRect(this.x, this.y, width, height);
        }
    }

    private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            prepareDrawing(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            finishDrawing(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            keepDrawing(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }
    }
}