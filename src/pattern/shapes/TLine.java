package pattern.shapes;

import java.awt.Graphics2D;

public class TLine extends TShape {
    private int x1, y1, x2, y2;

    public TLine() {
    }

    public void start(int x1, int y1) {
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
}
