package pattern;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUTD = 1L;

    private MenuBar menuBar;
    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    public MainFrame() {
        this.setSize(400,600);
        this.setLayout(new FlowLayout());

        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);

        this.toolBar = new ToolBar();
        this.add(this.toolBar);

        this.drawingPanel = new DrawingPanel();
        this.add(this.drawingPanel);

        setTitle("my window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
