package function;

import main.GUI;

/**
 * Class for edit operations (undo/redo).
 */
public class FunctionEdit {
    private final GUI gui;

    public FunctionEdit(GUI gui) {
        this.gui = gui;
    }

    /**
     * Undo the last action
     */
    public void undo() {
        if(gui.getUndoManager().canUndo()) {
            gui.getUndoManager().undo();
        }
    }

    /**
     * Redo the previously undone action
     */
    public void redo() {
        if(gui.getUndoManager().canRedo()) {
            gui.getUndoManager().redo();
        }
    }
}
