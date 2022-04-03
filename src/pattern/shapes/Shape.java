package pattern.shapes;

import java.awt.Graphics2D;

abstract public class Shape {
    abstract public void resize(int x, int y);
    abstract public void draw(Graphics2D graphics2D);
}