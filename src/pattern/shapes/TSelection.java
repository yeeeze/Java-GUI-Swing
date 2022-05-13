package pattern.shapes;

import java.awt.*;

public class TSelection extends TShape {

    private static final long serialVersionUTD = 1L;

    public TSelection() {
        this.shape = new Rectangle();
    }

    public TShape clone() {
        return new TSelection();
    }

    public void setOrigin(int x, int y) {
        Rectangle rectangle = (Rectangle) this.shape;
        rectangle.setFrame(x, y, 0, 0);
    }

    public void resize(int x, int y) {
        Rectangle rectangle = (Rectangle) this.shape;
        rectangle.setSize(x - rectangle.x, y - rectangle.y);
    }
}

