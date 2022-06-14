package pattern.shapes;

import pattern.global.Constants;

import java.awt.*;

import static pattern.global.Constants.*;

public class GraphicsAttributes {

    private boolean filled;
    private Color color;
    private Stroke stroke;

    public GraphicsAttributes() {
        this.filled = false;
        this.stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, 0);
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}
