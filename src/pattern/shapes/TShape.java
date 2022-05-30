package pattern.shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import static pattern.shapes.TAnchors.*;

abstract public class TShape implements Serializable {

    // attributes
    private static final long serialVersionUTD = 1L;
    private boolean bSelected;

    // components
    protected Shape shape;
    public TAnchors anchors;
    private AffineTransform affineTransform;    // 축적된 값을 가지고 있음... shape x affineTransform으로 계산

    public TShape() {
        this.bSelected = false;

        this.anchors = new TAnchors();
        this.affineTransform = new AffineTransform();
        this.affineTransform.setToIdentity();
    }
    public abstract TShape clone(); // 새로운 객체 생성

    public boolean isSelected() {
        return this.bSelected;
    }

    public void setSelected(boolean bSelected) {
        this.bSelected = bSelected;
    }

    public EAnchors geteAnchors() {
        return this.anchors.geteSelectedAnchors();
    }

    public AffineTransform getAffineTransform() {
        return this.affineTransform;
    }

    public TAnchors getAnchors() {
        return this.anchors;
    }

    public double getCenterX() {
        return this.shape.getBounds().getCenterX();
    }

    public double getCenterY() {
        return this.shape.getBounds().getCenterY();
    }

    public abstract void prepareDrawing(int x, int y);
    public abstract void keepDrawing(int x, int y);

    public boolean contains(int x, int y) {
        Shape transformedShape = this.affineTransform.createTransformedShape(this.shape);
        if(this.bSelected) {
            if (this.anchors.contains(x, y)) {
                return true;
            }
        }
        if(transformedShape.contains(x, y)) {
            this.anchors.seteSelectedAnchors(EAnchors.eMove);
            return true;
        }
        return false;
    }

    public void addPoint(int x, int y){ }

    public void draw(Graphics2D graphics2D) {
        Shape transformedShape = this.affineTransform.createTransformedShape(this.shape);
        graphics2D.draw(transformedShape);

        // 선택되어 있으면 앵커까지 같이 그려줌
        if(this.bSelected) {
            this.anchors.draw(graphics2D, transformedShape.getBounds());
        }
    }
}