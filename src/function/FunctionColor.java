package function;

import constants.AppConstants;
import main.GUI;

import java.awt.*;

/**
 * Class responsible for theme colors
 */
public class FunctionColor {
    private final GUI gui;

    public FunctionColor(GUI gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {

        switch(color) {
            case AppConstants.Colors.WHITE:
                changePalette(
                        AppConstants.Colors.WINDOW_WHITE,
                        AppConstants.Colors.TEXT_AREA_WHITE,
                        AppConstants.Colors.TEXT_BLACK,
                        AppConstants.Colors.CARET_BLACK);
                break;
            case AppConstants.Colors.GREEN:
                changePalette(
                        AppConstants.Colors.WINDOW_GREEN,
                        AppConstants.Colors.TEXT_AREA_GREEN,
                        AppConstants.Colors.TEXT_GREEN,
                        AppConstants.Colors.CARET_GREEN);
                break;
            case AppConstants.Colors.BLACK:
                changePalette(
                        AppConstants.Colors.WINDOW_BLACK,
                        AppConstants.Colors.TEXT_AREA_BLACK,
                        AppConstants.Colors.TEXT_GRAY,
                        AppConstants.Colors.CARET_GRAY);
                break;
        }
    }

    /**
     * Method that simplifies changing colors
     */
    public void changePalette(Color window, Color textArea, Color text, Color caret) {
        gui.getWindow().getContentPane().setBackground(window);
        gui.getTextArea().setBackground(textArea);
        gui.getTextArea().setForeground(text);
        gui.getTextArea().setCaretColor(caret);
    }
}
