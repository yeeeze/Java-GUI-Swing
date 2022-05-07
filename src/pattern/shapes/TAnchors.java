package pattern.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TAnchors {
    private final static int WIDTH = 15;
    private final static int HEIGHT = 15;

    public enum EAnchors {
        eNW,
        eWW,
        eSW,
        eSS,
        eSE,
        eEE,
        eNE,
        eNN,
        eRR     // rotate
    }

    private Ellipse2D anchors[];

    public TAnchors() {
        this.anchors = new Ellipse2D.Double[EAnchors.values().length];

        for(EAnchors eAnchors: EAnchors.values()) {
            this.anchors[eAnchors.ordinal()] = new Ellipse2D.Double();
        }
    }

    public void draw(Graphics2D graphics2D, Rectangle boundingRectangle) {
        // 좌표 계산
        for(EAnchors eAnchors: EAnchors.values()) {
            // anchors 좌표
            int x = boundingRectangle.x - WIDTH/2;
            int y = boundingRectangle.y - HEIGHT/2;
            int w = boundingRectangle.width;
            int h = boundingRectangle.height;

            switch (eAnchors) {
                case eNW:
                    break;
                case eWW:
                    y = y + h/2;
                    break;
                case eSW:
                    y = y + h;
                    break;
                case eSS:
                    x = x + w/2;
                    y = y + h;
                    break;
                case eSE:
                    x = x + w;
                    y = y + h;
                    break;
                case eEE:
                    x = x + w;
                    y = y + h/2;
                    break;
                case eNE:
                    x = x + w;
                    break;
                case eNN:
                    x = x + w/2;
                    break;
                case eRR:
                    x = x + w/2;
                    y = y - h/2;
                    break;
                default:
                    break;
            }

            this.anchors[eAnchors.ordinal()].setFrame(x, y, WIDTH, HEIGHT);
            Color foreground = graphics2D.getColor();
            graphics2D.setColor(graphics2D.getBackground());
            graphics2D.fill(this.anchors[eAnchors.ordinal()]);
            graphics2D.setColor(foreground);
            graphics2D.draw(this.anchors[eAnchors.ordinal()]);
        }
    }
}
