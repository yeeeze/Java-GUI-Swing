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

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

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
    }
}
