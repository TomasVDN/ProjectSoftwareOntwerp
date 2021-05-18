package facades;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import canvaswindow.MyCanvasWindow;
import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;
import EventListeners.SavePageListener;
import GUIElements.*;

/**
 * Controller type class. Handles everything UI related.
 *
 */
public class WindowManager {
	
	private MyCanvasWindow window;

	private Dialog activeDialog;
	private MainDialog mainDialog;
	
	private List<SavePageListener> savePageListener = new ArrayList<SavePageListener>();
	private List<AddBookmarkListener> AddbookmarkListener = new ArrayList<AddBookmarkListener>();

	private int width;
	private int height;
	boolean ignoreClick;
	
	
	/**
	 * Constructor of the WindowManager class.
	 * @param window - CanvasWindow linked to this windowManager
	 */
	public WindowManager (MyCanvasWindow window) {
		
		this.window = window;
		
		//Make new BrowsrController object.
		BrowsrController browsrController = new BrowsrController(this);
		this.AddbookmarkListener.add(browsrController::addBookmark);
		this.savePageListener.add(browsrController::savePage);
		
		this.setWidth(600);
		this.setHeight(600);
		
		initMainDialog(browsrController);
	}

	/**
	 * Initialize the mainDialog.
	 * @param browsrController - event handler to transmit to the maindDialog.
	 */
	private void initMainDialog(BrowsrController browsrController) {
		MainDialog mainDialog = new MainDialog(0, 0, 600, 600, browsrController);
		this.setMainDialog(mainDialog);
		this.setActiveDialog(mainDialog);
	}

	/**
	 * Changes width and height of this windowManager (in case of a resize). Then paint the components of the active dialog.
	 * @param g - the Graphics to use to paint 
	 * @param width - the width of the linked window
	 * @param height - the height of the linked window
	 */
	public void paint(Graphics g, int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.getActiveDialog().paint(g);
	}
	
	/**
	 * Adds the given GUIElements to the active page.
	 * @param GUIElements - the list of GUIElements to add to the active page.
	 * @param code - String with the HTML code to be saved in the HTMLDocument
	 * @param path - String with the URL to be saved in the HTMLDocument
	 */
	public void loadHTML(ArrayList<GUIElement> GUIElements, String path, String code) {
		this.getMainDialog().getActiveHTMLDocument().loadHTML(GUIElements, path, code);
	}
	
	/**
	 * Adds the given GUIElement to the active page.
	 * @param gui - the GUIElement to add to the active page.
	 */
	public void addGUIToPage(GUIElement gui) {
		this.getActiveDialog().addElement(gui);
	}
	
	/**
	 * Adds the given list of GUIElements to the active page.
	 * @param listOfGUI - the GUIElements to add to the active page.
	 */
	public void addAllGUIToPage(ArrayList<GUIElement> listOfGUI) {
		this.getActiveDialog().addMultipleElements(listOfGUI);
	}

