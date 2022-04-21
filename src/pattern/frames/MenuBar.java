package pattern.frames;

import pattern.menus.EditMenu;
import pattern.menus.FileMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar{
    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    private FileMenu fileMenu;
    private EditMenu editMenu;
    
    private DrawingPanel drawingPanel;

    public MenuBar(){
        // components
        this.fileMenu = new FileMenu("파일");
        this.add(this.fileMenu);

        this.editMenu = new EditMenu("Edit");
        this.add(this.editMenu);
    }
    
    public void associate(DrawingPanel drawingPanel) {
    	this.drawingPanel = drawingPanel;
    	this.fileMenu.associate(this.drawingPanel);
    }
}
