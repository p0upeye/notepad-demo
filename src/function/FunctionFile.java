package function;

import main.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionFile {
    private final GUI gui;
    private String fileName;     // File name
    private String fileAddress;  // File address

    // CONSTANTS FOR MESSAGES
    private static final String FILE_NOT_OPENED_ERROR = "File not Opened!";
    private static final String FILE_NOT_SAVED_ERROR = "File not Saved!";
    private static final String FILE_NOT_SAVED_AS_ERROR = "File not Saved As!";
    private static final String SAVE_SUCCESS_MESSAGE = "Current result was successfully saved!";
    private static final String NEW_FILE_TITLE = "New File";

    // GETTERS & SETTERS
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
    public String getFileName() {
        return fileName;
    }
    public String getFileAddress() {
        return fileAddress;
    }

    public FunctionFile(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.getTextArea().setText("");
        gui.getWindow().setTitle(NEW_FILE_TITLE);

        // Clear the file name and address
        setFileName(null);
        setFileAddress(null);
    }
    public void openFile() {
        FileDialog fDialog = new FileDialog(gui.getWindow(), "Select File", FileDialog.LOAD);
        fDialog.setVisible(true);

        if(fDialog.getFile() != null) {
            setFileName(fDialog.getFile());
            setFileAddress(fDialog.getDirectory());

            gui.getWindow().setTitle(getFileName());
        }

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(
                    getFileAddress() + getFileName()));

            // Clear text
            gui.getTextArea().setText("");

            String line;
            while((line = bReader.readLine()) != null) {
                gui.getTextArea().append(line + "\n");
            }

            bReader.close();
        } catch(Exception e) {
            System.err.println(FILE_NOT_OPENED_ERROR);
        }
    }
    public void saveFile() {

        if(getFileName() == null) {
            saveAsFile();
        }else {
            try {
                FileWriter fWriter = new FileWriter(getFileAddress() + getFileName());
                fWriter.write(gui.getTextArea().getText());
                gui.getWindow().setTitle(getFileName());
                fWriter.close();

                JOptionPane.showMessageDialog(gui.getTextArea(), SAVE_SUCCESS_MESSAGE);
            } catch(Exception e) {
                System.err.println(FILE_NOT_SAVED_ERROR);
            }
        }
    }
    public void saveAsFile() {
        FileDialog fDialog = new FileDialog(gui.getWindow(), GUI.SAVE_AS_TEXT, FileDialog.SAVE);
        fDialog.setVisible(true);

        if(fDialog.getFile() != null) {
            setFileName(fDialog.getFile());
            setFileAddress(fDialog.getDirectory());
            gui.getWindow().setTitle(getFileName());
        }

        try {
            FileWriter fWriter = new FileWriter(getFileAddress() + getFileName());
            fWriter.write(gui.getTextArea().getText());
            fWriter.close();
        } catch(Exception e) {
            System.err.println(FILE_NOT_SAVED_AS_ERROR);
        }
    }
    public void exit() {
        System.exit(0);
    }
}
