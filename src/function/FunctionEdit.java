package function;

import main.GUI;

public class FunctionEdit {
    private final GUI gui;

    public FunctionEdit(GUI gui) {
        this.gui = gui;
    }

    public void undo() {
        if(gui.getUndoManager().canUndo()) {
            gui.getUndoManager().undo();
        }
    }
    public void redo() {
        if(gui.getUndoManager().canRedo()) {
            gui.getUndoManager().redo();
        }
    }
}
