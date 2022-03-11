package pattern;

import javax.swing.*;

public class MenuBar extends JMenuBar{
    private static final long serialVersionUTD = 1L;

    private FileMenu fileMenu;
    private EditMenu editMenu;

    public MenuBar(){
        this.fileMenu = new FileMenu("파일");
        this.add(this.fileMenu);

        this.editMenu = new EditMenu("Edit");
        this.add(this.editMenu);
    }
}
