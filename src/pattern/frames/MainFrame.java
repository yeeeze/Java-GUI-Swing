package pattern.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // attributes
    private static final long serialVersionUTD = 1L;

    // components
    private final MenuBar menuBar;
    private final ToolBar toolBar;
    private final DrawingPanel drawingPanel;

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

        this.drawingPanel = new DrawingPanel();
        this.add(this.drawingPanel, BorderLayout.CENTER);

        // association (자식과 자식과의 관계)
        this.menuBar.associate(this.drawingPanel);
        this.toolBar.associate(this.drawingPanel);
        setTitle("yeji window");
    }

    public void initialize() {
        this.menuBar.initialize();
        this.toolBar.initialize();
        this.drawingPanel.initialize();
    }
}
