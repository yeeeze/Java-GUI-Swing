package pattern.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import pattern.shapes.TLine;
import pattern.shapes.TOval;
import pattern.shapes.TRectangle;
import pattern.shapes.TPolygon;
import pattern.shapes.TShape;

public class DrawingPanel extends JPanel{
    // attribute
    private static final long serialVersionUTD = 1L;

    // components
    private Vector<TShape> shapes;

//    public enum ETools {
//        eRectangle,
//        eOval,
//        eLine,
//        ePolygon
//    }

    private enum EDrawingState {
        eIdle,
        e2PointDrawing,
        eNPointDrawing
    }

    private EDrawingState eDrawingState;
    private TShape selectedTool;

    public DrawingPanel() {
        // attributes
        this.eDrawingState = EDrawingState.eIdle;
        this.setBackground(Color.white);

        //components
        this.shapes = new Vector<TShape>();

        MouseHandler mouseHandler = new MouseHandler();

        // button
        this.addMouseListener(mouseHandler);
        // position
        this.addMouseMotionListener(mouseHandler);
        // wheel
        this.addMouseWheelListener(mouseHandler);
    }

    public void setSelectedTool(TShape selectedTool) {
        this.selectedTool = selectedTool;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        // 창 최소화 시켰을 때 지워지는 것을 막기 위해
        for(TShape shape: this.shapes) {
            shape.draw((Graphics2D) graphics);
        }
    }

    private void prepareDrawing(int x, int y) {
            Graphics2D graphics2D = (Graphics2D) this.getGraphics();
            graphics2D.setXORMode(this.getBackground());
            this.selectedTool.start(x, y);
            this.selectedTool.draw(graphics2D);
    }

    private void keepDrawing(int x, int y) {
            Graphics2D graphics2D = (Graphics2D) this.getGraphics();    // 패널이 가지고 있는 그래픽스를 가져옴
            graphics2D.setXORMode(this.getBackground());

            // erase (배경색을 다시 그린다)
            this.selectedTool.draw(graphics2D);

            // draw (그림을 그리는 역할은?? : 그래픽스!)
            this.selectedTool.resize(x, y);
            this.selectedTool.draw(graphics2D);
    }

    // n개의 점을 그릴 때 사용하는 메소드
    private void continueDrawing(int x, int y) {
        this.selectedTool.addPoint(x, y);
    }

    private void finishDrawing(int x, int y) {
        // 그림 저장
        this.shapes.add(this.selectedTool);
    }

    private class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(e.getClickCount() == 1) {
                    lButtonClick(e);
                } else if(e.getClickCount() == 2) {
                    lButtonDoubleClick(e);
                }
            }
        }

        private void lButtonClick(MouseEvent e) {
            if(eDrawingState == EDrawingState.eIdle) {
                if (selectedTool.getClass() == TPolygon.class) {
                    eDrawingState = EDrawingState.eNPointDrawing;
                    System.out.println("polygon 생성");
                    prepareDrawing(e.getX(), e.getY());
                }
            } else if(eDrawingState == EDrawingState.eNPointDrawing) {
                continueDrawing(e.getX(), e.getY());
            }
        }

        private void lButtonDoubleClick(MouseEvent e) {
            if(eDrawingState == EDrawingState.eNPointDrawing){
                finishDrawing(e.getX(), e.getY());
                eDrawingState = EDrawingState.eIdle;
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
            if(eDrawingState == EDrawingState.eIdle) {
                if (selectedTool.getClass() != TPolygon.class) {
                    eDrawingState = EDrawingState.e2PointDrawing;
                    prepareDrawing(e.getX(), e.getY());
                }
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
                eDrawingState = EDrawingState.eIdle;
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