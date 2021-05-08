package facades;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.MainDialog;
import GUIElements.SaveDialog;
import GUIElements.SearchBar;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import GUIElements.BookmarkDialog;
import GUIElements.BookmarkHyperlink;
import GUIElements.Container;
import GUIElements.Dialog;
import converter.HTMLToGUI;
import htmlElement.ContentSpan;

public class WindowManager {
	
	private MyCanvasWindow window;
	private Browsr browsr;

	private Dialog activeDialog;
	private MainDialog mainDialog;

	private int width;
	private int height;
	private final int BAR_SIZE = 60;
	private final int BOOKMARK_SIZE = 60;
	boolean ignoreClick; //boolean if the next click shouldnt be reported (not press)
	
	
	/**
	 * Constructor of the WindowManager class.
	 * @param window - CanvasWindow linked to this windowManager
	 */
	public WindowManager (MyCanvasWindow window) {
		
		this.window = window;
		
		//Make new Browsr object.
		this.browsr = new Browsr(this);
		
		//Set width/height.
		this.setWidth(600);
		this.setHeight(600);
		
		//Make the bar and page containers
		initMainDialog();
		
		//Setup the welcome page
		Text text = new Text(50, 200, "Welcome my friend, take a seat and enjoy your surfing.");
		this.addGUIToPage(text);

	}

	/**
	 * Initialize the mainDialog. Adds three containers (one for the searchbar, one for the bookmarks and one for the htmlCode).
	 */
	private void initMainDialog() {
		Container searchBarContainer = new Container(0,0,this.getWidth(),BAR_SIZE);
		Container bookmarkBarContainer = new Container(0,BAR_SIZE,this.getWidth(),height - BAR_SIZE);
		Container pageContainer = new Container(0, BAR_SIZE + BOOKMARK_SIZE, this.getWidth(), height - BAR_SIZE - BOOKMARK_SIZE);

		MainDialog mainDialog = new MainDialog(0, 0, 600, 600, pageContainer, searchBarContainer, bookmarkBarContainer, browsr);
		this.setMainDialog(mainDialog);
		this.setActiveDialog(mainDialog);
	}

	/**
	 * Changes width and height of this windowManager (in case of a resize). Then ask the page and the bar to paint their components.
	 * @param g - the Graphics to use to paint 
	 * @param width - the width of the linked window
	 * @param height - the height of the linked window
	 */
	public void paint(Graphics g,int width,int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.getActiveDialog().paint(g);
	}
	
