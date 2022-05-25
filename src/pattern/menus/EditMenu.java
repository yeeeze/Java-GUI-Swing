package pattern.menus;

import javax.swing.*;

public class EditMenu extends JMenu {
    private static final long serialVersionUTD = 1L;

    private final JMenuItem undoItem;
    private final JMenuItem redoItem;
    private final JMenuItem cutItem;
    private final JMenuItem copyItem;
    private final JMenuItem pasteItem;
    private final JMenuItem groupItem;
    private final JMenuItem ungroupItem;

    public EditMenu(String s) {
        super(s);

        this.undoItem = new JMenuItem("undo");
        this.add(this.undoItem);

        this.redoItem = new JMenuItem("redo");
        this.add(this.redoItem);

        this.cutItem = new JMenuItem("cut");
        this.add(this.cutItem);

        this.copyItem = new JMenuItem("copy");
        this.add(this.copyItem);

        this.pasteItem = new JMenuItem("paste");
        this.add(this.pasteItem);

        this.groupItem = new JMenuItem("group");
        this.add(this.groupItem);

        this.ungroupItem = new JMenuItem("ungroup");
        this.add(this.ungroupItem);
        }
    }
