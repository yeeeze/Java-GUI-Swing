package pattern.shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

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
        eRR,     // rotate
        eMove
    }

    private final Ellipse2D[] anchors;
    private EAnchors eSelectedAnchors, eResizeAnchor;

    public EAnchors geteSelectedAnchors() {
        return eSelectedAnchors;
    }

    public void seteSelectedAnchors(EAnchors eSelectedAnchors) {
        this.eSelectedAnchors = eSelectedAnchors;
    }

    public EAnchors geteResizeAnchor() {
        return eResizeAnchor;
    }

    public void seteResizeAnchor(EAnchors eResizeAnchor) {
        this.eResizeAnchor = eResizeAnchor;
    }

    public TAnchors() {
        this.anchors = new Ellipse2D.Double[EAnchors.values().length-1];

        for(int i = 0; i<EAnchors.values().length-1; i++) {
            this.anchors[i] = new Ellipse2D.Double();
        }
    }

    public boolean contains(int x, int y) {
        for(int i = 0; i<EAnchors.values().length-1; i++) {
            if(this.anchors[i].contains(x, y)) {
                this.eSelectedAnchors = EAnchors.values()[i];
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D graphics2D, Rectangle boundingRectangle) {
        // 좌표 계산
        for(int i = 0; i<EAnchors.values().length-1; i++) {
            // anchors 좌표
            int x = boundingRectangle.x - WIDTH/2;
            int y = boundingRectangle.y - HEIGHT/2;
            int w = boundingRectangle.width;
            int h = boundingRectangle.height;

            EAnchors eAnchors = EAnchors.values()[i];
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
//            Color foreground = graphics2D.getColor();
//            graphics2D.setColor(graphics2D.getBackground());
//            graphics2D.fill(this.anchors[eAnchors.ordinal()]);
//            graphics2D.setColor(foreground);
            graphics2D.draw(this.anchors[eAnchors.ordinal()]);
        }
    }

    // resizeAnchor의 원점 계산
    public Point2D getResizeAnchorPoint(int x, int y) {
        switch (this.eSelectedAnchors) {
            case eNW:
                this.eResizeAnchor = EAnchors.eSE;
                break;
            case eWW:
                this.eResizeAnchor = EAnchors.eEE;
                break;
            case eSW:
                this.eResizeAnchor = EAnchors.eNE;
                break;
            case eSS:
                this.eResizeAnchor = EAnchors.eNN;
                break;
            case eSE:
                this.eResizeAnchor = EAnchors.eNW;
                break;
            case eEE:
                this.eResizeAnchor = EAnchors.eWW;
                break;
            case eNE:
                this.eResizeAnchor = EAnchors.eSW;
                break;
            case eNN:
                this.eResizeAnchor = EAnchors.eSS;
                break;
            default:
                break;
        }
        Point2D point = new Point2D.Double(
                anchors[eResizeAnchor.ordinal()].getCenterX(),
                anchors[eResizeAnchor.ordinal()].getCenterY()
        );
        return point;
    }
}
