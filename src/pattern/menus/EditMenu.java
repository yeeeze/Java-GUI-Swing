package pattern.menus;

import pattern.frames.DrawingPanel;
import pattern.global.Constants;
import pattern.shapes.TShape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pattern.global.Constants.*;

public class EditMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    private DrawingPanel drawingPanel;
    private TShape removeShape;
    private TShape copyShape;

    public EditMenu(String s) {
        super(s);

        ActionHandler actionHandler = new ActionHandler();

        for (EEditMenu eEditMenu : EEditMenu.values()) {
            JMenuItem jMenuItem = new JMenuItem(eEditMenu.getLabel());
            jMenuItem.addActionListener(actionHandler);
            jMenuItem.setActionCommand(eEditMenu.name());
            this.add(jMenuItem);
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void undo() {
        this.removeShape = this.drawingPanel.undo();
    }

    public void redo() {
        this.drawingPanel.redoPaste(this.removeShape);
    }

    public void copy() {
        this.copyShape = this.drawingPanel.copy();
    }

    public void paste() {
        this.drawingPanel.redoPaste(this.copyShape);
    }

    public void cut() {
        this.copyShape = this.drawingPanel.cut();
    }

    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(EEditMenu.eUndo.name())) {
                undo();
            } else if (e.getActionCommand().equals(EEditMenu.eRedo.name())) {
                redo();
            } else if (e.getActionCommand().equals(EEditMenu.eCopy.name())) {
                copy();
            } else if (e.getActionCommand().equals(EEditMenu.ePaste.name())) {
                paste();
            } else if (e.getActionCommand().equals(EEditMenu.eCut.name())) {
                cut();
            }
        }
    }


}
