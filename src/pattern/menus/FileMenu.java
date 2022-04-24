package pattern.menus;

import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import pattern.frames.DrawingPanel;
import pattern.global.Constants.EFileMenu;
import pattern.global.Constants.SaveState;

public class FileMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    //private String openFilePath = null;
    private File openFile = null;
    private JFrame dialog;
   
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
    

	public void create() {
		this.drawingPanel.remove();
	}
    
    private void store() {    	
    	if(this.openFile == null) {
    		storeAs();
    	}
    	else {
        	try {
        		FileOutputStream fileOutputStream = new FileOutputStream(this.openFile);
        		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        		objectOutputStream.writeObject(this.drawingPanel.getShapes());
        		objectOutputStream.close();
        	} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    

	public void storeAs() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showSaveDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {			
				FileOutputStream fileOutputStream = new FileOutputStream(selectedFile);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(this.drawingPanel.getShapes());			
				objectOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
    private void load() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			this.openFile = fileChooser.getSelectedFile();
			try {
				FileInputStream fileInputStream = new FileInputStream(openFile);
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

    }
    
	public void quit() {
		if(this.drawingPanel.getSaveState() == SaveState.exist) {
			this.dialog = new JFrame();
			int result = JOptionPane.showConfirmDialog(dialog, "변경 사항을 저장하시겠습니까? 저장하지 않으면 변경 사항이 유실됩니다.");
			
			if(result == JOptionPane.CANCEL_OPTION) {
				this.dialog.setVisible(false);
			}
			else if(result == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			else if(result == JOptionPane.YES_OPTION) {
				storeAs();
			}
		}		
		else {
			System.exit(0);
		}
	}
	
	public void print() {
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(drawingPanel);
		
		if(!printJob.printDialog()) {
			return;
		}
		try {
			printJob.print();
		} catch (PrinterException e) {
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
				drawingPanel.setSaveState(SaveState.done);
			}
			else if(e.getActionCommand().equals(EFileMenu.eSaveAs.name())) {
				storeAs();
				drawingPanel.setSaveState(SaveState.done);
			}
			else if(e.getActionCommand().equals(EFileMenu.eNew.name())) {
				create();
			}
			else if(e.getActionCommand().equals(EFileMenu.ePrint.name()) ) {
				print();
			}
			else if(e.getActionCommand().equals(EFileMenu.eQuit.name())) {
				quit();
			}
		}
    	
    }
}
