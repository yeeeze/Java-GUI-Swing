package pattern.shapes;

import java.awt.Graphics2D;

public class TLine extends TShape {
    private int x1, y1, x2, y2;

    public TLine(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1;
        this.y2 = y1;
    }

    public void resize(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
