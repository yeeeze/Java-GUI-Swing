package pattern.shapes;

import java.awt.Graphics2D;

public class TPolygon extends TShape {
    private final int MAX_POINTS = 20;

    private int[] xPoints;
    private int[] yPoints;
    private int nPoints;
    private TLine line;

    public TPolygon(int x, int y) {
        this.nPoints = 0;
        this.xPoints = new int[MAX_POINTS];
        this.yPoints = new int[MAX_POINTS];

        this.addPoint(x, y);

        this.line = new TLine(x, y);
    }

    public void addPoint(int x, int y) {
        this.xPoints[this.nPoints] = x;
        this.yPoints[this.nPoints] = y;
        this.nPoints++;
        System.out.println("nPoints = " + nPoints);
    }

    public void resize(int x, int y) {
        this.line.resize(x, y);
    }

    public void setLine(int x, int y) {
        this.line.setX1(x);
        this.line.setY1(y);
    }

    // 움직이면서 Line을 그려나감
    public void drawLine(Graphics2D graphics2D) {
        graphics2D.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawPolygon(xPoints, yPoints, nPoints);
    }
}