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
    private List<JRadioButton> buttonList = new ArrayList<>();

    // associations
    private DrawingPanel drawingPanel;

    public ToolBar() {
        // components
        ButtonGroup buttonGroup = new ButtonGroup();
        ActionHandler actionHandler = new ActionHandler();

        for(ETools eTool: ETools.values()) {
            buttonList.add(new JRadioButton(eTool.getButtonName()));
            this.buttonList.get(eTool.ordinal()).setActionCommand(eTool.name());
            this.buttonList.get(eTool.ordinal()).addActionListener(actionHandler);
            this.add(this.buttonList.get(eTool.ordinal()));
            buttonGroup.add(this.buttonList.get(eTool.ordinal()));
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        this.buttonList.get(ETools.eRectangle.ordinal()).doClick();
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setSelectedTool(ETools.valueOf(e.getActionCommand()));
        }
    }
}