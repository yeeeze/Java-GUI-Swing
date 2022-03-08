package pattern;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    MenuBar menuBar = new MenuBar();
    ToolBar toolBar = new ToolBar();
    Panel panel = new Panel();

    public Window() {
        setTitle("my window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);

        setJMenuBar(menuBar);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}
