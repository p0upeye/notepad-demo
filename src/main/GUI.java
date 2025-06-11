package main;

import constants.AppConstants;
import function.*;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main GUI class that manages the application interface.
 */
public class GUI implements ActionListener {
    private JFrame window;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private boolean wordWrapOn;

    // Menu components
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuFormat, menuColor;
    private JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    private JMenuItem iSearch, iUndo, iRedo;
    private JMenuItem iWrap, iFontArial, iFontMaruMonica, iFontPlaywrite,
            iFontSize12, iFontSize16, iFontSize22, iFontSize30;
    private JMenu menuFont, menuFontSize;
    private JMenuItem iColor1, iColor2, iColor3;

    // Functional classes
    private final FunctionFile fileHandler;
    private final FunctionFormat formatHandler;
    private final FunctionColor colorHandler;
    private final FunctionEdit editHandler;
    private final FunctionSearch searchHandler;
    private final KeyHandler keyHandler;
    private final UndoManager undoManager;

    public GUI() {
        wordWrapOn = false;

        // Initializing functional classes
        this.fileHandler = new FunctionFile(this);
        this.formatHandler = new FunctionFormat(this);
        this.colorHandler = new FunctionColor(this);
        this.editHandler = new FunctionEdit(this);
        this.searchHandler = new FunctionSearch(this);
        this.keyHandler = new KeyHandler(this);
        this.undoManager = new UndoManager();

        initializeGUI();
        setDefaultSettings();
        window.setVisible(true);
    }

    // Getters & setters
    public JFrame getWindow() {
        return window;
    }
    public JTextArea getTextArea() {
        return textArea;
    }
    public boolean isWordWrapOn() {
        return wordWrapOn;
    }
    public void setWordWrapOn(boolean wordWrapOn) {
        this.wordWrapOn = wordWrapOn;
    }
    public JMenuItem getItemWrap() {
        return iWrap;
    }
    public UndoManager getUndoManager() {
        return undoManager;
    }
    public JMenu getMenuFile() {
        return menuFile;
    }
    public FunctionFile getFunctionFile() {
        return fileHandler;
    }
    public FunctionEdit getFunctionEdit() {
        return editHandler;
    }
    public FunctionSearch getFunctionSearch() {
        return searchHandler;
    }

