package pattern;

import javax.swing.*;

public class ToolBar extends JToolBar {
    private static final long serialVersionUTD = 1L;

    private Buttons buttons;

    public ToolBar() {
        this.buttons = new Buttons();

        this.add(buttons.toolbuttons[0]);
        this.add(buttons.toolbuttons[1]);
        this.add(buttons.toolbuttons[2]);
        this.add(buttons.toolbuttons[3]);
    }
}
