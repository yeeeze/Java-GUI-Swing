package pattern.frames;

import pattern.menus.EditMenu;
import pattern.menus.FileMenu;
import pattern.menus.GraphicsMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar{
    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    private final FileMenu fileMenu;
    private final EditMenu editMenu;
    private final GraphicsMenu graphicsMenu;
    
    private DrawingPanel drawingPanel;

    public MenuBar(){
        // components
        this.fileMenu = new FileMenu("파일");
        this.add(this.fileMenu);

        this.editMenu = new EditMenu("Edit");
        this.add(this.editMenu);

        this.graphicsMenu = new GraphicsMenu("그래픽 속성");
        this.add(this.graphicsMenu);
    }
    
    public void associate(DrawingPanel drawingPanel) {
    	this.drawingPanel = drawingPanel;
    	this.fileMenu.associate(this.drawingPanel);
        this.graphicsMenu.associate(this.drawingPanel);
    }
}
