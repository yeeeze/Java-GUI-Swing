package pattern.frames;

import javax.swing.*;

import pattern.global.Constants;
import pattern.global.Constants.ETools;
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

        for(ETools eTool: ETools.values()) {
            // 버튼의 갯수를 어떻게 알지?? 반복문 횟수말이야
        }
        this.rectangleTool = new JRadioButton("rectangle");
        this.rectangleTool.setActionCommand(ETools.eRectangle.name());
        this.rectangleTool.addActionListener(actionHandler);
        this.add(this.rectangleTool);
        buttonGroup.add(this.rectangleTool);

        this.ovalTool = new JRadioButton("oval");
        this.ovalTool.setActionCommand(ETools.eOval.name());
        this.ovalTool.addActionListener(actionHandler);
        this.add(this.ovalTool);
        buttonGroup.add(this.ovalTool);

        this.lineTool = new JRadioButton("line");
        this.lineTool.setActionCommand(ETools.eLine.name());
        this.lineTool.addActionListener(actionHandler);
        this.add(this.lineTool);
        buttonGroup.add(this.lineTool);

        this.polygonTool = new JRadioButton("polygon");
        this.polygonTool.setActionCommand(ETools.ePolygon.name());
        this.polygonTool.addActionListener(actionHandler);
        this.add(this.polygonTool);
        buttonGroup.add(this.polygonTool);
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.rectangleTool.doClick();
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setSelectedTool(ETools.valueOf(e.getActionCommand()));
        }
    }
}