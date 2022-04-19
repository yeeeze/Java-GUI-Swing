package pattern.shapes;

import java.awt.Graphics2D;

public class TOval extends TShape {
    private int x, y, width, height;

    public TOval() {
    }

    public TShape clone() {
        return new TOval();
    }

    public void setOrigin(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
    }

    public void resize(int x, int y) {
        this.width = x - this.x;
        this.height = y - this.y;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawOval(this.x, this.y, this.width, this.height);
    }

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
