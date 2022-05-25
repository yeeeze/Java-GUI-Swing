package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Rotator extends Transformer {

    public Rotator(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.prepareRotating(x, y);
    }

    @Override
    public void keepTransforming(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.keepRotating(x, y);
    }

    @Override
    public void finalize(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.finalRotating(x, y);
    }
}
