package main;

import function.*;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    public JFrame window;

    // TEXT AREA
    public JTextArea textArea;
    JScrollPane scrollPane;
    public boolean wordWrapOn = false;

    // TOP MENU BAR
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    // FILE MENU
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    // EDIT MENU
    JMenuItem iSearch, iUndo, iRedo;
    // FORMAT MENU
    public JMenuItem iWrap, iFontArial, iFontMaruMonica, iFontPlaywrite,
                     iFontSize12, iFontSize16, iFontSize22, iFontSize30;
    JMenu menuFont, menuFontSize;
    // COLOR MENU
    JMenuItem iColor1, iColor2, iColor3;

    // CLASSES
    Function_File fFile     = new Function_File(this);
    Function_Format fFormat = new Function_Format(this);
    Function_Color fColor   = new Function_Color(this);
    Function_Edit fEdit     = new Function_Edit(this);
    Function_Search fSearch = new Function_Search(this);
    KeyHandler keyH         = new KeyHandler(this);

    public UndoManager uManager = new UndoManager();

    public static final int DEFAULT_FONT_SIZE = 16;
    public static final int WINDOW_WIDTH  = 800;
    public static final int WINDOW_HEIGHT = 600;

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();

        // DEFAULT PARAMETERS
        fFormat.setSelectedFont("Arial");
        fFormat.createFont(DEFAULT_FONT_SIZE);
        fFormat.wordWrap();
        fColor.changeColor("White");

        window.setVisible(true);
    }

    /// WINDOW
    public void createWindow() {
        window = new JFrame("Notepad");

        ImageIcon icon = new ImageIcon("res/icon.png");
        window.setIconImage(icon.getImage());

        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /// AREA WITH TEXT
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
    /// MENU BAR
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
    /// TOOLBAR: File
    public void createFileMenu() {

        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("Save As");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }
    /// TOOLBAR: Edit
    public void createEditMenu() {

        iSearch = new JMenuItem("Find");
        iSearch.addActionListener(this);
        iSearch.setActionCommand("Search");
        menuEdit.add(iSearch);

        iUndo = new JMenuItem("↩ Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("↪ Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }
    /// TOOLBAR: Format
    public void createFormatMenu() {

        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        // FONT
        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontMaruMonica = new JMenuItem("Maru Monica");
        iFontMaruMonica.addActionListener(this);
        iFontMaruMonica.setActionCommand("Maru Monica");
        menuFont.add(iFontMaruMonica);

        iFontPlaywrite = new JMenuItem("Playwrite");
        iFontPlaywrite.addActionListener(this);
        iFontPlaywrite.setActionCommand("Playwrite");
        menuFont.add(iFontPlaywrite);

        // FONT SIZE
        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size16");
        menuFontSize.add(iFontSize16);

        iFontSize22 = new JMenuItem("22");
        iFontSize22.addActionListener(this);
        iFontSize22.setActionCommand("Size22");
        menuFontSize.add(iFontSize22);

        iFontSize30 = new JMenuItem("30");
        iFontSize30.addActionListener(this);
        iFontSize30.setActionCommand("Size30");
        menuFontSize.add(iFontSize30);
    }
    /// TOOLBAR: Color
    public void createColorMenu() {

        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Green");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Green");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Black");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Black");
        menuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch(command) {
            // FILE
            case "New":  fFile.newFile();  break;
            case "Open":  fFile.openFile();  break;
            case "Save":  fFile.saveFile();  break;
            case "Save As":  fFile.saveAsFile();  break;
            case "Exit":  fFile.exit();  break;
            // EDIT
            case "Search":  fSearch.showSearchDialog();  break;
            case "Undo":  fEdit.undo();  break;
            case "Redo":  fEdit.redo();  break;
            // FORMAT
            case "Word Wrap":  fFormat.wordWrap();  break;
            case "Arial", "Playwrite", "Maru Monica":  fFormat.setFont(command);  break;
            case "Size12":  fFormat.createFont(12);  break;
            case "Size16":  fFormat.createFont(16);  break;
            case "Size22":  fFormat.createFont(22);  break;
            case "Size30":  fFormat.createFont(30);  break;
            case "White":  fColor.changeColor("White");  break;
            case "Green":  fColor.changeColor("Green");  break;
            case "Black":  fColor.changeColor("Black");  break;
            // ...

            default:  System.out.println("Invalid command: " + command);  break;
        }
    }
}
