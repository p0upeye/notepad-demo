package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GUI gui;

    public KeyHandler(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // SAVE / SAVE AS
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {

            if(e.isShiftDown() == true) {  // shift + ctrl + s
                gui.fFile.saveAsFile();
            }else {                        // ctrl + s
                gui.fFile.saveFile();
            }
        }
        // FILE MENU
        if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F) {  // alt + f
            gui.menuFile.doClick();
        }
        // UNDO & REDO
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {

            if(e.isShiftDown() == true) {  // shift + ctrl + z
                gui.fEdit.redo();
            }else {                        // ctrl + z
                gui.fEdit.undo();
            }
        }
        // FIND & REPLACE
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {  // ctrl + f
            gui.fSearch.showSearchDialog();
        }
    }
}
