package pattern.frames;

import javax.swing.*;

import pattern.global.Constants.ETools;

import java.awt.Image;
import java.awt.event.*;

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
        	ImageIcon icon = new ImageIcon("icon/" + eTool.name() + ".png");
        	Image img = icon.getImage();
        	Image updateImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        	icon = new ImageIcon(updateImg);
        	
            JButton toolButton = new JButton(icon);
            toolButton.setActionCommand(eTool.name());
            toolButton.setToolTipText(eTool.getLabel());
            toolButton.addActionListener(actionHandler);
            this.add(toolButton);
            buttonGroup.add(toolButton);
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        JButton defaultButton = (JButton) this.getComponent(ETools.eSelection.ordinal());
        defaultButton.doClick();
    }

    public void initialize() {
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == ETools.eColorMode.name()) {
                drawingPanel.changeColorMode();
            } else {
                drawingPanel.setSelectedTool(ETools.valueOf(e.getActionCommand()));
            }
        }
    }
}