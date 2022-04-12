package pattern.frames;

import javax.swing.*;

import pattern.shapes.*;

import java.awt.*;
import java.awt.event.*;

public class ToolBar extends JToolBar {
    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    private JRadioButton rectangleTool;
    private JRadioButton ovalTool;
    private JRadioButton lineTool;
    private JRadioButton polygonTool;

    // associations
    private DrawingPanel drawingPanel;

    public ToolBar() {
        // components
        ButtonGroup buttonGroup = new ButtonGroup();
        ActionHandler actionHandler = new ActionHandler();

        this.rectangleTool = new JRadioButton("rectangle");
        this.rectangleTool.addActionListener(actionHandler);
        this.add(this.rectangleTool);
        buttonGroup.add(this.rectangleTool);

        this.ovalTool = new JRadioButton("oval");
        this.ovalTool.addActionListener(actionHandler);
        this.add(this.ovalTool);
        buttonGroup.add(this.ovalTool);

        this.lineTool = new JRadioButton("line");
        this.lineTool.addActionListener(actionHandler);
        this.add(this.lineTool);
        buttonGroup.add(this.lineTool);

        this.polygonTool = new JRadioButton("polygon");
        this.polygonTool.addActionListener(actionHandler);
        this.add(this.polygonTool);
        buttonGroup.add(this.polygonTool);
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.rectangleTool.doClick();
    }

    public TShape newShape(TShape tShape) {
        if (tShape.getClass() == TRectangle.class) {
            return new TRectangle();
        }
        if (tShape.getClass() == TOval.class) {
            return new TOval();
        }
        if (tShape.getClass() == TLine.class) {
            return new TLine();
        }
        else {
            return new TPolygon();
        }
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == rectangleTool) {
                drawingPanel.setSelectedTool(new TRectangle());
            } else if(e.getSource() == ovalTool) {
                drawingPanel.setSelectedTool(new TOval());
            } else if(e.getSource() == lineTool) {
                drawingPanel.setSelectedTool(new TLine());
            } else if(e.getSource() == polygonTool){
                drawingPanel.setSelectedTool(new TPolygon());
            }
        }
    }
}