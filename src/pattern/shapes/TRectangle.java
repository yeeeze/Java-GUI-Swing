package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class TRectangle extends TShape {
	private static final long serialVersionUTD = 1L;

    public TRectangle() {
    	this.shape = new Rectangle();
		this.anchors = new TAnchors();
    }

    public TShape clone() {
        return new TRectangle();
    }

    public void setOrigin(int x, int y) {
    	Rectangle rectangle = (Rectangle) this.shape;
    	rectangle.setFrame(x, y, 0, 0);
    }

    public void resize(int x, int y) {
    	Rectangle rectangle = (Rectangle) this.shape;
    	rectangle.setSize(x - rectangle.x, y - rectangle.y);
    }

	public Rectangle boundingRec() {
		Rectangle rectangle = (Rectangle) this.shape;
		return rectangle.getBounds();
	}

	public void move(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;

		Point p = new Point(x, y);
		rectangle.setFrame(p, rectangle.getSize());
	}
}
