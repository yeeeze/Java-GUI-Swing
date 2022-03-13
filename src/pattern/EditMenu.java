package pattern;

import javax.swing.*;

public class EditMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    private EditMenuItems editMenuItems;

    public EditMenu(String s) {
        super(s);

        this.editMenuItems = new EditMenuItems();

        for(JMenuItem items : this.editMenuItems.getInstance()) {
            this.add(items);
        }
    }
}
