package function;

import main.GUI;
import main.Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FunctionFormat {
    private final GUI gui;
    private Font arial, maruMonica, playwrite;
    private String selectedFont;

    // CONSTANTS
    private static final String FONT_ERROR_MESSAGE = "Font not loaded";
    private static final String MARU_MONICA_FONT_PATH = "/font/x12y16pxMaruMonica.ttf";
    private static final String PLAYWRITE_FONT_PATH = "/font/PlaywriteHU-VariableFont_wght.ttf";

    // GETTERS & SETTERS
    public void setSelectedFont(String selectedFont) {
        this.selectedFont = selectedFont;
    }
    public String getSelectedFont() {
        return selectedFont;
    }

    public FunctionFormat(GUI gui) {
        this.gui = gui;

        try {
            InputStream ISMaruMonica = Main.class.getResourceAsStream(MARU_MONICA_FONT_PATH);
            InputStream ISPlaywrite = Main.class.getResourceAsStream(PLAYWRITE_FONT_PATH);

            if(ISMaruMonica != null) maruMonica = Font.createFont(Font.TRUETYPE_FONT, ISMaruMonica);
            if(ISPlaywrite != null) playwrite = Font.createFont(Font.TRUETYPE_FONT, ISPlaywrite);
        } catch(FontFormatException | IOException e) {
            System.err.println(FONT_ERROR_MESSAGE);
        }
    }

    public void wordWrap() {

        if(!gui.isWordWrapOn()) {
            gui.setWordWrapOn(true);
            gui.getTextArea().setLineWrap(true);
            gui.getTextArea().setWrapStyleWord(true);
            gui.getItemWrap().setText(GUI.WORD_WRAP_ON_TEXT);
        }else {
            gui.setWordWrapOn(false);
            gui.getTextArea().setLineWrap(false);
            gui.getTextArea().setWrapStyleWord(false);
            gui.getItemWrap().setText(GUI.WORD_WRAP_OFF_TEXT);
        }
    }
    public void createFont(int fontSize) {
        arial = new Font(GUI.ARIAL_TEXT, Font.PLAIN, fontSize);
        maruMonica = maruMonica.deriveFont(Font.PLAIN, fontSize);
        playwrite = playwrite.deriveFont(Font.PLAIN, fontSize);

        setFont(getSelectedFont());
    }
    public void setFont(String font) {
        setSelectedFont(font);

        switch(getSelectedFont()) {
            case GUI.ARIAL_TEXT:  gui.getTextArea().setFont(arial);  break;
            case GUI.MARU_MONICA_TEXT:  gui.getTextArea().setFont(maruMonica);  break;
            case GUI.PLAYWRITE_TEXT:  gui.getTextArea().setFont(playwrite);  break;
            // ...

            default:  System.out.println("Invalid font: " + getSelectedFont());  break;
        }
    }
}
