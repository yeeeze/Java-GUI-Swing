package pattern.shapes;

import java.awt.*;
import java.io.Serializable;

import static pattern.shapes.TAnchors.*;

abstract public class TShape implements Serializable {

    // attributes
    private static final long serialVersionUTD = 1L;

    // components
	protected Shape shape;
    public TAnchors anchors;

    // working
    private boolean bSelected;
    private EAnchors eSelectedAnchors;

    public TShape() {
        this.anchors = new TAnchors();
        this.bSelected = false;
    }

    public abstract TShape clone(); // 새로운 객체 생성

    public boolean isSelected() {
        return bSelected;
    }

    public void setSelected(boolean bSelected) {
        this.bSelected = bSelected;
    }

    public EAnchors geteAnchors() {
        return eSelectedAnchors;
    }

    public void seteAnchors(EAnchors eSelectedAnchors) {
        this.eSelectedAnchors = eSelectedAnchors;
    }

    public abstract void setOrigin(int x, int y);
    public abstract void resize(int x, int y);

    public boolean contains(int x, int y) {
        if(this.bSelected) {
            this.eSelectedAnchors = this.anchors.contains(x, y);
            if(this.eSelectedAnchors != null) {
                return true;
            }
        }
        if(this.shape.contains(x, y)) {
            this.eSelectedAnchors = EAnchors.eMove;
            return true;
        }
        return false;
    }

    public void addPoint(int x, int y){
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(this.shape);

        // 선택되어 있으면 앵커까지 같이 그려줌
        if(this.bSelected) {
            this.anchors.draw(graphics2D, this.shape.getBounds());
        }
    }
    
    public void move(int x, int y) {

    }

    public void rotate(int x, int y) {}
}