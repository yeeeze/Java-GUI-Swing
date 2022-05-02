package pattern.shapes;

import java.awt.*;

public class TPolygon extends TShape {

    private static final long serialVersionUTD = 1L;

    public TPolygon() {
        this.shape = new Polygon();
        this.anchors = new TAnchors();
    }

    public TShape clone() {
        return new TPolygon();
    }

    public void setOrigin(int x, int y) {
        this.addPoint(x, y);
        this.addPoint(x, y);
    }

    public void addPoint(int x, int y) {
        Polygon polygon = (Polygon) this.shape;
        polygon.addPoint(x, y);
    }

    public void resize(int x, int y) {
        Polygon polygon = (Polygon) this.shape;
        polygon.xpoints[polygon.npoints - 1] = x;
        polygon.ypoints[polygon.npoints - 1] = y;
    }

	public Rectangle boundingRec() {
		Polygon polygon = (Polygon) this.shape;
		return polygon.getBounds();
	}
	
	public void move(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		
		for(int i = 0; i < polygon.npoints; i++) {
			polygon.xpoints[i] = Math.abs(x - polygon.xpoints[i]);
			polygon.ypoints[i] = Math.abs(y - polygon.ypoints[i]);
		}
	}
}