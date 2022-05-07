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
        this.anchors = new TAnchors();
    }

    public TShape clone() {
        return new TLine();
    }

    public void setOrigin(int x, int y) {
        Line2D line = (Line2D) this.shape;
        line.setLine(x, y, x, y);
    }

    public void resize(int x, int y) {
        Line2D line = (Line2D) this.shape;
        line.setLine(line.getX1(), line.getY1(), x, y);
    }
	
	public void move(int x, int y) {
		Line2D line = (Line2D) this.shape;
		
		Rectangle r = line.getBounds();
		
		Point p = new Point(x, y);
		r.setFrame(p, r.getSize());
		line.setLine(r.getX(), r.getY(), r.getWidth()-r.getX(), r.getHeight()-r.getY());
	}
}
