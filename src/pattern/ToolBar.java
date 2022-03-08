package pattern;

import javax.swing.*;

public class ToolBar extends JToolBar {
    Buttons buttons = new Buttons();

    public ToolBar() {
        this.add(buttons.toolbuttons[0]);
        this.add(buttons.toolbuttons[1]);
        this.addSeparator();
        this.add(buttons.toolbuttons[2]);
        this.add(buttons.toolbuttons[3]);
        this.addSeparator();
        this.add(buttons.toolbuttons[4]);
    }
}
