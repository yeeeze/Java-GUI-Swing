package pattern.transformer;

import pattern.shapes.TAnchors;
import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Transformer {

    protected TShape selectedShape;
    protected AffineTransform affineTransform;
    protected TAnchors anchors;

    // working
    protected int px, py; // 전 점
    protected double cx, cy; // 기준점
    protected double xScale, yScale; // 배율 점

    public Transformer(TShape selectedShape) {
        this.selectedShape = selectedShape;
        this.affineTransform = this.selectedShape.getAffineTransform();
        this.anchors = this.selectedShape.getAnchors();
    }

    public abstract void prepare(int x, int y, Graphics2D graphics2D);
    public abstract void keepTransforming(int x, int y, Graphics2D graphics2D);
    public abstract void finalize(int x, int y, Graphics2D graphics2D);

}
