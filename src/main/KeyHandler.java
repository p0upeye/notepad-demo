package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final GUI gui;

    public KeyHandler(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // SAVE & SAVE AS
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {

            if(e.isShiftDown()) {              // shift + ctrl + s
                gui.getFunctionFile().saveAsFile();
            }else {                            // ctrl + s
                gui.getFunctionFile().saveFile();
            }
        }
        // FILE MENU
        if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F) {  // alt + f
            gui.getMenuFile().doClick();
        }
        // UNDO & REDO
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {

            if(e.isShiftDown()) {              // shift + ctrl + z
                gui.getFunctionEdit().redo();
            }else {                            // ctrl + z
                gui.getFunctionEdit().undo();
            }
        }
        // FIND & REPLACE
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {  // ctrl + f
            gui.getFunctionSearch().showSearchDialog();
        }
    }
}
