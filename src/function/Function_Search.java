package function;

import main.GUI;
import javax.swing.*;
import java.awt.*;

public class Function_Search {
    GUI gui;
    private JDialog searchDialog;
    private JTextField searchField, replaceField;
    private int lastFoundIndex = 0;

    public Function_Search(GUI gui) {
        this.gui = gui;
    }

    public void showSearchDialog() {
        if(searchDialog == null) {
            createSearchDialog();
        }
        searchDialog.setVisible(true);
    }

    private void createSearchDialog() {
        searchDialog = new JDialog(gui.window, "Find & replace", true);
        searchDialog.setSize(400, 220);
        searchDialog.setLocationRelativeTo(gui.window);
        searchDialog.setLayout(new GridLayout(4, 2, 5, 5));

        searchDialog.add(new JLabel("Find:"));
        searchField = new JTextField();
        searchDialog.add(searchField);

        searchDialog.add(new JLabel("Replace with:"));
        replaceField = new JTextField();
        searchDialog.add(replaceField);

        JButton findButton = new JButton("Find further");
        findButton.addActionListener(e -> findNext());
        searchDialog.add(findButton);

        JButton replaceButton = new JButton("Replace");
        replaceButton.addActionListener(e -> replace());
        searchDialog.add(replaceButton);

        JButton replaceAllButton = new JButton("Replace all");
        replaceAllButton.addActionListener(e -> replaceAll());
        searchDialog.add(replaceAllButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> searchDialog.setVisible(false));
        searchDialog.add(cancelButton);
    }

    private void findNext() {
        String searchText = searchField.getText();
        String content = gui.textArea.getText();

        if(searchText.isEmpty()) return;

        int index = content.indexOf(searchText, lastFoundIndex);

        if(index == -1) {
            index = content.indexOf(searchText);

            if(index == -1) {
                JOptionPane.showMessageDialog(searchDialog, "Text was not found!");
                return;
            }
        }

        gui.textArea.setSelectionStart(index);
        gui.textArea.setSelectionEnd(index + searchText.length());
        gui.textArea.requestFocus();
        lastFoundIndex = index + 1;
    }

    private void replace() {
        String selectedText = gui.textArea.getSelectedText();
        if(selectedText != null && selectedText.equals(searchField.getText())) {
            gui.textArea.replaceSelection(replaceField.getText());
        }
        findNext();
    }

    private void replaceAll() {
        String content = gui.textArea.getText();
        String searchText = searchField.getText();
        String replaceText = replaceField.getText();

        if(searchText.isEmpty()) return;

        String newContent = content.replaceAll(searchText, replaceText);
        gui.textArea.setText(newContent);

        JOptionPane.showMessageDialog(searchDialog, "Replacement is complete!");
    }
}