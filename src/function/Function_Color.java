package function;

import main.GUI;

import java.awt.*;

public class Function_Color {
    GUI gui;

    public Function_Color(GUI gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {

        switch(color) {
            case "White":
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setForeground(Color.black);
                break;
            case "Green":
                gui.window.getContentPane().setBackground(Color.green);
                gui.textArea.setBackground(new Color(50, 67, 51, 255));
                gui.textArea.setForeground(new Color(232, 255, 234, 255));
                gui.textArea.setCaretColor(new Color(184, 222, 187, 255));
                break;
            case "Black":
                gui.window.getContentPane().setBackground(Color.black);
                gui.textArea.setBackground(new Color(44, 44, 44));
                gui.textArea.setForeground(new Color(232, 232, 232));
                gui.textArea.setCaretColor(new Color(165, 165, 165));
                break;
        }
    }
}
