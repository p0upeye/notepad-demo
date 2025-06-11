package function;

import constants.AppConstants;
import main.GUI;
import javax.swing.*;
import java.awt.*;

/**
 * Class for text search and replace
 */
public class FunctionSearch {
    private final GUI gui;
    private JDialog searchDialog;
    private JTextField searchField, replaceField;
    private int lastFoundIndex;

    public FunctionSearch(GUI gui) {
        this.lastFoundIndex = 0;

        this.gui = gui;
    }

    public void showSearchDialog() {

        if(searchDialog == null) {
            createSearchDialog();
        }
        searchDialog.setVisible(true);
    }

    /**
     * Creates the dialog window with available actions
     */
    private void createSearchDialog() {

        searchDialog = new JDialog(gui.getWindow(), "Find & replace", true);
        searchDialog.setSize(AppConstants.Dimensions.SEARCH_DIALOG_WIDTH, AppConstants.Dimensions.SEARCH_DIALOG_HEIGHT);
        searchDialog.setLocationRelativeTo(gui.getWindow());
        searchDialog.setLayout(new GridLayout(
                AppConstants.Dimensions.GRID_ROWS,
                AppConstants.Dimensions.GRID_COLS,
                AppConstants.Dimensions.GRID_HGAP,
                AppConstants.Dimensions.GRID_VGAP));

        searchDialog.add(new JLabel(AppConstants.SearchDialog.FIND_LABEL));
        searchField = new JTextField();
        searchDialog.add(searchField);

        searchDialog.add(new JLabel(AppConstants.SearchDialog.REPLACE_LABEL));
        replaceField = new JTextField();
        searchDialog.add(replaceField);

        JButton findButton = new JButton(AppConstants.SearchDialog.FIND_BUTTON);
        findButton.addActionListener(e -> findNext());
        searchDialog.add(findButton);

        JButton replaceButton = new JButton(AppConstants.SearchDialog.REPLACE_BUTTON);
        replaceButton.addActionListener(e -> replace());
        searchDialog.add(replaceButton);

        JButton replaceAllButton = new JButton(AppConstants.SearchDialog.REPLACE_ALL_BUTTON);
        replaceAllButton.addActionListener(e -> replaceAll());
        searchDialog.add(replaceAllButton);

        JButton cancelButton = new JButton(AppConstants.SearchDialog.CANCEL_BUTTON);
        cancelButton.addActionListener(e -> searchDialog.setVisible(false));
        searchDialog.add(cancelButton);
    }

    /**
     * Method to find the next occurrence of text in the file
     */
    private void findNext() {
        String searchText = searchField.getText();
        String content = gui.getTextArea().getText();

        if(searchText.isEmpty()) return;

        int index = content.indexOf(searchText, lastFoundIndex);

        if(index == -1) {
            index = content.indexOf(searchText);

            if(index == -1) {
                JOptionPane.showMessageDialog(searchDialog, AppConstants.SearchDialog.TEXT_NOT_FOUND);
                return;
            }
        }

        gui.getTextArea().setSelectionStart(index);
        gui.getTextArea().setSelectionEnd(index + searchText.length());
        gui.getTextArea().requestFocus();
        lastFoundIndex = index + 1;
    }

    /**
     * Method to replace the selected text
     */
    private void replace() {
        String selectedText = gui.getTextArea().getSelectedText();

        if(selectedText != null && selectedText.equals(searchField.getText())) {
            gui.getTextArea().replaceSelection(replaceField.getText());
        }
        findNext();
    }

    /**
     * Method to replace all found text
     */
    private void replaceAll() {
        String content = gui.getTextArea().getText();
        String searchText = searchField.getText();
        String replaceText = replaceField.getText();

        if(searchText.isEmpty()) return;

        String newContent = content.replaceAll(searchText, replaceText);
        gui.getTextArea().setText(newContent);

        JOptionPane.showMessageDialog(searchDialog, AppConstants.SearchDialog.REPLACEMENT_COMPLETE);
    }
}
