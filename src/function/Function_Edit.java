package function;

import main.GUI;

public class Function_Edit {
    GUI gui;

    public Function_Edit(GUI gui) {
        this.gui = gui;
    }

    public void undo() {
        if(gui.uManager.canUndo() == true) {
            gui.uManager.undo();
        }
    }
    public void redo() {
        if(gui.uManager.canRedo() == true) {
            gui.uManager.redo();
        }
    }
}
