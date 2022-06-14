package pattern.menus;

import pattern.frames.DrawingPanel;
import pattern.global.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pattern.global.Constants.*;

public class EditMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    DrawingPanel drawingPanel;

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
        this.drawingPanel.undo();
    }

    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(EEditMenu.eUndo.name())) {
                undo();
            }
        }
    }


}
