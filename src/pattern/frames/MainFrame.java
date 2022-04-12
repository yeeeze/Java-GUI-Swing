package pattern.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    private MenuBar menuBar;
    private ToolBar toolBar;
    private DrawingPanel drawingPanel;

    public MainFrame() {
        // attributes
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // components
        BorderLayout layoutManager = new BorderLayout();
        this.setLayout(layoutManager);

        this.menuBar = new MenuBar();
        this.setJMenuBar(this.menuBar);

        this.toolBar = new ToolBar();
        this.add(this.toolBar, BorderLayout.NORTH);

        this.drawingPanel = new DrawingPanel(this.toolBar);
        this.add(this.drawingPanel, BorderLayout.CENTER);

        // association (자식과 자식과의 관계)
        this.toolBar.associate(this.drawingPanel);
        setTitle("yeji window");
    }
}