	/**
	 * Transforms the given HTMLElements to GUIElements, and adds them to the active page.
	 * @param htmlElements - the list of HTMLElements to add to the active page.
	 */
	public void draw(ArrayList<ContentSpan> htmlElements) {
		HTMLToGUI converter = new HTMLToGUI();
		
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.getWidth(), this.getHeight(), htmlElements, browsr);
		this.getActiveDialog().resetAllElements(list);
	}
	
	/**
	 * Adds the given GUIElement to the active page.
	 * @param gui - the GUIElement to add to the active page.
	 */
	public void addGUIToPage(GUIElement gui) {
		this.getActiveDialog().addElement(gui);
	}
	
	/**
	 * Adds the given GUIElements to the active page.
	 * @param listOfGUI - the GUIElements to add to the active page.
	 */
	public void addAllGUIToPage(ArrayList<GUIElement> listOfGUI) {
		this.getActiveDialog().addMultipleElements(listOfGUI);
	}

	/**
	 * Checks if there is a GUIElement at coordinates (x,y), and transmits it to the changeElementWithKeyboardFocus method. If there are none, it transmits null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handleClickLeftMouse(int x, int y, int clickCount, int modifiers) {
		if (!ignoreClick) {
			this.getActiveDialog().handleClickLeftMouse(x, y, clickCount, modifiers);	
		}
	}
	
	/**
	 * Checks if there is a GUIElement at coordinates (x,y) when the mouse is pressed, and calls the handlePressClick method on that element (if there is).
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
	 * Handles the drag operation on the pressed element (x,y) is the position where the element is dragged to
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handleDragLeftMouse(int x, int y, int clickCount, int modifiers) {
		this.getActiveDialog().handleDragMouse(x, y, clickCount, modifiers);
	}
	
	
	/**
	 * Checks if there is a GUIElement at coordinates (x,y) when the mouse is released, and calls the handleReleaseClick method on that element (if there is).
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter etc)
	 */
	public void handleReleaseLeftMouse(int x, int y, int clickCount, int modifiers) {
		this.getActiveDialog().handleReleaseLeftMouse(x,y,clickCount,modifiers);
	}
	
	/**
	 * This handles hyperlinks.
	 * @param url
	 */
	public void updateURL(String url) {
		this.getActiveDialog().changeElementWithKeyboardFocus(null);
		this.getMainDialog().getSearchbar().replaceBox(url);
	}

	/**
	 * @return the elementWithKeyboardFocus
	 */
	public GUIElement getElementWithKeyboardFocus() {
		return this.getActiveDialog().getElementWithKeyBoardFocus();
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
	 * Transmits the keyEvent to the ElementWithKeyboardFocus. If a modifier (such as ctrl+s) is used, calls the setActiveDialog method. 
	 * @param id
	 * @param keyCode
	 * @param keyChar
	 * @param modifiersEx
	 */
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx) {
		if (id == KeyEvent.KEY_PRESSED & modifiersEx != 128) {
			this.getActiveDialog().handleKeyEvent(keyCode, keyChar, modifiersEx);
		}
		
		// Ctrl + s
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			if (keyCode == 83) {
				this.setActiveDialog("saveDialog");
			}
		}
		
		// Ctrl + d
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			if (keyCode == 68) {
				this.setActiveDialog("bookmarkDialog");
			}
		}
		
		//Enkel op Tomas zijn keyboard.
        if (id == KeyEvent.KEY_TYPED && keyChar == "~".charAt(0)) {
            GUIElement element = this.getElementWithKeyboardFocus();
            if (element != null) {
                element.handleKeyEvent(keyCode, keyChar, modifiersEx);
            }
        }
	}
	
	/**
	 * Returns the url present at that moment in mainDialog.searchbar.
	 * @return
	 */
	public String getURLFromSearchBar() {
		SearchBar bar = this.getMainDialog().getSearchbar();
		return bar.getText();
	}
	
	/**
	 * returns the base from the url present at that moment in mainDialog.searchbar.
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
	 * @param type - the activeDialog to set (String version)
	 */
	public void setActiveDialog(String type) {
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
	 * Sets this.MainDialog as the active dialog.
	 */
	private void setMainDialogToActive() {
		this.setActiveDialog(this.getMainDialog());
		this.changeWindowTitle("Browsr");
	}
	
	/**
	 * Creates a saveDialog and set it as the active dialog.
	 */
	private void setSaveDialogToActive() {
		this.setActiveDialog(new SaveDialog(0, 0, this.getWidth(), this.getHeight(), browsr));
		this.changeWindowTitle("Save As");
	}
	
	/**
	 * Creates a bookmarkDialog and set it as the active dialog.
	 */
	private void setBookmarkDialogToActive() {
		BookmarkDialog newBookmarkDialog = new BookmarkDialog(0, 0, this.getWidth(), this.getHeight(), browsr); //TODO
		String suggestedUrl = this.getURLFromSearchBar();
		newBookmarkDialog.setSuggestedUrl(suggestedUrl);
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
	 * Method to add a bookmark in the mainDialog.
	 * @param bookmarkHyperlinkName - name of the bookmark
	 * @param bookmarkHyperlinkUrl - url of the bookmark
	 */
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		Text bookmarkHyperlinkNameText = new Text(0, 0, bookmarkHyperlinkName);
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, bookmarkHyperlinkNameText, bookmarkHyperlinkUrl, browsr);
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
}
