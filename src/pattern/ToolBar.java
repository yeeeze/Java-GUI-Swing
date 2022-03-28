package pattern;

import javax.swing.*;

import pattern.DrawingPanel.EShapes;

import java.awt.*;
import java.awt.event.*;

public class ToolBar extends JToolBar implements ActionListener {
    private static final long serialVersionUTD = 1L;

    private JRadioButton rectangleTool;
    private JRadioButton ovalTool;
    private JRadioButton lineTool;
    private JRadioButton polygonTool;
    private JButton colorButton;
    private ButtonGroup buttonGroup;

    public ToolBar() {
        this.buttonGroup = new ButtonGroup();

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

    public EShapes selectedButton() {
    	if(this.buttonGroup.getSelection().equals(this.rectangleTool.getModel())) {
            return EShapes.eRectangle;
        }
        else if(this.buttonGroup.getSelection().equals(this.ovalTool.getModel())) {
            return EShapes.eOval;
        }
        else if(this.buttonGroup.getSelection().equals(this.lineTool.getModel())) {
            return EShapes.eLine;
        }
        else {
            return EShapes.ePolygon;
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == colorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "색상 고르기", Color.black);
        }
    }
}
