package pattern.shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

import static pattern.shapes.TAnchors.*;

abstract public class TShape implements Serializable {

    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    protected Shape shape;
    public TAnchors anchors;

    // working
    private int px, py;
    private AffineTransform affineTransform;    // 축적된 값을 가지고 있음... shape x affineTransform으로 계산
    private boolean bSelected;
    private EAnchors eSelectedAnchors;
    private Rectangle rotateAnchors;

    public TShape() {
        this.anchors = new TAnchors();
        this.bSelected = false;
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
        return this.eSelectedAnchors;
    }

    public void seteAnchors(EAnchors eAnchors) {
        this.eSelectedAnchors = eAnchors;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public AffineTransform getAffineTransform() {
        return affineTransform;
    }

    public Point getPoint() {
        int x = this.shape.getBounds().x + this.shape.getBounds().width / 2;
        int y = this.shape.getBounds().y + this.shape.getBounds().height / 2;
        return new Point(x, y);
    }

    public Rectangle getRotateAnchors() {
        return this.shape.getBounds();
    }

    public void setRotateAnchors(Rectangle rotateAnchors) {
        this.rotateAnchors = rotateAnchors;
    }

    public abstract void prepareDrawing(int x, int y);
    public abstract void keepDrawing(int x, int y);

    public boolean contains(int x, int y) {
        Shape transformedShape = this.affineTransform.createTransformedShape(this.shape);
        if(this.bSelected) {
            this.eSelectedAnchors = this.anchors.contains(x, y);
            if (this.eSelectedAnchors != null) {
                return true;
            }
        }
        if(transformedShape.contains(x, y)) {
            this.eSelectedAnchors = EAnchors.eMove;
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

    public void keepResize(int x, int y) {
        int width = px - this.shape.getBounds().x;
        int height = py - this.shape.getBounds().y;
        double width2 = x - this.shape.getBounds().x;
        double height2 = y - this.shape.getBounds().y;

        double sx = width2/width;
        double sy = height2/height;
        affineTransform.scale(sx, sy);
    }
}