package pattern.frames;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.Vector;

public class PanelList extends JList {

    DefaultListModel model = new DefaultListModel();
    int count = 1;

    private SplitPanel splitPanel;

    public PanelList() {
        model.addElement(String.valueOf(count));

        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void initialize() {
        super.setModel(model);
    }

    public void associate(SplitPanel splitPanel) {
        this.splitPanel = splitPanel;
    }

    public void addPanel() {
        model.addElement(String.valueOf(count += 1));
        this.initialize();
//        this.splitPanel.addDrawingPanelList(count);
    }

    public void changePanel(String listIndex) {
        int index =  Integer.parseInt(listIndex) - 1;
        this.splitPanel.changePanel(index);
    }

    public void valueChanged(ListSelectionEvent e) {
        String selectedValue = (String) this.getSelectedValue();

        this.changePanel(selectedValue);
    }
}
