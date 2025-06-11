package constants;

import java.awt.*;

/**
 * Centralized class for all application constants.
 * Organized by logical groups for better readability.
 */
public final class AppConstants {

    // Prevent instantiation of this class
    private AppConstants() {}

    /**
     * Constants for window and component sizes
     */
    public static final class Dimensions {
        public static final int WINDOW_WIDTH = 800;
        public static final int WINDOW_HEIGHT = 600;
        public static final int SEARCH_DIALOG_WIDTH = 400;
        public static final int SEARCH_DIALOG_HEIGHT = 220;
        public static final int GRID_ROWS = 4;
        public static final int GRID_COLS = 2;
        public static final int GRID_HGAP = 5;
        public static final int GRID_VGAP = 5;
    }

    /**
     * Constants for fonts
     */
    public static final class Fonts {
        public static final String ARIAL = "Arial";
        public static final String MARU_MONICA = "Maru Monica";
        public static final String PLAYWRITE = "Playwrite (only English)";
        public static final int DEFAULT_SIZE = 16;
        public static final int SIZE_12 = 12;
        public static final int SIZE_16 = 16;
        public static final int SIZE_22 = 22;
        public static final int SIZE_30 = 30;
        public static final String MARU_MONICA_PATH = "/font/x12y16pxMaruMonica.ttf";
        public static final String PLAYWRITE_PATH = "/font/PlaywriteHU-VariableFont_wght.ttf";
    }

    /**
     * Constants for color schemes
     */
    public static final class Colors {
        public static final String WHITE = "White";
        public static final String GREEN = "Green";
        public static final String BLACK = "Black";

        // White
        public static final Color WINDOW_WHITE = Color.WHITE;
        public static final Color TEXT_AREA_WHITE = Color.WHITE;
        public static final Color TEXT_BLACK = Color.BLACK;
        public static final Color CARET_BLACK = Color.BLACK;

        // Green
        public static final Color WINDOW_GREEN = Color.GREEN;
        public static final Color TEXT_AREA_GREEN = new Color(50, 67, 51);
        public static final Color TEXT_GREEN = new Color(232, 255, 234);
        public static final Color CARET_GREEN = new Color(184, 222, 187);

        // Black
        public static final Color WINDOW_BLACK = Color.BLACK;
        public static final Color TEXT_AREA_BLACK = new Color(44, 44, 44);
        public static final Color TEXT_GRAY = new Color(232, 232, 232);
        public static final Color CARET_GRAY = new Color(165, 165, 165);
    }

    /**
     * Constants for menu and button text
     */
    public static final class MenuText {
        public static final String NEW = "New";
        public static final String OPEN = "Open";
        public static final String SAVE = "Save";
        public static final String SAVE_AS = "Save As";
        public static final String EXIT = "Exit";
        public static final String FIND = "Find";
        public static final String UNDO = "↩ Undo";
        public static final String REDO = "↪ Redo";
        public static final String WORD_WRAP_ON = "Word Wrap: On";
        public static final String WORD_WRAP_OFF = "Word Wrap: Off";
    }

    /**
     * Constants for action commands
     */
    public static final class Commands {
        public static final String SIZE_12 = "Size12";
        public static final String SIZE_16 = "Size16";
        public static final String SIZE_22 = "Size22";
        public static final String SIZE_30 = "Size30";
        public static final String SEARCH = "Search";
        public static final String UNDO = "Undo";
        public static final String REDO = "Redo";
        public static final String WORD_WRAP = "Word Wrap";
    }

    /**
     * Constants for the search dialog
     */
    public static final class SearchDialog {
        public static final String FIND_LABEL = "Find:";
        public static final String REPLACE_LABEL = "Replace with:";
        public static final String FIND_BUTTON = "Find further";
        public static final String REPLACE_BUTTON = "Replace";
        public static final String REPLACE_ALL_BUTTON = "Replace all";
        public static final String CANCEL_BUTTON = "Cancel";
        public static final String TEXT_NOT_FOUND = "Text was not found!";
        public static final String REPLACEMENT_COMPLETE = "Replacement is complete!";
    }

    /**
     * Constants for error and success messages
     */
    public static final class Messages {
        public static final String ICON_NOT_FOUND = "Icon not found, using default";
        public static final String FILE_NOT_OPENED = "Failed to open file";
        public static final String FILE_NOT_SAVED = "Failed to save file";
        public static final String FILE_NOT_SAVED_AS = "Failed to save file as";
        public static final String SAVE_SUCCESS = "File successfully saved!";
        public static final String NEW_FILE_TITLE = "New File";
        public static final String FONT_LOAD_ERROR = "Failed to load custom font";
        public static final String INVALID_COMMAND = "Invalid command: ";
        public static final String INVALID_FONT = "Invalid font: ";
    }
}
