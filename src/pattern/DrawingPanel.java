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

    private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "(" + e.getButton() + ") : " + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY() + ": " + e.getClickCount());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + "x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0] + " : " + e.getWheelRotation() + ", " + e.getScrollAmount());
        }
    }
}