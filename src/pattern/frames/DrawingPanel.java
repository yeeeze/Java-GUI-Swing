package pattern.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import pattern.shapes.TLine;
import pattern.shapes.TOval;
import pattern.shapes.TRectangle;
import pattern.shapes.TPolygon;
import pattern.shapes.TShape;

public class DrawingPanel extends JPanel{
    private static final long serialVersionUTD = 1L;

    public enum ETools {
        eRectangle,
        eOval,
        eLine,
        ePolygon
    }

    private ETools eSelectedTool;

    private enum EDrawingState {
        eIdle,
        e2PointDrawing,
        eNPointDrawing
    }

    private EDrawingState eDrawingState;
    private TShape selectedTool;

    public DrawingPanel() {
        this.eDrawingState = EDrawingState.eIdle;
        this.setBackground(Color.white);

        MouseHandler mouseHandler = new MouseHandler();
        // button
        this.addMouseListener(mouseHandler);
        // position
        this.addMouseMotionListener(mouseHandler);
        // wheel
        this.addMouseWheelListener(mouseHandler);
    }

    public void setSelectedTool(ETools eSelectedTool) {
        this.eSelectedTool = eSelectedTool;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    private void prepareDrawing(int x, int y) {
            if (this.eSelectedTool == ETools.ePolygon) {
                this.selectedTool = new TPolygon(x, y);
                this.eDrawingState = EDrawingState.eNPointDrawing;
            } else {
                this.eDrawingState = EDrawingState.e2PointDrawing;

                if (this.eSelectedTool == ETools.eRectangle) {
                    this.selectedTool = new TRectangle(x, y);
                } else if (this.eSelectedTool == ETools.eOval) {
                    this.selectedTool = new TOval(x, y);
                } else if (this.eSelectedTool == ETools.eLine) {
                    this.selectedTool = new TLine(x, y);
                }

                Graphics2D graphics2D = (Graphics2D) this.getGraphics();
                graphics2D.setXORMode(this.getBackground());
                this.selectedTool.draw(graphics2D);
            }
    }

    private void keepDrawing(int x, int y) {
            Graphics2D graphics2D = (Graphics2D) this.getGraphics();    // 패널이 가지고 있는 그래픽스를 가져옴
            graphics2D.setXORMode(this.getBackground());

            // polygon은 좌표를 클릭하고 mouse move시 직선을 추가하며 그려나감
            if(eDrawingState == EDrawingState.eNPointDrawing) {
                ((TPolygon)this.selectedTool).drawLine(graphics2D);
                this.selectedTool.resize(x, y);
                ((TPolygon)this.selectedTool).drawLine(graphics2D);
            } else {
                // erase (배경색을 다시 그린다)
                this.selectedTool.draw(graphics2D);

                // draw (그림을 그리는 역할은?? : 그래픽스!)
                this.selectedTool.resize(x, y);
                this.selectedTool.draw(graphics2D);
            }
    }

    // n개의 점을 그릴 때 사용하는 메소드
    private void continueDrawing(int x, int y) {
        this.selectedTool.addPoint(x, y);
        ((TPolygon)this.selectedTool).setLine(x, y);
    }
    private void finishDrawing(int x, int y) {
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        //graphics2D.setXORMode(this.getBackground());

        if(this.eSelectedTool == ETools.ePolygon) {
            this.selectedTool.draw(graphics2D);
            System.out.println("polygon 1개 완성");
        }
        this.eDrawingState = EDrawingState.eIdle;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(eDrawingState == EDrawingState.eIdle && eSelectedTool == ETools.ePolygon) {
                System.out.println("polygon 생성");
                prepareDrawing(e.getX(), e.getY());
                return;
            }
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(e.getClickCount() == 1) {
                    lButtonClick(e);
                } else if(e.getClickCount() == 2) {
                    lButtonDoubleClick(e);
                }
            }
        }

        private void lButtonClick(MouseEvent e) {
            if(eDrawingState == EDrawingState.eNPointDrawing) {
                System.out.println("click");
                continueDrawing(e.getX(), e.getY());
            }
        }
        private void lButtonDoubleClick(MouseEvent e) {
            if(eDrawingState == EDrawingState.eNPointDrawing){
                finishDrawing(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(eDrawingState == EDrawingState.eNPointDrawing){
                keepDrawing(e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(eDrawingState == EDrawingState.eIdle && eSelectedTool != ETools.ePolygon) {
                prepareDrawing(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(eDrawingState == EDrawingState.e2PointDrawing){
                keepDrawing(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(eDrawingState == EDrawingState.e2PointDrawing){
                finishDrawing(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }
    }
}