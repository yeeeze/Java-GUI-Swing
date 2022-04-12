package pattern.shapes;

import java.awt.Graphics2D;

public class TPolygon extends TShape {
    private final int MAX_POINTS = 20;

    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;

    public TPolygon() {
    }

    public void start(int x, int y) {
        this.nPoints = 0;
        this.xPoints = new int[MAX_POINTS];
        this.yPoints = new int[MAX_POINTS];

        this.addPoint(x, y);
        this.addPoint(x, y);

    }

    public void addPoint(int x, int y) {
        this.xPoints[this.nPoints] = x;
        this.yPoints[this.nPoints] = y;
        this.nPoints++;
    }

    public void resize(int x, int y) {
        this.xPoints[this.nPoints - 1] = x;
        this.yPoints[this.nPoints - 1] = y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawPolyline(xPoints, yPoints, nPoints);
    }
}