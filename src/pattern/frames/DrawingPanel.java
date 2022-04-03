package pattern.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import pattern.shapes.Line;
import pattern.shapes.Oval;
import pattern.shapes.Rectangle;
import pattern.shapes.Polygon;
import pattern.shapes.Shape;

public class DrawingPanel extends JPanel{
    private static final long serialVersionUTD = 1L;

    public enum ETools {
        eRectangle,
        eOval,
        eLine,
        ePolygon
    }

    private ETools eSelectedTool;

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

    public void setSelectedTool(ETools eSelectedTool) {
        this.eSelectedTool = eSelectedTool;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    Shape shape;

    private void prepareDrawing(int x, int y) {
        if(this.eSelectedTool == ETools.eRectangle) {
            this.shape = new Rectangle(x, y);
        } else if(this.eSelectedTool == ETools.eOval) {
            this.shape = new Oval(x, y);
        } else if(this.eSelectedTool == ETools.eLine) {
            this.shape = new Line(x, y);
        } else if(this.eSelectedTool == ETools.ePolygon) {
            this.shape = new Polygon(x, y);
        }

        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.getBackground());
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