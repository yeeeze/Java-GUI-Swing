package pattern.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import pattern.frames.DrawingPanel;
import pattern.global.Constants.EFileMenu;

public class FileMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    private DrawingPanel drawingPanel;
    
    public FileMenu(String title) {
        super(title);

        ActionHandler actionHandler = new ActionHandler();
        for(EFileMenu eMenuItem: EFileMenu.values()) {
        	JMenuItem menuItem = new JMenuItem(eMenuItem.getLabel());
        	menuItem.addActionListener(actionHandler);
        	menuItem.setActionCommand(eMenuItem.name());
        	this.add(menuItem);
        }
    }
    
    public void associate(DrawingPanel drawingPanel) {
    	this.drawingPanel = drawingPanel;
    }
    
    private void store() {
    	try {
    		FileOutputStream fileOutputStream = new FileOutputStream("test");
    		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    		objectOutputStream.writeObject(this.drawingPanel.getShapes());
    		objectOutputStream.close();
    	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void load() {
		try {
			FileInputStream fileInputStream = new FileInputStream("test");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object object = objectInputStream.readObject();
			this.drawingPanel.setShapes(object);
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenu.eOpen.name())) {
				load();
			}
			else if(e.getActionCommand().equals(EFileMenu.eSave.name())) {
				store();
			}
		}
    	
    }
}
