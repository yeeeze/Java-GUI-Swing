package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Resizer extends Transformer {

    AffineTransform affineTransform = this.selectedShape.getAffineTransform();
    int sx, sy;

    public Resizer(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.setPx(x);
        this.selectedShape.setPy(y);
    }

    @Override
    public void keepTransforming(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.keepResize(x, y);
    }

    @Override
    public void finalize(int x, int y, Graphics2D graphics2D) {

    }
}
