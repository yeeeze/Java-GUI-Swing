package pattern.frames;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Vector;

import com.sun.java.accessibility.util.Translator;
import pattern.colorChange.ColorChange;
import pattern.global.Constants;
import pattern.global.Constants.ETools;
import pattern.shapes.*;
import pattern.transformer.*;

import static pattern.global.Constants.*;
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
    private Transformer transformer;
    private ColorChange colorChange;

    // working variables
    private enum EDrawingState {
        eIdle,
        e2PointDrawing,
        eNPointDrawing,
        eRotating,
        eMoving,
        eResizing
    }
    private EDrawingState eDrawingState;
    private EColorMode eColorMode;

    public DrawingPanel() {
        // attributes
        this.eDrawingState = EDrawingState.eIdle;
        this.eColorMode = EColorMode.eRightMode;
        this.setBackground(this.eColorMode.geteBackground());
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
            shape.draw((Graphics2D) graphics, this.eColorMode);
        }
    }

    private void prepareTransforming(int x, int y) {
        if (selectedTool == ETools.eSelection) {
            // move, rotate, resize 등...
            currentShape = onShape(x, y);

            if (currentShape != null) {
                EAnchors eAnchors = currentShape.geteAnchors();
                if (eAnchors == EAnchors.eMove) {
                    this.transformer = new Mover(this.currentShape);
                } else if (eAnchors == EAnchors.eRR) {
                    this.transformer = new Rotator(this.currentShape);
                } else {
                    this.transformer = new Resizer(this.currentShape);
                }
            } else {
                // selection
                this.currentShape = this.selectedTool.newShape();
                this.transformer = new Drawer(this.currentShape);

                this.colorChange = new ColorChange(this.currentShape);
                this.colorChange.changeColor(this.eColorMode.geteForeground());
            }
        } else {
            // draw
            this.currentShape = this.selectedTool.newShape();
            this.transformer = new Drawer(this.currentShape);

            this.colorChange = new ColorChange(this.currentShape);
            this.colorChange.changeColor(this.eColorMode.geteForeground());
        }

        Graphics2D graphics2d = (Graphics2D) this.getGraphics();
        graphics2d.setXORMode(this.eColorMode.geteBackground());
        this.transformer.prepare(x, y, graphics2d);
    }
    
    private void keepTransforming(int x, int y) {
        // erase
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.eColorMode.geteBackground());
        this.currentShape.draw(graphics2D, this.eColorMode);

        this.currentShape.setSelected(false);

        // draw
        this.transformer.keepTransforming(x, y, graphics2D);
        this.currentShape.draw(graphics2D, this.eColorMode);
    }

    // n개의 점을 그릴 때 사용하는 메소드
    private void continueTransforming(int x, int y) {
        this.currentShape.addPoint(x, y);
    }

    private void finishTransforming(int x, int y) {
        Graphics2D graphics2D = (Graphics2D) this.getGraphics();
        graphics2D.setXORMode(this.eColorMode.geteBackground());
        this.transformer.finalize(x, y, graphics2D);

        if(this.selectedShape != null) {
            this.selectedShape.setSelected(false);
        }

        if(!(this.currentShape instanceof TSelection)) {
            // 그림 저장
            this.shapes.add(this.currentShape);
            this.selectedShape = this.currentShape;
            this.selectedShape.setSelected(true);
            this.setUpdated(true);
        }

        this.repaint();
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
            this.selectedShape.draw((Graphics2D) this.getGraphics(), this.eColorMode);
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

    public void changeColorMode() {
        if (this.eColorMode == EColorMode.eRightMode) {
            this.eColorMode = EColorMode.eDarkMode;
        } else {
            this.eColorMode = EColorMode.eRightMode;
        }
        this.setBackground(this.eColorMode.geteBackground());

        for (TShape shape : this.shapes) {
            if (shape.getGraphicsAttributes().getColor() == this.eColorMode.geteBackground()) {
                shape.getGraphicsAttributes().setColor(this.eColorMode.geteForeground());
            }
        }
    }

    public void changeColor(Color color) {
        this.colorChange = new ColorChange(this.selectedShape);

        this.colorChange.changeColor(color);
        this.selectedShape.draw((Graphics2D) this.getGraphics(), this.eColorMode);
    }

    public void fill() {
        this.colorChange = new ColorChange(this.selectedShape);

        if (this.colorChange.filled()) {
            this.selectedShape.draw((Graphics2D) this.getGraphics(), this.eColorMode);
        } else {
            repaint();
        }
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
                if (selectedTool.getTransformationStyle() == ETransformationStyle.eNPTransformation) {
                    eDrawingState = EDrawingState.eNPointDrawing;
                    prepareTransforming(e.getX(), e.getY());
                    System.out.println("polygon 생성");
                }
            } else if(eDrawingState == EDrawingState.eNPointDrawing) {
                continueTransforming(e.getX(), e.getY());
            }
        }

        private void lButtonDoubleClick(MouseEvent e) {
            if(eDrawingState == EDrawingState.eNPointDrawing){
                finishTransforming(e.getX(), e.getY());
                eDrawingState = EDrawingState.eIdle;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(eDrawingState == EDrawingState.eNPointDrawing){
                keepTransforming(e.getX(), e.getY());
            }
            else if (eDrawingState == EDrawingState.eIdle) {
                changeCursor(e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(eDrawingState == EDrawingState.eIdle) {
                changeSelection(e.getX(), e.getY());
                if(selectedTool.getTransformationStyle() == ETransformationStyle.e2PTransformation) {
                    prepareTransforming(e.getX(), e.getY());    // draw, move, resize 선택
                    eDrawingState = EDrawingState.e2PointDrawing;
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(eDrawingState == EDrawingState.e2PointDrawing){
                keepTransforming(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(eDrawingState == EDrawingState.e2PointDrawing){
                finishTransforming(e.getX(), e.getY());
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