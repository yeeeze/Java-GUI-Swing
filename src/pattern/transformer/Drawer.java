package pattern.transformer;

import pattern.global.Constants;
import pattern.shapes.TShape;

import java.awt.*;

public class Drawer extends Transformer {
    public Drawer(TShape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void prepare(int x, int y) {
        this.selectedShape.prepareDrawing(x, y);
    }

    @Override
    public void keepTransforming(int x, int y) {
        this.selectedShape.keepDrawing(x, y);
    }

    @Override
    public void finalize(int x, int y) {

    }
}
