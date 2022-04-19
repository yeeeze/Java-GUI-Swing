package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TRectangle extends TShape {

    public TRectangle() {
    	this.shape = new Rectangle();
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

    public void draw(Graphics2D graphics2D) {
    	graphics2D.draw(this.shape);
    }

	@Override
	public boolean contains(int x, int y) {
		return this.shape.contains(x, y);
	}
}
