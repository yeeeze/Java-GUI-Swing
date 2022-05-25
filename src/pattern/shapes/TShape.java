package pattern.shapes;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import static pattern.shapes.TAnchors.*;

abstract public class TShape implements Serializable {

    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    protected Shape shape;
    public TAnchors anchors;

    // working
    private int px, py; // 전 점
    private double cx, cy; // 기준점
    private double xScale, yScale; // 배율 점
    private AffineTransform affineTransform;    // 축적된 값을 가지고 있음... shape x affineTransform으로 계산
    private boolean bSelected;

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
        return this.anchors.geteSelectedAnchors();
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
        graphics2D.draw(transformedShape);;

        // 선택되어 있으면 앵커까지 같이 그려줌
        if(this.bSelected) {
            this.anchors.draw(graphics2D, transformedShape.getBounds());
        }
    }

    public void prepareMoving(int x, int y) {
        // 원점 저장
        this.px = x;
        this.py = y;
    }

    public void keepMoving(int x, int y) {
        // materix 계산
        this.affineTransform.translate(x - this.px,y - this.py);
        this.px = x;
        this.py = y;
    }

    public void finalizeMoving(int x, int y) {
        this.shape = this.affineTransform.createTransformedShape(this.shape);
        this.affineTransform.setToIdentity();
    }

    public void prepareResizing(int x, int y) {
        // 원점 저장
        this.px = x;
        this.py = y;
        Point2D resizeAnchorPoint = this.anchors.getResizeAnchorPoint(x, y);    //
        this.cx = resizeAnchorPoint.getX();
        this.cy = resizeAnchorPoint.getY();
    }

    public void keepResizing(int x, int y) {
        this.getResizeScale(x, y);
        this.affineTransform.translate(cx, cy);
        this.affineTransform.scale(this.xScale, this.yScale);
        this.affineTransform.translate(-cx, -cy);
        this.px = x;
        this.py = y;
    }

    public void finalizeResizing(int x, int y) {
        this.shape = this.affineTransform.createTransformedShape(this.shape);
        this.affineTransform.setToIdentity();
    }

    public void prepareRotating(int x, int y) {

    }

    public void keepRotating(int x, int y) {
        cx = this.shape.getBounds().x + this.shape.getBounds().width / 2;
        cy = this.shape.getBounds().y + this.shape.getBounds().height / 2;

        double dx = x - cx;
        double dy = y - cy;
        int r = (int) (Math.atan2(dy, dx) * (180.0 / Math.PI));

        affineTransform.rotate(r, cx, cy);
    }

    public void finalRotating(int x, int y) {

    }

    // cx, cy 계산
    private void getResizeScale(int x, int y) {
        EAnchors eResizeAnchor = this.anchors.geteResizeAnchor();

        // 밑에 네줄은 절댓값으로 계산....
        double w1 = px - cx;
        double w2 = x - cx;
        double h1 = py - cy;
        double h2 = y - cy;

        switch (eResizeAnchor) {
            case eNW:
                xScale = w2 / w1;
                yScale = h2 / h1;
                break;
            case eWW:
                xScale = w2 / w1;
                yScale = 1;
                break;
            case eSW:
                xScale = w2 / w1;
                yScale = h2 / h1;
                break;
            case eSS:
                xScale = 1;
                yScale = h2 / h1;
                break;
            case eSE:
                xScale = w2 / w1;
                yScale = h2 / h1;
                break;
            case eEE:
                xScale = w2 / w1;
                yScale = 1;
                break;
            case eNE:
                xScale = w2 / w1;
                yScale = h2 / h1;
                break;
            case eNN:
                xScale = 1;
                yScale = h2 / h1;
                break;
            default:
                break;
        }
    }
}