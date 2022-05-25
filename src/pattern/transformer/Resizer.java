package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Resizer extends Transformer {

    public Resizer(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.prepareResizing(x, y);
    }

    @Override
    public void keepTransforming(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.keepResizing(x, y);
    }

    @Override
    public void finalize(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.finalizeResizing(x, y);
    }
}
