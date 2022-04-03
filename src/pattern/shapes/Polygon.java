package pattern.shapes;

import java.awt.Graphics2D;

public class Polygon extends Shape {
    private int[] x = new int[5], y = new int[5];
    private int n;

    public Polygon(int x0, int y0) {
        for(int i=0; i<5; i++) {
            this.x[i] = x0;
            this.y[i] = y0;
        }
        this.n = 5;
    }

    public void resize(int x, int y) {
        for(int i=1; i<5; i++) {
            this.x[i] = x;
            this.y[i] = y;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawPolygon(this.x, this.y, this.n);
    }
}