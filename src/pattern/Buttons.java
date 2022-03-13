package pattern;

import javax.swing.*;

public class Buttons {
    JButton[] toolbuttons = new JButton[4];

    public Buttons() {
        toolbuttons[0] = new JButton("Rectange");
        toolbuttons[1] = new JButton("Oval");
        toolbuttons[2] = new JButton("Line");
        toolbuttons[3] = new JButton("Polygon");
    }
}
