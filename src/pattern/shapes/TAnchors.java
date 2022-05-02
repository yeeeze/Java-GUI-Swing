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

    public TAnchors(Ellipse2D[] anchors) {
        this.anchors = new Ellipse2D.Double[EAnchors.values().length];

        for(EAnchors eAnchors: EAnchors.values()) {
            this.anchors[eAnchors.ordinal()] = new Ellipse2D.Double();
        }
    }

    public void draw(Graphics2D graphics2D, Rectangle boundingRectangle) {
        // anchors 좌표
        int x = boundingRectangle.x;
        int y = boundingRectangle.y;
        int w = boundingRectangle.width;
        int h = boundingRectangle.height;

        // 좌표 계산
        for(EAnchors eAnchors: EAnchors.values()) {
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
                    break;
                default:
                    break;
            }

            this.anchors[eAnchors.ordinal()].setFrame(x, y, WIDTH, HEIGHT);
            graphics2D.draw(this.anchors[eAnchors.ordinal()]);
        }
    }
}
