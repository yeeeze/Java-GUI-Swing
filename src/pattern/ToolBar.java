package pattern;

import javax.swing.*;

public class ToolBar extends JToolBar {
    private static final long serialVersionUTD = 1L;

    private JRadioButton rectangleTool;
    private JRadioButton ovalTool;
    private JRadioButton lineTool;
    private JRadioButton polygonTool;

    public ToolBar() {
        ButtonGroup buttonGroup = new ButtonGroup();

        this.rectangleTool = new JRadioButton("rectangle");
        this.add(this.rectangleTool);
        buttonGroup.add(this.rectangleTool);

        this.ovalTool = new JRadioButton("oval");
        this.add(this.ovalTool);
        buttonGroup.add(this.ovalTool);

        this.lineTool = new JRadioButton("line");
        this.add(this.lineTool);
        buttonGroup.add(this.lineTool);

        this.polygonTool = new JRadioButton("polygon");
        this.add(this.polygonTool);
        buttonGroup.add(this.polygonTool);
    }
}
