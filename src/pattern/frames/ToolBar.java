package pattern.frames;

import javax.swing.*;

import pattern.global.Constants.ETools;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ToolBar extends JToolBar {
    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    // associations
    private DrawingPanel drawingPanel;

    public ToolBar() {
        // components
        ButtonGroup buttonGroup = new ButtonGroup();
        ActionHandler actionHandler = new ActionHandler();

        for(ETools eTool: ETools.values()) {
            JRadioButton toolButton = new JRadioButton(eTool.getLable());
            toolButton.setActionCommand(eTool.name());
            toolButton.addActionListener(actionHandler);
            this.add(toolButton);
            buttonGroup.add(toolButton);
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        JRadioButton defaultButton = (JRadioButton) this.getComponent(ETools.eRectangle.ordinal());
        defaultButton.doClick();
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setSelectedTool(ETools.valueOf(e.getActionCommand()));
        }
    }
}