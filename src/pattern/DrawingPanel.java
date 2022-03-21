package pattern;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class DrawingPanel extends JPanel{
    private static final long serialVersionUTD = 1L;

    public DrawingPanel() {
        this.setBackground(Color.white);

        MouseHandler mouseHandler = new MouseHandler();
        // button
        this.addMouseListener(mouseHandler);
        // position
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    private class MouseHandler implements MouseInputListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            String[] eNameArr = e.paramString().split(",");
            System.out.println(eNameArr[0]);
            System.out.println("x좌표 : " + e.getX() + ", y좌표 : " +e.getY());
        }
    }
}
