package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Mover extends Transformer{

    AffineTransform affineTransform = this.selectedShape.getAffineTransform();

    public Mover(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y, Graphics2D graphics2D) {
        this.selectedShape.setPx(x);
        this.selectedShape.setPy(y);
    }

    @Override
    public void keepTransforming(int x, int y, Graphics2D graphics2D) {
        int px = this.selectedShape.getPx();
        int py = this.selectedShape.getPy();

        affineTransform.translate(x - px,y - py);
        this.selectedShape.setPx(x);
        this.selectedShape.setPy(y);
    }

    @Override
    public void finalize(int x, int y, Graphics2D graphics2D) {

    }
}
