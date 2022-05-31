package pattern.colorChange;

import pattern.shapes.GraphicsAttributes;
import pattern.shapes.TAnchors;
import pattern.shapes.TShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ColorChange {

    private TShape selectedShape;
    private AffineTransform affineTransform;
    private TAnchors anchors;
    private GraphicsAttributes graphicsAttributes;

    public ColorChange(TShape selectedShape) {
        this.selectedShape = selectedShape;
        this.affineTransform = this.selectedShape.getAffineTransform();
        this.anchors = this.selectedShape.getAnchors();
        this.graphicsAttributes = this.selectedShape.getGraphicsAttributes();
    }

    public void changeColor(Color color) {
        this.graphicsAttributes.setColor(color);
    }
}
