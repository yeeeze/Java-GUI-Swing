package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class TOval extends TShape {

    private static final long serialVersionUTD = 1L;

    public TOval() {
        this.shape = new Ellipse2D.Double();
    }

    public TShape clone() {
        return new TOval();
    }

    @Override
    public void prepareDrawing(int x, int y) {
        Ellipse2D ellipse = (Ellipse2D) this.shape;
        ellipse.setFrame(x, y, 0, 0);
    }

    @Override
    public void keepDrawing(int x, int y) {
        Ellipse2D ellipse = (Ellipse2D) this.shape;
        ellipse.setFrame(ellipse.getX(), ellipse.getY(), x - ellipse.getX(), y - ellipse.getY());
    }
	
	public void move(int x, int y) {
		Ellipse2D.Double oval = (Ellipse2D.Double) this.shape;
		Rectangle r = oval.getBounds();
		
		Point p = new Point(x, y);
		r.setFrame(p, r.getSize());
		oval.setFrame(r);
	}
}
