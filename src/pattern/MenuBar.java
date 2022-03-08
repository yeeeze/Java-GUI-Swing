package pattern;

import javax.swing.*;

public class MenuBar extends JMenuBar{
    JMenu fileMenu = new JMenu("파일");
    JMenuItem save = new JMenuItem("저장");
    JMenuItem open = new JMenuItem("열기");

    JMenu editMenu = new JMenu("수정");
    JMenuItem copy = new JMenuItem("복사");
    JMenuItem paste = new JMenuItem("붙여넣기");

    public MenuBar(){
        fileMenu.add(save);
        fileMenu.add(open);
        editMenu.add(copy);
        editMenu.add(paste);
        this.add(fileMenu);
        this.add(editMenu);
    }
}
