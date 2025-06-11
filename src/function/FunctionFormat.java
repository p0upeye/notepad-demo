package function;

import constants.AppConstants;
import main.GUI;
import main.Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for managing text formatting.
 */
public class FunctionFormat {
    private final GUI gui;
    private Font arial, maruMonica, playwrite;
    private String selectedFont;

    // Getters & setters
    public void setSelectedFont(String selectedFont) {
        this.selectedFont = selectedFont;
    }
    public String getSelectedFont() {
        return selectedFont;
    }

    public FunctionFormat(GUI gui) {
        this.gui = gui;

        try {
            InputStream ISMaruMonica = Main.class.getResourceAsStream(AppConstants.Fonts.MARU_MONICA_PATH);
            InputStream ISPlaywrite = Main.class.getResourceAsStream(AppConstants.Fonts.PLAYWRITE_PATH);

            if(ISMaruMonica != null) maruMonica = Font.createFont(Font.TRUETYPE_FONT, ISMaruMonica);
            if(ISPlaywrite != null) playwrite = Font.createFont(Font.TRUETYPE_FONT, ISPlaywrite);
        } catch(FontFormatException | IOException e) {
            System.err.println(AppConstants.Messages.FONT_LOAD_ERROR);
        }
    }

    public void wordWrap() {

        if(!gui.isWordWrapOn()) {
            gui.setWordWrapOn(true);
            gui.getTextArea().setLineWrap(true);
            gui.getTextArea().setWrapStyleWord(true);
            gui.getItemWrap().setText(AppConstants.MenuText.WORD_WRAP_ON);
        }else {
            gui.setWordWrapOn(false);
            gui.getTextArea().setLineWrap(false);
            gui.getTextArea().setWrapStyleWord(false);
            gui.getItemWrap().setText(AppConstants.MenuText.WORD_WRAP_OFF);
        }
    }

    public void createFont(int fontSize) {
        arial = new Font(AppConstants.Fonts.ARIAL, Font.PLAIN, fontSize);
        maruMonica = maruMonica.deriveFont(Font.PLAIN, fontSize);
        playwrite = playwrite.deriveFont(Font.PLAIN, fontSize);

        setFont(getSelectedFont());
    }

    public void setFont(String font) {
        setSelectedFont(font);

        switch(getSelectedFont()) {
            case AppConstants.Fonts.ARIAL:  gui.getTextArea().setFont(arial);  break;
            case AppConstants.Fonts.MARU_MONICA:  gui.getTextArea().setFont(maruMonica);  break;
            case AppConstants.Fonts.PLAYWRITE:  gui.getTextArea().setFont(playwrite);  break;
            // ...

            default:  System.out.println(AppConstants.Messages.INVALID_FONT + getSelectedFont());  break;
        }
    }
}
