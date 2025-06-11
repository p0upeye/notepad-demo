package function;

import constants.AppConstants;
import main.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Class for working with files.
 */
public class FunctionFile {
    private final GUI gui;
    private String fileName;     // File name
    private String fileAddress;  // File address

    // Getters & setters
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

    /**
     * Creating a new file
     */
    public void newFile() {
        gui.getTextArea().setText("");
        gui.getWindow().setTitle(AppConstants.Messages.NEW_FILE_TITLE);

        // Clear the file name and address
        setFileName(null);
        setFileAddress(null);
    }

    /**
     * Opening an existing file
     */
    public void openFile() {
        FileDialog fileDialog = new FileDialog(gui.getWindow(), "Select File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String selectedFileName = fileDialog.getFile();
        String selectedDirectory = fileDialog.getDirectory();

        if(selectedFileName == null) {
            return;  // User canceled selection
        }

        setFileName(selectedFileName);
        setFileAddress(selectedDirectory);
        gui.getWindow().setTitle(getFileName());

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
            System.err.println(AppConstants.Messages.FILE_NOT_OPENED);
        }
    }

    /**
     * Saving the file
     */
    public void saveFile() {

        if(getFileName() == null) {
            saveAsFile();
        }else {
            try {
                FileWriter fWriter = new FileWriter(getFileAddress() + getFileName());
                fWriter.write(gui.getTextArea().getText());
                gui.getWindow().setTitle(getFileName());
                fWriter.close();

                JOptionPane.showMessageDialog(gui.getTextArea(), AppConstants.Messages.SAVE_SUCCESS);
            } catch(Exception e) {
                System.err.println(AppConstants.Messages.FILE_NOT_SAVED);
            }
        }
    }

    /**
     * Saving the file with a new name
     */
    public void saveAsFile() {
        FileDialog fileDialog = new FileDialog(gui.getWindow(), AppConstants.MenuText.SAVE_AS, FileDialog.SAVE);
        fileDialog.setVisible(true);

        String selectedFileName = fileDialog.getFile();
        String selectedDirectory = fileDialog.getDirectory();

        if(selectedFileName == null) {
            return;  // User canceled saving
        }

        setFileName(selectedFileName);
        setFileAddress(selectedDirectory);
        gui.getWindow().setTitle(getFileName());

        try {
            FileWriter fWriter = new FileWriter(getFileAddress() + getFileName());
            fWriter.write(gui.getTextArea().getText());
            fWriter.close();
        } catch(Exception e) {
            System.err.println(AppConstants.Messages.FILE_NOT_SAVED_AS);
        }
    }

    /**
     * Exit the app
     */
    public void exit() {
        System.exit(0);
    }
}
