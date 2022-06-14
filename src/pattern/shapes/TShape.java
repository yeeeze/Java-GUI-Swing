package pattern.shapes;

import pattern.global.Constants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import static pattern.global.Constants.*;
import static pattern.shapes.TAnchors.*;

abstract public class TShape implements Serializable {

    // attributes
    private static final long serialVersionUTD = 1L;
    private boolean bSelected;

    // components
    protected Shape shape;
    public TAnchors anchors;
    private AffineTransform affineTransform;    // 축적된 값을 가지고 있음... shape x affineTransform으로 계산
    private GraphicsAttributes graphicsAttributes;

    public TShape() {
        this.bSelected = false;

        this.anchors = new TAnchors();
        this.affineTransform = new AffineTransform();
        this.affineTransform.setToIdentity();
        this.graphicsAttributes = new GraphicsAttributes();
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

    public GraphicsAttributes getGraphicsAttributes() {
        return this.graphicsAttributes;
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

        if (this.onAnchors(x, y)) {
            return true;
        }

        if(transformedShape.contains(x, y)) {
            this.anchors.seteSelectedAnchors(EAnchors.eMove);
            return true;
        }
        return false;
    }

    public boolean onAnchors(int x, int y) {
        if (this.bSelected) {
            if (this.anchors.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void addPoint(int x, int y){ }

    public void draw(Graphics2D graphics2D, EColorMode eColorMode) {
        Color color = this.graphicsAttributes.getColor();
        graphics2D.setColor(color);

        Stroke stroke = this.graphicsAttributes.getStroke();
        graphics2D.setStroke(stroke);

        Shape transformedShape = this.affineTransform.createTransformedShape(this.shape);
        if (this.graphicsAttributes.isFilled()) {
            graphics2D.fill(transformedShape);
        } else {
            graphics2D.draw(transformedShape);
        }

        // 선택되어 있으면 앵커까지 같이 그려줌
        if(this.bSelected) {
            graphics2D.setStroke(new BasicStroke(1));
            this.anchors.draw(graphics2D, eColorMode, transformedShape.getBounds());
        }
    }

    public void reset() {
        this.shape = this.affineTransform.createTransformedShape(this.shape);
        this.affineTransform.setToIdentity();
    }
}