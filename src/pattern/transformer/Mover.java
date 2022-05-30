package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Mover extends Transformer{

    public Mover(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y, Graphics2D graphics2D) {
        this.px = x;
        this.py = y;
    }

    @Override
    public void keepTransforming(int x, int y, Graphics2D graphics2D) {
        this.affineTransform.translate(x - this.px, y - this.py);
        this.px = x;
        this.py = y;
    }

    @Override
    public void finalize(int x, int y, Graphics2D graphics2D) {

    }
}
