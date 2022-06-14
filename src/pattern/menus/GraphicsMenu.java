package pattern.menus;

import pattern.colorChange.ColorChange;
import pattern.frames.DrawingPanel;
import pattern.global.Constants;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pattern.global.Constants.*;

public class GraphicsMenu extends JMenu {

    private DrawingPanel drawingPanel;

    public GraphicsMenu(String title) {
        super(title);

        ActionHandler actionHandler = new ActionHandler();

        for (EGrapicsMenu eGrapicsMenu : EGrapicsMenu.values()) {
            if (eGrapicsMenu.getLabel() == "라인 두께") {
                JMenu jMenu = new JMenu(eGrapicsMenu.getLabel());
                this.add(jMenu);

                for (int i = 1; i < 7; i++) {
                    JMenuItem jMenuItem = new JMenuItem(String.valueOf(i));
                    jMenu.add(jMenuItem);
                    jMenuItem.setActionCommand(String.valueOf(i));
                    jMenuItem.addActionListener(actionHandler);
                }
            } else {
                JMenuItem jMenuItem = new JMenuItem(eGrapicsMenu.getLabel());
                this.add(jMenuItem);
                jMenuItem.setActionCommand(eGrapicsMenu.name());
                jMenuItem.addActionListener(actionHandler);
            }
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void line(int lineThickness) {
        this.drawingPanel.line(lineThickness);
    }

    public void lineColor() {
        JColorChooser jColorChooser = new JColorChooser();
        Color selectedColor = jColorChooser.showDialog(null, "Color", Color.black);
        if (selectedColor != null) {
            this.drawingPanel.changeColor(selectedColor);
        }
    }

    public void fill() {
        this.drawingPanel.fill();
    }

    class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().length() == 1) {
                line(Integer.parseInt(e.getActionCommand()));
            } else if (e.getActionCommand() == EGrapicsMenu.eLineColor.name()) {
                lineColor();
            } else if (e.getActionCommand() == EGrapicsMenu.eFill.name()) {
                fill();
            }
        }
    }
}
