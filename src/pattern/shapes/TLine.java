package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class TLine extends TShape {

    public TLine() {
        this.shape = new Line2D.Double();
    }

    public TShape clone() {
        return new TLine();
    }

    public void setOrigin(int x1, int y1) {
        Line2D.Double line = (Line2D.Double) this.shape;
        line.setLine(x1, y1, x1, y1);
    }

    public void resize(int x2, int y2) {
        Line2D.Double line = (Line2D.Double) this.shape;
        line.x2 = x2;
        line.y2 = y2;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(this.shape);
    }

	@Override
	public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
	}
}