    /**
     * Initialization of all GUI components
     */
    private void initializeGUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();
        // ...
    }

    /**
     * Setting default settings
     */
    private void setDefaultSettings() {
        formatHandler.setSelectedFont(AppConstants.Fonts.ARIAL);
        formatHandler.createFont(AppConstants.Fonts.DEFAULT_SIZE);
        formatHandler.wordWrap();
        colorHandler.changeColor(AppConstants.Colors.WHITE);
        // ...
    }

    private void createWindow() {
        window = new JFrame("Notepad");

        try {
            ImageIcon icon = new ImageIcon("res/icon.png");
            window.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println(AppConstants.Messages.ICON_NOT_FOUND);
        }

        window.setSize(AppConstants.Dimensions.WINDOW_WIDTH, AppConstants.Dimensions.WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.addKeyListener(keyHandler);

        // Adding support for undo/redo
        textArea.getDocument().addUndoableEditListener(
                e -> undoManager.addEdit(e.getEdit())
        );

        scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFormat = new JMenu("Format");
        menuColor = new JMenu("Color");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuColor);
    }

    private void createFileMenu() {
        iNew = createMenuItem(AppConstants.MenuText.NEW, AppConstants.MenuText.NEW);
        iOpen = createMenuItem(AppConstants.MenuText.OPEN, AppConstants.MenuText.OPEN);
        iSave = createMenuItem(AppConstants.MenuText.SAVE, AppConstants.MenuText.SAVE);
        iSaveAs = createMenuItem(AppConstants.MenuText.SAVE_AS, AppConstants.MenuText.SAVE_AS);
        iExit = createMenuItem(AppConstants.MenuText.EXIT, AppConstants.MenuText.EXIT);

        menuFile.add(iNew);
        menuFile.add(iOpen);
        menuFile.add(iSave);
        menuFile.add(iSaveAs);
        menuFile.addSeparator();
        menuFile.add(iExit);
    }

    private void createEditMenu() {
        iSearch = createMenuItem(AppConstants.MenuText.FIND, AppConstants.Commands.SEARCH);
        iUndo = createMenuItem(AppConstants.MenuText.UNDO, AppConstants.Commands.UNDO);
        iRedo = createMenuItem(AppConstants.MenuText.REDO, AppConstants.Commands.REDO);

        menuEdit.add(iSearch);
        menuEdit.addSeparator();
        menuEdit.add(iUndo);
        menuEdit.add(iRedo);
    }

    private void createFormatMenu() {
        iWrap = createMenuItem(AppConstants.MenuText.WORD_WRAP_OFF, AppConstants.Commands.WORD_WRAP);
        menuFormat.add(iWrap);

        // Font submenu
        menuFont = new JMenu("Font");
        iFontArial = createMenuItem(AppConstants.Fonts.ARIAL, AppConstants.Fonts.ARIAL);
        iFontMaruMonica = createMenuItem(AppConstants.Fonts.MARU_MONICA, AppConstants.Fonts.MARU_MONICA);
        iFontPlaywrite = createMenuItem(AppConstants.Fonts.PLAYWRITE, AppConstants.Fonts.PLAYWRITE);

        menuFont.add(iFontArial);
        menuFont.add(iFontMaruMonica);
        menuFont.add(iFontPlaywrite);
        menuFormat.add(menuFont);

        // Font size submenu
        menuFontSize = new JMenu("Font Size");
        iFontSize12 = createMenuItem(String.valueOf(AppConstants.Fonts.SIZE_12), AppConstants.Commands.SIZE_12);
        iFontSize16 = createMenuItem(String.valueOf(AppConstants.Fonts.SIZE_16), AppConstants.Commands.SIZE_16);
        iFontSize22 = createMenuItem(String.valueOf(AppConstants.Fonts.SIZE_22), AppConstants.Commands.SIZE_22);
        iFontSize30 = createMenuItem(String.valueOf(AppConstants.Fonts.SIZE_30), AppConstants.Commands.SIZE_30);

        menuFontSize.add(iFontSize12);
        menuFontSize.add(iFontSize16);
        menuFontSize.add(iFontSize22);
        menuFontSize.add(iFontSize30);
        menuFormat.add(menuFontSize);
    }

    private void createColorMenu() {
        iColor1 = createMenuItem(AppConstants.Colors.WHITE, AppConstants.Colors.WHITE);
        iColor2 = createMenuItem(AppConstants.Colors.GREEN, AppConstants.Colors.GREEN);
        iColor3 = createMenuItem(AppConstants.Colors.BLACK, AppConstants.Colors.BLACK);

        menuColor.add(iColor1);
        menuColor.add(iColor2);
        menuColor.add(iColor3);
    }

    /**
     * Helper method for creating menu items.
     * Reduces code duplication.
     */
    private JMenuItem createMenuItem(String text, String command) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(this);
        item.setActionCommand(command);
        return item;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            // File
            case AppConstants.MenuText.NEW: fileHandler.newFile(); break;
            case AppConstants.MenuText.OPEN: fileHandler.openFile(); break;
            case AppConstants.MenuText.SAVE: fileHandler.saveFile(); break;
            case AppConstants.MenuText.SAVE_AS: fileHandler.saveAsFile(); break;
            case AppConstants.MenuText.EXIT: fileHandler.exit(); break;

            // Edit
            case AppConstants.Commands.SEARCH: searchHandler.showSearchDialog(); break;
            case AppConstants.Commands.UNDO: editHandler.undo(); break;
            case AppConstants.Commands.REDO: editHandler.redo(); break;

            // Format
            case AppConstants.Commands.WORD_WRAP: formatHandler.wordWrap(); break;
            case AppConstants.Fonts.ARIAL, AppConstants.Fonts.MARU_MONICA, AppConstants.Fonts.PLAYWRITE:
                formatHandler.setFont(command); break;
            case AppConstants.Commands.SIZE_12: formatHandler.createFont(AppConstants.Fonts.SIZE_12); break;
            case AppConstants.Commands.SIZE_16: formatHandler.createFont(AppConstants.Fonts.SIZE_16); break;
            case AppConstants.Commands.SIZE_22: formatHandler.createFont(AppConstants.Fonts.SIZE_22); break;
            case AppConstants.Commands.SIZE_30: formatHandler.createFont(AppConstants.Fonts.SIZE_30); break;

            // Color
            case AppConstants.Colors.WHITE, AppConstants.Colors.GREEN, AppConstants.Colors.BLACK:
                colorHandler.changeColor(command); break;
            // ...

            default:
                System.out.println(AppConstants.Messages.INVALID_COMMAND + command);
                break;
        }
    }
}
