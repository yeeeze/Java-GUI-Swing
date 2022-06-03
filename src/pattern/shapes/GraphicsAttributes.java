package pattern.shapes;

import pattern.global.Constants;

import java.awt.*;

import static pattern.global.Constants.*;

public class GraphicsAttributes {

    private boolean filled;
    private Color color;

    public GraphicsAttributes() {
        this.filled = false;
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
}
