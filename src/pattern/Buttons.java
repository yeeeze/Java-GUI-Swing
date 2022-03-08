package pattern;

import javax.swing.*;

public class Buttons {
    JButton[] toolbuttons = new JButton[5];

    public Buttons() {
        for(int i=0; i<toolbuttons.length; i++) {
            toolbuttons[i] = new JButton("버튼"+(i+1));
        }
    }
}
