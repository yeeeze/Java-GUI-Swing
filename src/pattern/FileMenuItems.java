package pattern;

import javax.swing.*;

public class FileMenuItems {

    private JMenuItem[] items;

    public FileMenuItems() {
        this.items = new JMenuItem[7];

        this.items[0] = new JMenuItem("New");
        this.items[1] = new JMenuItem("Open");
        this.items[2] = new JMenuItem("Close");
        this.items[3] = new JMenuItem("Save");
        this.items[4] = new JMenuItem("saveAs");
        this.items[5] = new JMenuItem("Print");
        this.items[6] = new JMenuItem("Quit");
    }

    public JMenuItem[] getInstance() {
        return this.items;
    }
}
