package pattern;

import javax.swing.*;

public class FileMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    private FileMenuItems fileMenuItems;

    public FileMenu(String s) {
        super(s);

        this.fileMenuItems = new FileMenuItems();
        for(JMenuItem items : fileMenuItems.getInstance()) {
            this.add(items);
        }
    }
}
