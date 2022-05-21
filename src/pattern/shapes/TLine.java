package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class TLine extends TShape {

    private static final long serialVersionUTD = 1L;

    public TLine() {
        this.shape = new Line2D.Double();
    }

    public TShape clone() {
        return new TLine();
    }

    @Override
    public void prepareDrawing(int x, int y) {
        Line2D line = (Line2D) this.shape;
        line.setLine(x, y, x, y);
    }

    @Override
    public void keepDrawing(int x, int y) {
        Line2D line = (Line2D) this.shape;
        line.setLine(line.getX1(), line.getY1(), x, y);
    }
}
