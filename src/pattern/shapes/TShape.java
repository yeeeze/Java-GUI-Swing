package pattern.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

abstract public class TShape implements Serializable {
	protected Shape shape;
	
    public abstract void setOrigin(int x, int y);
    public abstract void resize(int x, int y);
    public abstract void draw(Graphics2D graphics2D);
    public abstract TShape clone(); // 새로운 객체 생성
    public abstract boolean contains(int x, int y);
    
    public void addPoint(int x, int y){
    }
}