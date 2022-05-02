package pattern.shapes;

import java.awt.*;
import java.io.Serializable;

abstract public class TShape implements Serializable {

    private static final long serialVersionUTD = 1L;

	protected Shape shape;
    public TAnchors anchors;

    public abstract void setOrigin(int x, int y);
    public abstract void resize(int x, int y);

    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(this.shape);
    }

    public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
    }

    public abstract TShape clone(); // 새로운 객체 생성

    public abstract Rectangle boundingRec();
    public void addPoint(int x, int y){
    }
    
    public abstract void move(int x, int y);
}