package pattern.shapes;

import java.awt.Graphics2D;

abstract public class TShape {
    public abstract void resize(int x, int y);
    public abstract void draw(Graphics2D graphics2D);
    public void addPoint(int x, int y){
    }
}