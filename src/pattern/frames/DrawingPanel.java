package pattern.frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import pattern.global.Constants;
import pattern.global.Constants.ETools;
import pattern.global.Constants.SaveState;
import pattern.shapes.TLine;
import pattern.shapes.TOval;
import pattern.shapes.TRectangle;
import pattern.shapes.TPolygon;
import pattern.shapes.TShape;

public class DrawingPanel extends JPanel implements Printable{
    // attribute
    private static final long serialVersionUTD = 1L;
    private boolean updated;
    
    // components
    private Vector<TShape> shapes;

    // associated attribute
    private ETools selectedTool;
    private TShape currentShape;
    
    // working variables
    private enum EDrawingState {
        eIdle,
        e2PointDrawing,
        eNPointDrawing
    }
    private EDrawingState eDrawingState;
    
    public DrawingPanel() {
        // attributes
        this.eDrawingState = EDrawingState.eIdle;
        this.setBackground(Color.white);
        this.updated = false;

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
    
    public void init() {
        this.eDrawingState = EDrawingState.eIdle;
        this.setBackground(Color.white);
        this.updated = false;
        
    	this.removeAll();
    	this.shapes = new Vector<TShape>();
    	this.repaint();
    }
    
    public boolean isUpdated() {
    	return this.updated;
    }
    
    public void setUpdated(boolean updated) {
    	this.updated = updated;
    }
    
    @SuppressWarnings("unchecked")
	public void setShapes(Object shapes) {
    	this.shapes = (Vector<TShape>) shapes;
    	this.repaint();
    }
    
    public Object getShapes() {
    	return this.shapes;
    }

    public void setSelectedTool(ETools selectedTool) {
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
        this.currentShape = this.selectedTool.newShape();
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.getBackground());

        this.currentShape.setOrigin(x, y);
        this.currentShape.draw(graphics2D);
    }

    private void keepDrawing(int x, int y) {
            Graphics2D graphics2D = (Graphics2D) this.getGraphics();    // 패널이 가지고 있는 그래픽스를 가져옴
            graphics2D.setXORMode(this.getBackground());

            // erase (배경색을 다시 그린다)
            this.currentShape.draw(graphics2D);

            // draw (그림을 그리는 역할은?? : 그래픽스!)
            this.currentShape.resize(x, y);
            this.currentShape.draw(graphics2D);
    }

    // n개의 점을 그릴 때 사용하는 메소드
    private void continueDrawing(int x, int y) {
        this.currentShape.addPoint(x, y);
    }

    private void finishDrawing(int x, int y) {
        // 그림 저장
        this.shapes.add(this.currentShape);
        this.setUpdated(true);
    }
    
    private boolean onShape(int x, int y) {
    	for(TShape shape: this.shapes) {
    		if(shape.contains(x, y)) {
    			return true;
    		}
    	}
    	return false;
    }

    private void changeCursor(int x, int y) {
    	Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
    	if(this.onShape(x, y)) {
    		cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
    	}
    	this.setCursor(cursor);
    }
    
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex >= 1)
			return NO_SUCH_PAGE;
		graphics.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());

			paint(graphics);
			return PAGE_EXISTS;
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
                if (selectedTool == ETools.ePolygon) {
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
            else if (eDrawingState == EDrawingState.eIdle) {
            	changeCursor(e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(eDrawingState == EDrawingState.eIdle) {
                if (selectedTool != ETools.ePolygon) {
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