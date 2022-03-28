package pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingPanel extends JPanel{
    private static final long serialVersionUTD = 1L;
    public enum EShapes {
        eRectangle,
        eOval,
        eLine,
        ePolygon
    }
    public EShapes eShapes;

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

    Shape shape;

    private void prepareDrawing(int x, int y) {
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.getBackground());

        if(eShapes == EShapes.eRectangle) {
            this.shape = new Rectangle(x, y);
        } else if(eShapes == EShapes.eOval) {
            this.shape = new Oval(x, y);
        } else if(eShapes == EShapes.eLine) {
            this.shape = new Line(x, y);
        }
        this.shape.draw(graphics2D);
    }

    private void keepDrawing(int x, int y) {
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();    // 패널이 가지고 있는 그래픽스를 가져옴
        graphics2D.setXORMode(this.getBackground());

        // erase (배경색을 다시 그린다)
        this.shape.draw(graphics2D);

        // draw (그림을 그리는 역할은?? : 그래픽스!)
        this.shape.resize(x, y);
        this.shape.draw(graphics2D);
    }

    private void finishDrawing(int x, int y) {
    }

    abstract private class Shape {
        abstract public void resize(int x, int y);
        abstract public void draw(Graphics2D graphics2D);
    }
    private class Rectangle extends Shape{
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

        public void draw(Graphics2D graphics2D) {
            graphics2D.drawRect(this.x, this.y, this.width, this.height);
        }
    }

    private class Oval extends Shape{
        private int x, y, width, height;

        public Oval(int x, int y) {
            this.x = x;
            this.y = y;
            this.width = 0;
            this.height = 0;
        }

        public void resize(int x, int y) {
            this.width = x - this.x;
            this.height = y - this.y;
        }

        public void draw(Graphics2D graphics2D) {
            graphics2D.drawOval(this.x, this.y, this.width, this.height);
        }
    }

    private class Line extends Shape{
        private int x1, y1, x2, y2;

        public Line(int x1, int y1) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1;
            this.y2 = y2;
        }

        public void resize(int x2, int y2) {
            this.x2 = x2;
            this.y2 = y2;
        }

        public void draw(Graphics2D graphics2D) {
            graphics2D.drawLine(this.x1, this.y1, this.x2, this.y2);
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