package function;

import main.GUI;
import javax.swing.*;
import java.awt.*;

public class FunctionSearch {
    private final GUI gui;
    private JDialog searchDialog;
    private JTextField searchField, replaceField;
    private int lastFoundIndex = 0;

    // CONSTANTS
    private static final int DIALOG_WIDTH = 400;
    private static final int DIALOG_HEIGHT = 220;
    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 2;
    private static final int GRID_HGAP = 5;
    private static final int GRID_VGAP = 5;

    private static final String FIND_LABEL = "Find:";
    private static final String REPLACE_LABEL = "Replace with:";
    private static final String FIND_BUTTON_TEXT = "Find further";
    private static final String REPLACE_BUTTON_TEXT = "Replace";
    private static final String REPLACE_ALL_BUTTON_TEXT = "Replace all";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";

    private static final String TEXT_NOT_FOUND_MESSAGE = "Text was not found!";
    private static final String REPLACEMENT_COMPLETE_MESSAGE = "Replacement is complete!";

    public FunctionSearch(GUI gui) {
        this.gui = gui;
    }

    public void showSearchDialog() {

        if(searchDialog == null) {
            createSearchDialog();
        }
        searchDialog.setVisible(true);
    }

    private void createSearchDialog() {

        searchDialog = new JDialog(gui.getWindow(), "Find & replace", true);
        searchDialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        searchDialog.setLocationRelativeTo(gui.getWindow());
        searchDialog.setLayout(new GridLayout(GRID_ROWS, GRID_COLS, GRID_HGAP, GRID_VGAP));

        searchDialog.add(new JLabel(FIND_LABEL));
        searchField = new JTextField();
        searchDialog.add(searchField);

        searchDialog.add(new JLabel(REPLACE_LABEL));
        replaceField = new JTextField();
        searchDialog.add(replaceField);

        JButton findButton = new JButton(FIND_BUTTON_TEXT);
        findButton.addActionListener(e -> findNext());
        searchDialog.add(findButton);

        JButton replaceButton = new JButton(REPLACE_BUTTON_TEXT);
        replaceButton.addActionListener(e -> replace());
        searchDialog.add(replaceButton);

        JButton replaceAllButton = new JButton(REPLACE_ALL_BUTTON_TEXT);
        replaceAllButton.addActionListener(e -> replaceAll());
        searchDialog.add(replaceAllButton);

        JButton cancelButton = new JButton(CANCEL_BUTTON_TEXT);
        cancelButton.addActionListener(e -> searchDialog.setVisible(false));
        searchDialog.add(cancelButton);
    }

    private void findNext() {
        String searchText = searchField.getText();
        String content = gui.getTextArea().getText();

        if(searchText.isEmpty()) return;

        int index = content.indexOf(searchText, lastFoundIndex);

        if(index == -1) {
            index = content.indexOf(searchText);

            if(index == -1) {
                JOptionPane.showMessageDialog(searchDialog, TEXT_NOT_FOUND_MESSAGE);
                return;
            }
        }

        gui.getTextArea().setSelectionStart(index);
        gui.getTextArea().setSelectionEnd(index + searchText.length());
        gui.getTextArea().requestFocus();
        lastFoundIndex = index + 1;
    }

    private void replace() {
        String selectedText = gui.getTextArea().getSelectedText();

        if(selectedText != null && selectedText.equals(searchField.getText())) {
            gui.getTextArea().replaceSelection(replaceField.getText());
        }
        findNext();
    }

    private void replaceAll() {
        String content = gui.getTextArea().getText();
        String searchText = searchField.getText();
        String replaceText = replaceField.getText();

        if(searchText.isEmpty()) return;

        String newContent = content.replaceAll(searchText, replaceText);
        gui.getTextArea().setText(newContent);

        JOptionPane.showMessageDialog(searchDialog, REPLACEMENT_COMPLETE_MESSAGE);
    }
}
