package pattern;

import javax.swing.*;

public class EditMenuItems {
    private JMenuItem[] items;

    public EditMenuItems() {
        this.items = new JMenuItem[7];

        this.items[0] = new JMenuItem("Cut");
        this.items[1] = new JMenuItem("Paste");
        this.items[2] = new JMenuItem("Copy");
        this.items[3] = new JMenuItem("Group");
        this.items[4] = new JMenuItem("ungroup");
        this.items[5] = new JMenuItem("Undo");
        this.items[6] = new JMenuItem("Redo");
    }

    public JMenuItem[] getInstance() {
        return this.items;
    }
}
