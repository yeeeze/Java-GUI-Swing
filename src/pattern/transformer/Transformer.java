package pattern.transformer;

import pattern.shapes.TShape;

import java.awt.*;

public abstract class Transformer {
    protected TShape selectedShape;

    public Transformer(TShape selectedShape) {
        this.selectedShape = selectedShape;
    }

    public abstract void prepare(int x, int y, Graphics2D graphics2D);
    public abstract void keepTransforming(int x, int y, Graphics2D graphics2D);
    public abstract void finalize(int x, int y, Graphics2D graphics2D);

}
