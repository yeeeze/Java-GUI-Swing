package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Rotator extends Transformer {

    AffineTransform affineTransform = this.selectedShape.getAffineTransform();

    public Rotator(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.setRotateAnchors(this.selectedShape.getRotateAnchors());
    }

    @Override
    public void keepTransforming(int x, int y, Graphics2D graphics2D) {
        Point point = this.selectedShape.getPoint();

        double dx = x - point.x;
        double dy = y - point.y;
        int r = (int) (Math.atan2(dy, dx) * (180.0 / Math.PI));

        affineTransform.rotate(r, point.x, point.y);
    }

    @Override
    public void finalize(int x, int y, Graphics2D graphics2D) {
    }
}
