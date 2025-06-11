package main;

import function.*;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private JFrame window;

    // TEXT AREA
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private boolean wordWrapOn = false;

    // TOP MENU BAR
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuFormat, menuColor;
    // FILE MENU
    private JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    // EDIT MENU
    private JMenuItem iSearch, iUndo, iRedo;
    // FORMAT MENU
    private JMenuItem iWrap, iFontArial, iFontMaruMonica, iFontPlaywrite,
            iFontSize12, iFontSize16, iFontSize22, iFontSize30;
    private JMenu menuFont, menuFontSize;
    // COLOR MENU
    private JMenuItem iColor1, iColor2, iColor3;

    // CLASSES
    private final FunctionFile fFile = new FunctionFile(this);
    private final FunctionFormat fFormat = new FunctionFormat(this);
    private final FunctionColor fColor = new FunctionColor(this);
    private final FunctionEdit fEdit = new FunctionEdit(this);
    private final FunctionSearch fSearch = new FunctionSearch(this);
    private final KeyHandler keyH = new KeyHandler(this);
    private final UndoManager uManager = new UndoManager();

    // CONSTANTS
    public static final int DEFAULT_FONT_SIZE = 16;
    public static final int WINDOW_WIDTH  = 800;
    public static final int WINDOW_HEIGHT = 600;

    private static final String NEW_TEXT = "New";
    private static final String OPEN_TEXT = "Open";
    private static final String SAVE_TEXT = "Save";
    public static final String SAVE_AS_TEXT = "Save As";
    private static final String EXIT_TEXT = "Exit";
    private static final String FIND_TEXT = "Find";
    private static final String UNDO_TEXT = "↩ Undo";
    private static final String REDO_TEXT = "↪ Redo";
    public static final String WORD_WRAP_ON_TEXT = "Word Wrap: On";
    public static final String WORD_WRAP_OFF_TEXT = "Word Wrap: Off";
    public static final String ARIAL_TEXT = "Arial";
    public static final String MARU_MONICA_TEXT = "Maru Monica";
    public static final String PLAYWRITE_TEXT = "Playwrite";
    public static final String WHITE_TEXT = "White";
    public static final String GREEN_TEXT = "Green";
    public static final String BLACK_TEXT = "Black";

    private static final int FONT_SIZE_12 = 12;
    private static final int FONT_SIZE_16 = 16;
    private static final int FONT_SIZE_22 = 22;
    private static final int FONT_SIZE_30 = 30;

    private static final String SIZE_12_COMMAND = "Size12";
    private static final String SIZE_16_COMMAND = "Size16";
    private static final String SIZE_22_COMMAND = "Size22";
    private static final String SIZE_30_COMMAND = "Size30";
    private static final String SEARCH_COMMAND = "Search";
    private static final String UNDO_COMMAND = "Undo";
    private static final String REDO_COMMAND = "Redo";
    private static final String WORD_WRAP_COMMAND = "Word Wrap";

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();

        // DEFAULT PARAMETERS
        fFormat.setSelectedFont(ARIAL_TEXT);
        fFormat.createFont(DEFAULT_FONT_SIZE);
        fFormat.wordWrap();
        fColor.changeColor(WHITE_TEXT);

        window.setVisible(true);
    }

    // GETTERS & SETTERS
    public JFrame getWindow() { return window; }
    public JTextArea getTextArea() { return textArea; }
    public boolean isWordWrapOn() { return wordWrapOn; }
    public void setWordWrapOn(boolean wordWrapOn) { this.wordWrapOn = wordWrapOn; }
    public JMenuItem getItemWrap() { return iWrap; }
    public UndoManager getUndoManager() { return uManager; }
    public JMenu getMenuFile() { return menuFile; }
    public FunctionFile getFunctionFile() { return fFile; }
    public FunctionEdit getFunctionEdit() { return fEdit; }
    public FunctionSearch getFunctionSearch() { return fSearch; }

    // WINDOW
    public void createWindow() {
        window = new JFrame("Notepad");

        ImageIcon icon = new ImageIcon("res/icon.png");
        window.setIconImage(icon.getImage());

        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // AREA WITH TEXT
    public void createTextArea() {
        textArea = new JTextArea();

        // ADD KEY HANDLER
        textArea.addKeyListener(keyH);

        // ADD UNDO & REDO
        textArea.getDocument().addUndoableEditListener(
                e -> uManager.addEdit(e.getEdit())
        );

        scrollPane = new JScrollPane(  // Borders of the text area
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder())
        ;
        window.add(scrollPane);
    }
    // MENU BAR
    public void createMenuBar() {

        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
    }
    // TOOLBAR: File
    public void createFileMenu() {

        iNew = new JMenuItem(NEW_TEXT);
        iNew.addActionListener(this);
        iNew.setActionCommand(NEW_TEXT);
        menuFile.add(iNew);

        iOpen = new JMenuItem(OPEN_TEXT);
        iOpen.addActionListener(this);
        iOpen.setActionCommand(OPEN_TEXT);
        menuFile.add(iOpen);

        iSave = new JMenuItem(SAVE_TEXT);
        iSave.addActionListener(this);
        iSave.setActionCommand(SAVE_TEXT);
        menuFile.add(iSave);

        iSaveAs = new JMenuItem(SAVE_AS_TEXT);
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand(SAVE_AS_TEXT);
        menuFile.add(iSaveAs);

        iExit = new JMenuItem(EXIT_TEXT);
        iExit.addActionListener(this);
        iExit.setActionCommand(EXIT_TEXT);
        menuFile.add(iExit);
    }
    // TOOLBAR: Edit
    public void createEditMenu() {

        iSearch = new JMenuItem(FIND_TEXT);
        iSearch.addActionListener(this);
        iSearch.setActionCommand(SEARCH_COMMAND);
        menuEdit.add(iSearch);

        iUndo = new JMenuItem(UNDO_TEXT);
        iUndo.addActionListener(this);
        iUndo.setActionCommand(UNDO_COMMAND);
        menuEdit.add(iUndo);

        iRedo = new JMenuItem(REDO_TEXT);
        iRedo.addActionListener(this);
        iRedo.setActionCommand(REDO_COMMAND);
        menuEdit.add(iRedo);
    }
    // TOOLBAR: Format
    public void createFormatMenu() {

        iWrap = new JMenuItem(WORD_WRAP_OFF_TEXT);
        iWrap.addActionListener(this);
        iWrap.setActionCommand(WORD_WRAP_COMMAND);
        menuFormat.add(iWrap);

        // FONT
        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem(ARIAL_TEXT);
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand(ARIAL_TEXT);
        menuFont.add(iFontArial);

        iFontMaruMonica = new JMenuItem(MARU_MONICA_TEXT);
        iFontMaruMonica.addActionListener(this);
        iFontMaruMonica.setActionCommand(MARU_MONICA_TEXT);
        menuFont.add(iFontMaruMonica);

        iFontPlaywrite = new JMenuItem(PLAYWRITE_TEXT);
        iFontPlaywrite.addActionListener(this);
        iFontPlaywrite.setActionCommand(PLAYWRITE_TEXT);
        menuFont.add(iFontPlaywrite);

        // FONT SIZE
        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize12 = new JMenuItem(String.valueOf(FONT_SIZE_12));
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand(SIZE_12_COMMAND);
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem(String.valueOf(FONT_SIZE_16));
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand(SIZE_16_COMMAND);
        menuFontSize.add(iFontSize16);

        iFontSize22 = new JMenuItem(String.valueOf(FONT_SIZE_22));
        iFontSize22.addActionListener(this);
        iFontSize22.setActionCommand(SIZE_22_COMMAND);
        menuFontSize.add(iFontSize22);

        iFontSize30 = new JMenuItem(String.valueOf(FONT_SIZE_30));
        iFontSize30.addActionListener(this);
        iFontSize30.setActionCommand(SIZE_30_COMMAND);
        menuFontSize.add(iFontSize30);
    }
    // TOOLBAR: Color
    public void createColorMenu() {

        iColor1 = new JMenuItem(WHITE_TEXT);
        iColor1.addActionListener(this);
        iColor1.setActionCommand(WHITE_TEXT);
        menuColor.add(iColor1);

        iColor2 = new JMenuItem(GREEN_TEXT);
        iColor2.addActionListener(this);
        iColor2.setActionCommand(GREEN_TEXT);
        menuColor.add(iColor2);

        iColor3 = new JMenuItem(BLACK_TEXT);
        iColor3.addActionListener(this);
        iColor3.setActionCommand(BLACK_TEXT);
        menuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            // FILE
            case NEW_TEXT:  fFile.newFile();  break;
            case OPEN_TEXT:  fFile.openFile();  break;
            case SAVE_TEXT:  fFile.saveFile();  break;
            case SAVE_AS_TEXT:  fFile.saveAsFile();  break;
            case EXIT_TEXT:  fFile.exit();  break;
            // EDIT
            case SEARCH_COMMAND:  fSearch.showSearchDialog();  break;
            case UNDO_COMMAND:  fEdit.undo();  break;
            case REDO_COMMAND:  fEdit.redo();  break;
            // FORMAT
            case WORD_WRAP_COMMAND:  fFormat.wordWrap();  break;
            case ARIAL_TEXT, PLAYWRITE_TEXT, MARU_MONICA_TEXT:  fFormat.setFont(command);  break;
            case SIZE_12_COMMAND:  fFormat.createFont(FONT_SIZE_12);  break;
            case SIZE_16_COMMAND:  fFormat.createFont(FONT_SIZE_16);  break;
            case SIZE_22_COMMAND:  fFormat.createFont(FONT_SIZE_22);  break;
            case SIZE_30_COMMAND:  fFormat.createFont(FONT_SIZE_30);  break;
            case WHITE_TEXT:  fColor.changeColor(WHITE_TEXT);  break;
            case GREEN_TEXT:  fColor.changeColor(GREEN_TEXT);  break;
            case BLACK_TEXT:  fColor.changeColor(BLACK_TEXT);  break;
            // ...

            default:  System.out.println("Invalid command: " + command);  break;
        }
    }
}
