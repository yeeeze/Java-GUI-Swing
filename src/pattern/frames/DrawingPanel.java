package pattern.frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import pattern.global.Constants.ETools;
import pattern.shapes.*;

import static pattern.shapes.TAnchors.*;

public class DrawingPanel extends JPanel implements Printable{
    // attribute
    private static final long serialVersionUTD = 1L;
    private boolean updated;
    
    // components
    private Vector<TShape> shapes;

    // associated attribute
    private ETools selectedTool;
    private TShape selectedShape;
    private TShape currentShape;
    
    // working variables
    private enum EDrawingState {
        eIdle,
        e2PointDrawing,
        eNPointDrawing,
        eRotating,
        eMoving
    }
    private EDrawingState eDrawingState;

    private enum EColor {
        eForeground,
        eBackground
    }
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
        this.updated = false;
        
    	this.removeAll();
        this.selectedShape = null;
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
        if(this.selectedShape != null) {
            this.selectedShape.setSelected(false);
        }
        this.repaint();

        this.currentShape = this.selectedTool.newShape();
        Graphics2D graphics2d = (Graphics2D) this.getGraphics();
        graphics2d.setXORMode(this.getBackground());

        this.currentShape.setOrigin(x, y);
        this.currentShape.draw(graphics2d);
    }
    
    private void keepDrawing(int x, int y) {
        // erase
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.getBackground());
        this.currentShape.draw(graphics2D);

        // draw
        this.currentShape.resize(x, y);
        this.currentShape.draw(graphics2D);
    }

    // n개의 점을 그릴 때 사용하는 메소드
    private void continueDrawing(int x, int y) {
        this.currentShape.addPoint(x, y);
    }

    private void finishDrawing(int x, int y) {
        if(this.selectedShape != null) {
            this.selectedShape.setSelected(false);
        }

        if(!(this.currentShape instanceof TSelection)) {
            // 그림 저장
            this.shapes.add(this.currentShape);
            this.selectedShape = this.currentShape;
            this.selectedShape.setSelected(true);
            this.selectedShape.draw((Graphics2D) this.getGraphics());
        }

        this.repaint();
    }

    private void prepareMoving(int x, int y) {
        changeSelection(x, y);
    }

    private void keepMoving(int x, int y) {
        this.selectedShape.setSelected(false);
        repaint();

        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.getBackground());

        this.selectedShape.draw(graphics2D);
        this.selectedShape.move(x, y);
        this.selectedShape.draw(graphics2D);
    }

    private void finishMoving(int x, int y) {
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        this.selectedShape.setSelected(true);
        this.selectedShape.draw(graphics2D);
    }

    private void prepareRotating(int x, int y) {
        changeSelection(x, y);
    }

    private void keepRotating(int x, int y) {
        this.selectedShape.setSelected(false);
        repaint();

        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.getBackground());

        this.selectedShape.draw(graphics2D);
        this.selectedShape.rotate(x, y);
        this.selectedShape.draw(graphics2D);
    }

    private void finishRotating(int x, int y) {
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        this.selectedShape.setSelected(true);
    }

    private TShape onShape(int x, int y) {
    	for(TShape shape: this.shapes) {
    		if(shape.contains(x, y)) {
    			return shape;
    		}
    	}
    	return null;
    }

    private void changeSelection(int x, int y) {
        if(this.selectedShape != null) {
            this.selectedShape.setSelected(false);
        }
        this.repaint();

        // draw 클릭한 도형을 그림
        this.selectedShape = this.onShape(x, y);
        if(this.selectedShape != null) {
            this.selectedShape.setSelected(true);
            this.selectedShape.draw((Graphics2D) this.getGraphics());
        }
    }

    private void changeCursor(int x, int y) {
    	Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
        if (this.selectedTool == ETools.eSelection) {
            cursor = new Cursor(Cursor.DEFAULT_CURSOR);

            this.currentShape = this.onShape(x, y);

            if (this.currentShape != null) {
                cursor = new Cursor(Cursor.MOVE_CURSOR);    // 맥에서는 무브 커서가 디폴트랑 똑같음
                if (this.currentShape.isSelected()) {
                    EAnchors eAnchors = this.currentShape.geteAnchors();

                    switch (eAnchors) {
                        case eRR: cursor = new Cursor(Cursor.HAND_CURSOR); break;   //rotate 커서 만들기
                        case eNW: cursor = new Cursor(Cursor.NW_RESIZE_CURSOR); break;
                        case eWW: cursor = new Cursor(Cursor.W_RESIZE_CURSOR); break;
                        case eSW: cursor = new Cursor(Cursor.SW_RESIZE_CURSOR); break;
                        case eSS: cursor = new Cursor(Cursor.S_RESIZE_CURSOR); break;
                        case eSE: cursor = new Cursor(Cursor.SE_RESIZE_CURSOR); break;
                        case eEE: cursor = new Cursor(Cursor.E_RESIZE_CURSOR); break;
                        case eNE: cursor = new Cursor(Cursor.NE_RESIZE_CURSOR); break;
                        case eNN: cursor = new Cursor(Cursor.N_RESIZE_CURSOR); break;
                        default: break;
                    }
                }
            }
        }
    	this.setCursor(cursor);
    }

 	@Override
 	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
 		// TODO Auto-generated method stub
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
                if (selectedTool == ETools.eSelection) {
                    // move, rotate, resize 등...
                    currentShape = onShape(e.getX(), e.getY());

                    if (currentShape != null) {
                        EAnchors eAnchors = currentShape.geteAnchors();
                        if (eAnchors == EAnchors.eMove) {
                            eDrawingState = EDrawingState.eMoving;
                            prepareMoving(e.getX(), e.getY());
                        } else if (eAnchors == EAnchors.eRR) {
                            prepareRotating(e.getX(), e.getY());
                            eDrawingState = EDrawingState.eRotating;
                        } else {
                            // resize
                        }
                    }
                } else {
                    if (selectedTool != ETools.ePolygon) {
                        eDrawingState = EDrawingState.e2PointDrawing;
                        prepareDrawing(e.getX(), e.getY());
                    }
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(eDrawingState == EDrawingState.e2PointDrawing){
                keepDrawing(e.getX(), e.getY());
            } else if (eDrawingState == EDrawingState.eMoving) {
                keepMoving(e.getX(), e.getY());
            } else if (eDrawingState == EDrawingState.eRotating) {
                keepRotating(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(eDrawingState == EDrawingState.e2PointDrawing){
                finishDrawing(e.getX(), e.getY());
                eDrawingState = EDrawingState.eIdle;
            } else if (eDrawingState == EDrawingState.eMoving) {
                finishMoving(e.getX(), e.getY());
                eDrawingState = EDrawingState.eIdle;
            } else if (eDrawingState == EDrawingState.eRotating) {
                finishRotating(e.getX(), e.getY());
                eDrawingState = EDrawingState.eRotating;
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