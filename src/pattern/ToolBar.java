package pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JToolBar implements ActionListener {
    private static final long serialVersionUTD = 1L;

    private JRadioButton rectangleTool;
    private JRadioButton ovalTool;
    private JRadioButton lineTool;
    private JRadioButton polygonTool;
    private JButton colorButton;

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

        this.colorButton = new JButton("색상 선택");
        this.colorButton.addActionListener(this);
        this.add(colorButton);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == colorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "색상 고르기", Color.black);
        }
    }
}
