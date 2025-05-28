package function;

import main.GUI;
import main.Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Function_Format {
    GUI gui;
    private Font arial, maruMonica, playwrite;
    private String selectedFont;

    public void setSelectedFont(String selectedFont) {
        this.selectedFont = selectedFont;
    }
    public String getSelectedFont() {
        return selectedFont;
    }

    public Function_Format(GUI gui) {
        this.gui = gui;

        try {
            InputStream ISMaruMonica = Main.class.getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            InputStream ISPlaywrite = Main.class.getResourceAsStream("/font/PlaywriteHU-VariableFont_wght.ttf");

            if(ISMaruMonica != null) maruMonica = Font.createFont(Font.TRUETYPE_FONT, ISMaruMonica);
            if(ISPlaywrite != null) playwrite = Font.createFont(Font.TRUETYPE_FONT, ISPlaywrite);
        } catch(FontFormatException | IOException e) {
            System.err.println("Font not loaded");
        }
    }

    public void wordWrap() {

        if(gui.wordWrapOn == false) {
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iWrap.setText("Word Wrap: On");
        }else {
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iWrap.setText("Word Wrap: Off");
        }
    }
    public void createFont(int fontSize) {
        arial = new Font("Arial", Font.PLAIN, fontSize);
        maruMonica = maruMonica.deriveFont(Font.PLAIN, fontSize);
        playwrite = playwrite.deriveFont(Font.PLAIN, fontSize);

        setFont(getSelectedFont());
    }
    public void setFont(String font) {
        setSelectedFont(font);

        switch(getSelectedFont()) {
            case "Arial":  gui.textArea.setFont(arial);  break;
            case "Maru Monica":  gui.textArea.setFont(maruMonica);  break;
            case "Playwrite":  gui.textArea.setFont(playwrite);  break;
            // ...

            default:  System.out.println("Invalid font: " + getSelectedFont());  break;
        }
    }
}
