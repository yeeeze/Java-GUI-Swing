package pattern.menus;

import pattern.global.Constants;

import javax.swing.*;

import static pattern.global.Constants.*;

public class GraphicsMenu extends JMenu {

    public GraphicsMenu(String title) {
        super(title);

        for (EGrapicsMenu eGrapicsMenu : EGrapicsMenu.values()) {
            JMenuItem jMenuItem = new JMenuItem(eGrapicsMenu.getLabel());
            this.add(jMenuItem);
        }
    }
}
