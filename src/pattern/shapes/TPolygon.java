package pattern.shapes;

import java.awt.*;

public class TPolygon extends TShape {
    private final int MAX_POINTS = 20;

    public TPolygon() {
        this.shape = new Polygon();
        Polygon polygon = (Polygon) this.shape;
        polygon.xpoints = new int[MAX_POINTS];
        polygon.ypoints = new int[MAX_POINTS];
    }

    public TShape clone() {
        return new TPolygon();
    }

    public void setOrigin(int x, int y) {
        Polygon polygon = (Polygon) this.shape;
        polygon.npoints = 0;
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

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(this.shape);
    }

	@Override
	public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
	}
}