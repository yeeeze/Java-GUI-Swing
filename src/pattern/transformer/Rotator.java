package pattern.transformer;

import pattern.global.Constants;
import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Rotator extends Transformer {

    public Rotator(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y) {
        this.cx = this.selectedShape.getCenterX();
        this.cy = this.selectedShape.getCenterY();
    }

    @Override
    public void keepTransforming(int x, int y) {
        double dx = x - this.cx;
        double dy = y - this.cy;
        int r = (int) (Math.atan2(dy, dx) * (180.0 / Math.PI));

        affineTransform.rotate(r, cx, cy);
    }

    @Override
    public void finalize(int x, int y) {

    }
}
