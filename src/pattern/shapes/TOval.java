package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class TOval extends TShape {

    public TOval() {
        this.shape = new Ellipse2D.Double();
    }

    public TShape clone() {
        return new TOval();
    }

    public void setOrigin(int x, int y) {
        Ellipse2D.Double oval = (Ellipse2D.Double) this.shape;
        oval.setFrame(x, y, 0, 0);
    }

    public void resize(int x, int y) {
        Ellipse2D.Double oval = (Ellipse2D.Double) this.shape;
        oval.setFrame(oval.x, oval.y, x - oval.x, y - oval.y );
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(this.shape);
    }

	@Override
	public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
	}
}
