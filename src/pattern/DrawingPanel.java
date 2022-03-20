package pattern;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private static final long serialVersionUTD = 1L;

    public DrawingPanel() {
        this.setBackground(Color.white);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawRect(20, 20, 40, 40);
        graphics.fillOval(20, 20, 40, 40);
        graphics.drawLine(20, 20, 40, 40);

        graphics.setColor(Color.RED);
        int xPoints[] = {20, 40, 60};
        int yPoints[] = {20, 49, 60};
        int nPoints = 3;
        graphics.drawPolygon(xPoints, yPoints, nPoints);
    }
}
