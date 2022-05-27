package pattern.menus;

import pattern.frames.DrawingPanel;
import pattern.global.Constants;

import javax.swing.*;

import static pattern.global.Constants.*;

public class GraphicsMenu extends JMenu {

    private DrawingPanel drawingPanel;

    public GraphicsMenu(String title) {
        super(title);

        for (EGrapicsMenu eGrapicsMenu : EGrapicsMenu.values()) {
            JMenuItem jMenuItem = new JMenuItem(eGrapicsMenu.getLabel());
            this.add(jMenuItem);
        }
    }

    public void associate(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }
}
