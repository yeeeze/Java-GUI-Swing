package pattern.frames;

import javax.swing.*;

import pattern.frames.DrawingPanel.ETools;

import java.awt.*;
import java.awt.event.*;

public class ToolBar extends JToolBar {
    private static final long serialVersionUTD = 1L;

    private JRadioButton rectangleTool;
    private JRadioButton ovalTool;
    private JRadioButton lineTool;
    private JRadioButton polygonTool;
    private JButton colorButton;

    private DrawingPanel drawingPanel;

    public ToolBar() {
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

        this.colorButton = new JButton("색상 선택");
        //this.colorButton.addActionListener(this);
        this.add(colorButton);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == colorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "색상 고르기", Color.black);
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == rectangleTool) {
                drawingPanel.setSelectedTool(ETools.eRectangle);
            } else if(e.getSource() == ovalTool) {
                drawingPanel.setSelectedTool(ETools.eOval);
            } else if(e.getSource() == lineTool) {
                drawingPanel.setSelectedTool(ETools.eLine);
            } else if(e.getSource() == polygonTool){
                drawingPanel.setSelectedTool(ETools.ePolygon);
            }
        }
    }
}
