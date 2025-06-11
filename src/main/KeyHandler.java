package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class for reading keyboard shortcuts
 */
public class KeyHandler implements KeyListener {
    private final GUI gui;

    public KeyHandler(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Not using */ }
    @Override
    public void keyReleased(KeyEvent e) { /* Not using */ }

    @Override
    public void keyPressed(KeyEvent e) {
        // Ctrl+S - save, Ctrl+Shift+S - save as
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {

            if(e.isShiftDown()) {
                gui.getFunctionFile().saveAsFile();
            }else {
                gui.getFunctionFile().saveFile();
            }
        }

        // Alt+F - open file menu
        if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F) {
            gui.getMenuFile().doClick();
        }

        // Ctrl+Z - undo, Ctrl+Shift+Z - redo
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {

            if(e.isShiftDown()) {
                gui.getFunctionEdit().redo();
            }else {
                gui.getFunctionEdit().undo();
            }
        }

        // Ctrl+F - find & replace
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {
            gui.getFunctionSearch().showSearchDialog();
        }
    }
}
