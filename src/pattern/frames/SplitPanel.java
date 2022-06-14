package pattern.frames;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SplitPanel extends JSplitPane {

    private PanelList panelList;
    private List<DrawingPanel> drawingPanelList;

    public SplitPanel() {
        super(JSplitPane.HORIZONTAL_SPLIT);
        super.setDividerLocation(50);

        this.panelList = new PanelList();
        this.panelList.associate(this);

        this.drawingPanelList = new ArrayList<>();
        this.drawingPanelList.add(new DrawingPanel());

        this.setLeftComponent(this.panelList);
        this.setRightComponent(this.drawingPanelList.get(0));
    }

    public void initialize() {
        this.drawingPanelList.get(0).initialize();
        this.panelList.initialize();
    }

    public PanelList getPanelList() {
        return panelList;
    }

    public void addDrawingPanelList(int index) {
        DrawingPanel newPanel = new DrawingPanel();
        this.drawingPanelList.add(newPanel);
        newPanel.initialize();
        this.setRightComponent(this.drawingPanelList.get(this.drawingPanelList.size() - 1));
    }

    public void changePanel(int index) {
        this.setRightComponent(this.drawingPanelList.get(index));
    }

    public DrawingPanel getDrawingPanel() {
        return this.drawingPanelList.get(0);
    }
}