	/**
	 * Transmits the left click event to the active dialog. If the dialog is being switched, ignore the event.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks`
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handleClickLeftMouse(int x, int y, int clickCount, int modifiers) {
		if (!ignoreClick) {
			this.getActiveDialog().handleClickLeftMouse(x, y, clickCount, modifiers);	
		}
	}
	
	/**
	 * Transmits the pressLeftMouse event to the active Dialog.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handlePressLeftMouse(int x, int y, int clickCount, int modifiers) {
		this.ignoreClick=false;
		this.getActiveDialog().handlePressLeftMouse(x, y, clickCount, modifiers);
	}
	
	/**
	 * Transmits the ReleaseLeftMouse event to the active Dialog.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handleReleaseLeftMouse(int x, int y, int clickCount, int modifiers) {
		this.getActiveDialog().handleReleaseLeftMouse(x,y,clickCount,modifiers);
	}
	
	/**
	 * Transmits the dragMouse event to the active Dialog.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handleDragLeftMouse(int x, int y, int clickCount, int modifiers) {
		this.getActiveDialog().handleDragMouse(x, y, clickCount, modifiers);
	}

	/**
	 * Transmits the KeyEvent to the active Dialog. Intercepts certain key combinations (those used to change dialogs).
	 * @param id
	 * @param keyCode
	 * @param keyChar
	 * @param modifiersEx
	 */
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx) {
		if (id == KeyEvent.KEY_PRESSED) {
			this.getActiveDialog().handleKeyEvent(keyCode, keyChar, modifiersEx);
		}
		
		// Ctrl + s
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			if (keyCode == 83) {
				this.changeDialog("saveDialog");
			}
		}
		
		// Ctrl + d
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			if (keyCode == 68) {
				this.changeDialog("bookmarkDialog");
			}
		}
		
		//For some keyboards that do not register the tilde key.
        if (id == KeyEvent.KEY_TYPED && keyChar == "~".charAt(0)) {
            GUIElement element = this.getElementWithKeyboardFocus();
            if (element != null) {
                element.handleKeyEvent(keyCode, keyChar, modifiersEx);
            }
        }
	}
	
	/**
	 * Updates the displayed URL in the searchBar in MainDialog.
	 * @param url - the URL to update the searchBar to
	 */
	public void updateURL(String url) {
		this.getActiveDialog().changeElementWithKeyboardFocus(null);
		this.getMainDialog().getSearchbar().replaceBox(url);
	}
	
	/**
	 * Returns the URL present at that moment in mainDialog.searchbar.
	 * @return
	 */
	public String getURLFromSearchBar() {
		SearchBar bar = this.getMainDialog().getSearchbar();
		return bar.getText();
	}
	
	/**
	 * returns the base from the URL present at that moment in mainDialog.searchbar.
	 * @return
	 */
	public String getBaseURLFromSearchBar() {
		SearchBar bar = this.getMainDialog().getSearchbar();
		return bar.getBaseURL();
	}

	/**
	 * @return the activeDialog
	 */
	public Dialog getActiveDialog() {
		return activeDialog;
	}

	/**
	 * @param activeDialog - the activeDialog to set
	 */
	public void setActiveDialog(Dialog activeDialog) {
		if (this.getActiveDialog() != this.getMainDialog() && activeDialog != this.getMainDialog()) {
			return;
		}
		this.activeDialog = activeDialog;
	}
	
	/**
	 * Changes the dialog to the corresponding dialog in the given String
	 * @param type - the activeDialog to set (String version)
	 */
	public void changeDialog(String type) {
		if (this.getActiveDialog() != this.getMainDialog() && type != "mainDialog") {
			return;
		}
		switch (type) {
		case "mainDialog":
			setMainDialogToActive();
			break;
		case "saveDialog":
			setSaveDialogToActive();
			break;
		case "bookmarkDialog":
			setBookmarkDialogToActive();
		default:
			break;
		}
		this.ignoreClick = true;
	}	
	
	/**
	 * Sets the MainDialog as the active dialog.
	 */
	private void setMainDialogToActive() {
		this.setActiveDialog(this.getMainDialog());
		this.changeWindowTitle("BrowsrController");
	}
	
	/**
	 * Creates a saveDialog and set it as the active dialog.
	 */
	private void setSaveDialogToActive() {
		SaveDialog saveDialog = new SaveDialog(0, 0, this.getWidth(), this.getHeight(),this.getMainDialog().getActiveHTMLDocument());
		
		saveDialog.addChangeDialogListener(this::changeDialog);
		savePageListener.forEach(listener -> saveDialog.addSavePageListener(listener));
		
		this.setActiveDialog(saveDialog);
		this.changeWindowTitle("Save As");
	}
	
	/**
	 * Creates a bookmarkDialog and set it as the active dialog.
	 */
	private void setBookmarkDialogToActive() {
		String suggestedUrl = this.getURLFromSearchBar();
		BookmarkDialog newBookmarkDialog = new BookmarkDialog(0, 0, this.getWidth(), this.getHeight(), suggestedUrl); //TODO
		
		newBookmarkDialog.addChangeDialogListener(this::changeDialog);
		AddbookmarkListener.forEach(listener -> newBookmarkDialog.addAddBookmarkListener(listener));
		
		this.setActiveDialog(newBookmarkDialog);
		this.changeWindowTitle("Add Bookmark");
	}

	/**
	 * @return the mainDialog
	 */
	public MainDialog getMainDialog() {
		return mainDialog;
	}

	/**
	 * @param mainDialog the mainDialog to set
	 */
	public void setMainDialog(MainDialog mainDialog) {
		this.mainDialog = mainDialog;
	}

	/**
	 * @return the searchbar
	 */
	public SearchBar getSearchbar() {
		return this.getMainDialog().getSearchbar();
	}
	
	/**
	 * Creates a bookmark and adds it in the mainDialog.
	 * @param bookmarkHyperlinkName - name of the bookmark
	 * @param bookmarkHyperlinkUrl - URL of the bookmark
	 * @param BrowsrController - eventHandler
	 */
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl, BrowsrController browsr) {
		Text bookmarkHyperlinkNameText = new Text(0, 0, bookmarkHyperlinkName);
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, bookmarkHyperlinkNameText, bookmarkHyperlinkUrl);
		newBookmarkHyperlink.addHyperLinkListener(browsr);
		this.getMainDialog().addBookmark(newBookmarkHyperlink);
	}

	/**
	 * 
	 * @return this.window
	 */
	public MyCanvasWindow getWindow() {
		return window;
	}

	/**
	 * Set this.window to the given window.
	 * @param window - new value of this.window
	 */
	public void setWindow(MyCanvasWindow window) {
		this.window = window;
	}
	
	/**
	 * Sets the title of this.window.
	 * @param newTitle
	 */
	public void changeWindowTitle(String newTitle) {
		this.window.setWindowTitle(newTitle);
	}
	
	/**
	 * @return this.width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the value of this.width to the given value.
	 * @param width
	 */
	public void setWidth(int width) throws IllegalArgumentException {
		if (width <= 0) {
			throw new IllegalArgumentException("Given width must be positive");
		} else {
			this.width = width;
		}
	}

	/**
	 * @return this.height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the value of this.height to the given value.
	 * @param height
	 */
	public void setHeight(int height) {
		if (height <= 0) {
			throw new IllegalArgumentException("Given height must be positive");
		} else {
			this.height = height;
		}
	}
	
	/**
	 * @return the elementWithKeyboardFocus
	 */
	public GUIElement getElementWithKeyboardFocus() {
		return this.getActiveDialog().getElementWithKeyBoardFocus();
	}
}
