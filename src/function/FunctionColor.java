package function;

import main.GUI;

import java.awt.*;

public class FunctionColor {
    private final GUI gui;

    // COLOR CONSTANTS
    private static final Color WINDOW_WHITE = Color.WHITE;
    private static final Color TEXT_AREA_WHITE = Color.WHITE;
    private static final Color TEXT_BLACK = Color.BLACK;
    private static final Color CARET_BLACK = Color.BLACK;

    private static final Color WINDOW_GREEN = Color.GREEN;
    private static final Color TEXT_AREA_GREEN = new Color(50, 67, 51);
    private static final Color TEXT_GREEN = new Color(232, 255, 234);
    private static final Color CARET_GREEN = new Color(184, 222, 187);

    private static final Color WINDOW_BLACK = Color.BLACK;
    private static final Color TEXT_AREA_BLACK = new Color(44, 44, 44);
    private static final Color TEXT_GRAY = new Color(232, 232, 232);
    private static final Color CARET_GRAY = new Color(165, 165, 165);

    public FunctionColor(GUI gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {

        switch(color) {
            case GUI.WHITE_TEXT:
                gui.getWindow().getContentPane().setBackground(WINDOW_WHITE);
                gui.getTextArea().setBackground(TEXT_AREA_WHITE);
                gui.getTextArea().setForeground(TEXT_BLACK);
                gui.getTextArea().setCaretColor(CARET_BLACK);
                break;
            case GUI.GREEN_TEXT:
                gui.getWindow().getContentPane().setBackground(WINDOW_GREEN);
                gui.getTextArea().setBackground(TEXT_AREA_GREEN);
                gui.getTextArea().setForeground(TEXT_GREEN);
                gui.getTextArea().setCaretColor(CARET_GREEN);
                break;
            case GUI.BLACK_TEXT:
                gui.getWindow().getContentPane().setBackground(WINDOW_BLACK);
                gui.getTextArea().setBackground(TEXT_AREA_BLACK);
                gui.getTextArea().setForeground(TEXT_GRAY);
                gui.getTextArea().setCaretColor(CARET_GRAY);
                break;
        }
    }
}
