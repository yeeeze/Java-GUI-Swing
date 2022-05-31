package pattern.shapes;

import java.awt.*;

public class GraphicsAttributes {

    private boolean filled;
    private Color color;

    public GraphicsAttributes() {
        this.filled = false;
        this.color = Color.black;
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
