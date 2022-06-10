package pattern.transformer;

import pattern.global.Constants;
import pattern.shapes.TAnchors;
import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static pattern.global.Constants.*;

public abstract class Transformer {

    protected TShape selectedShape;
    protected AffineTransform affineTransform;
    protected TAnchors anchors;

    // working
    protected int px, py; // 전 점
    protected double cx, cy; // 기준점

    public Transformer(TShape selectedShape) {
        this.selectedShape = selectedShape;
        this.affineTransform = this.selectedShape.getAffineTransform();
        this.anchors = this.selectedShape.getAnchors();
    }

    public abstract void prepare(int x, int y);
    public abstract void keepTransforming(int x, int y);
    public abstract void finalize(int x, int y);

}
