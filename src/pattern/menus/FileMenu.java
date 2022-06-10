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

public class FileMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    //private String openFilePath = null;
    private File file;
    
    private DrawingPanel drawingPanel;
    
    public FileMenu(String title) {
        super(title);

		this.file = null;

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

	private void load(File file) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
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

	private void store(File file) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this.drawingPanel.getShapes());
			objectOutputStream.close();
			this.drawingPanel.setUpdated(false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkSave() {
		boolean bCancel = false;
		int reply = JOptionPane.NO_OPTION;
		if(this.drawingPanel.isUpdated()) {
			reply = JOptionPane.showConfirmDialog(this.drawingPanel, "변경내용을 저장할까요? 저장하지 않으면 변경 사항이 유실됩니다.");
			if(reply == JOptionPane.CANCEL_OPTION) {
				bCancel = true;
			}
		}
		if(!bCancel) {
			if(reply == JOptionPane.OK_OPTION) {
				bCancel = this.save();
			}
		}
		return bCancel;
	}

	public void newPanel() {
		boolean bCancel = this.checkSave();
		if(!bCancel) {
			this.drawingPanel.initialize();
			this.file = null;
		}
	}

	public void open() {
		boolean bCancel = this.checkSave();
		if(!bCancel) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(this.drawingPanel);

			if(result == JFileChooser.APPROVE_OPTION) {
				this.file = fileChooser.getSelectedFile();
				this.load(this.file);
			}
		}
	}

	public boolean save() {
		boolean bCancel = false;
		if(this.drawingPanel.isUpdated()) {
			if(this.file == null) {
				bCancel = this.saveAs();
			}
			else {
				this.store(this.file);
			}
		}
		return bCancel;
	}

	public boolean saveAs() {
		boolean bCancel = false;
		
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showSaveDialog(this.drawingPanel);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			this.file = fileChooser.getSelectedFile();
			this.store(this.file);
		}
		else {
			bCancel = true;
		}
		return bCancel;
	}
    
	public void quit() {
		boolean bCancel = this.checkSave();
		if(!bCancel) {
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
				open();
			}
			else if(e.getActionCommand().equals(EFileMenu.eSave.name())) {
				save();
			}
			else if(e.getActionCommand().equals(EFileMenu.eSaveAs.name())) {
				saveAs();
			}
			else if(e.getActionCommand().equals(EFileMenu.eNew.name())) {
				newPanel();
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
