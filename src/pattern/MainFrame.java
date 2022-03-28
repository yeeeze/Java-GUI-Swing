package pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame {
    private static final long serialVersionUTD = 1L;

    private MenuBar menuBar;
    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    public MainFrame() {
        this.setSize(400,600);
        BorderLayout layoutManager = new BorderLayout();
        this.setLayout(layoutManager);

        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);

        this.toolBar = new ToolBar();
        this.add(this.toolBar, layoutManager.NORTH);

        this.drawingPanel = new DrawingPanel(toolBar);
        this.add(this.drawingPanel, layoutManager.CENTER);

        setTitle("my window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
