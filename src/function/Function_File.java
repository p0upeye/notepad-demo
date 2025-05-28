package function;

import main.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {
    GUI gui;
    private String fileName;     // File name
    private String fileAddress;  // File address

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

    public Function_File(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New File");

        // Clear the file name and address
        setFileName(null);
        setFileAddress(null);
    }
    public void openFile() {
        FileDialog fDialog = new FileDialog(gui.window, "Select File", FileDialog.LOAD);
        fDialog.setVisible(true);

        if(fDialog.getFile() != null) {
            setFileName(fDialog.getFile());
            setFileAddress(fDialog.getDirectory());

            gui.window.setTitle(getFileName());
        }

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(
                    getFileAddress() + getFileName()));

            // Clear text
            gui.textArea.setText("");

            String line;
            while((line = bReader.readLine()) != null) {
                gui.textArea.append(line + "\n");
            }

            bReader.close();
        } catch(Exception e) {
            System.err.println("File not Opened!");
        }
    }
    public void saveFile() {

        if(getFileName() == null) {
            saveAsFile();
        }else {
            try {
                FileWriter fWriter = new FileWriter(getFileAddress() + getFileName());
                fWriter.write(gui.textArea.getText());
                gui.window.setTitle(getFileName());
                fWriter.close();

                JOptionPane.showMessageDialog(gui.textArea, "Current result was successfully saved!");
            } catch(Exception e) {
                System.err.println("File not Saved!");
            }
        }
    }
    public void saveAsFile() {
        FileDialog fDialog = new FileDialog(gui.window, "Save As", FileDialog.SAVE);
        fDialog.setVisible(true);

        if(fDialog.getFile() != null) {
            setFileName(fDialog.getFile());
            setFileAddress(fDialog.getDirectory());
            gui.window.setTitle(getFileName());
        }

        try {
            FileWriter fWriter = new FileWriter(getFileAddress() + getFileName());
            fWriter.write(gui.textArea.getText());
            fWriter.close();
        } catch(Exception e) {
            System.err.println("File not Saved As!");
        }
    }
    public void exit() {
        System.exit(0);
    }
}
