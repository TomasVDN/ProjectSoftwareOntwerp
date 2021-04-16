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
import converter.HTMLToGUI;
import events.EventReader;
import htmlElement.ContentSpan;

public class WindowManager {

	private Container activeDialog;
	private MainDialog mainDialog;

	private int width;
	private int height;
	private final int BAR_SIZE = 60;
	private final int BOOKMARK_SIZE = 60;
	private GUIElement elementWithKeyboardFocus;
	private GUIElement pressedElement;
	
	private EventReader eventReader;
	
	/**
	 * Constructor of the WindowManager class.
	 * @param width - the width of the linked window
	 * @param height - the height of the linked window
	 */
	public WindowManager (MyCanvasWindow window) {
		//Make new Browsr object.
		Browsr browsr = new Browsr(this);
		
		//Set width/height.
		this.setWidth(600);
		this.setHeight(600);
		
		//Initialize EventReader
		this.eventReader = new EventReader(browsr);
		
		//Make the bar and page containers
		Container searchBarContainer = new Container(0,0,this.getWidth(),BAR_SIZE);
		Container bookmarkBarContainer = new Container(0,BAR_SIZE,this.getWidth(),height - BAR_SIZE);
		Container pageContainer = new Container(0, BAR_SIZE + BOOKMARK_SIZE, this.getWidth(), height - BAR_SIZE - BOOKMARK_SIZE);

		MainDialog mainDialog = new MainDialog(0, 0, 600, 600, pageContainer, searchBarContainer, bookmarkBarContainer, eventReader);
		this.setMainDialog(mainDialog);
		this.setActiveDialog(mainDialog);
		
		//Setup the welcome page
		Text text = new Text(50, 200, "Welcome my friend, take a seat and enjoy your surfing.");
		this.addGUIToPage(text);

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
		
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.getWidth(), this.getHeight(), htmlElements,this.getEventReader());
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
	 * Checks if there is a GUIElement at coordinates (x,y), and transmits it to the inherit method. If there are none, it transmits null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers given by the mouse click (like enter et cetera)
	 */
	public void handleClickLeftMouse(int x, int y, int clickCount, int modifiers) {
		try {
			changeElementWithKeyboardFocus(getActiveDialog().getGUIAtPosition(x, y));	
		} catch (NullPointerException e) {
			changeElementWithKeyboardFocus(null);
		}		
	}
	
	
	public void handlePressLeftMouse(int x, int y, int clickCount, int modifiers) {
		this.setPressedElement(this.getActiveDialog().getGUIAtPosition(x, y)); 
	}
	
	
	public void handleReleaseLeftMouse(int x, int y, int clickCount, int modifiers) {
		if(this.getPressedElement()!=null) {
			GUIElement releasedAt = this.getActiveDialog().getGUIAtPosition(x, y);
			if(this.getPressedElement() == releasedAt ) {
				releasedAt.handleReleaseClick();
			}
		}
		this.setPressedElement(null);
	}
	
	
	
	/**
	 * This method changes the activeElement to the given element, and invokes element.handleClick. If the given element is already the activeElement, it only invokes element.handleClick.
	 * @param element - the new activeElement
	 */
	public void changeElementWithKeyboardFocus(GUIElement element) {
		if(element!=this.getElementWithKeyboardFocus()) {
			//deactivate old activeElement
			if (elementWithKeyboardFocus != null && this.getElementWithKeyboardFocus().isActive()) {
				elementWithKeyboardFocus.setActive(false);
			}
			
			//activate new activeElement
			this.setElementWithKeyboardFocus(element);
			
			if (elementWithKeyboardFocus != null) {
				elementWithKeyboardFocus.handleClick();
				elementWithKeyboardFocus.setActive(true);
			}
		}
		else {
			//if the given element is already the activeElement.
			if(element!=null) {
				element.handleClick();
			}
		}
	}
	
	/**
	 * This handles hyperlinks.
	 * @param url
	 */
	public void updateURL(String url) {
		this.changeElementWithKeyboardFocus(null);
		this.getMainDialog().getSearchbar().replaceBox(url);
	}

	/**
	 * @return the elementWithKeyboardFocus
	 */
	public GUIElement getElementWithKeyboardFocus() {
		return elementWithKeyboardFocus;
	}

	/**
	 * @param elementWithKeyboardFocus the elementWithKeyboardFocus to set
	 */
	public void setElementWithKeyboardFocus(GUIElement elementWithKeyboardFocus) {
		this.elementWithKeyboardFocus = elementWithKeyboardFocus;
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
	 * @return this.eventReader
	 */
	public EventReader getEventReader() {
		return eventReader;
	}

	
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx) {
		//TODO modifiers => 128 = Ctrl, 512 = alt
		if (id == KeyEvent.KEY_PRESSED & modifiersEx != 128) {
			GUIElement element = this.getElementWithKeyboardFocus();
			if (element != null) {
				element.handleKeyEvent(keyCode, keyChar, modifiersEx);
			}
		}
		
		// Ctrl + s
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			System.out.print(keyCode);
			if (keyCode == 83) {
				this.setActiveDialog("saveDialog");
			}
		}
		
		// Ctrl + d
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			System.out.print(keyCode);
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
	
	public String getURLFromSearchBar() {
		return this.getMainDialog().getSearchbar().getText();
	}
	
	public String getBaseURLFromSearchBar() {
		return this.getMainDialog().getSearchbar().getBaseURL();
	}

	/**
	 * @return the activeDialog
	 */
	public Container getActiveDialog() {
		return activeDialog;
	}

	/**
	 * @param activeDialog the activeDialog to set
	 */
	public void setActiveDialog(Container activeDialog) {
		if (this.getActiveDialog() != this.getMainDialog() && activeDialog != this.getMainDialog()) {
			return;
		}
		this.activeDialog = activeDialog;
	}
	
	/**
	 * @param activeDialog the activeDialog to set
	 */
	public void setActiveDialog(String type) {
		
		if (this.getActiveDialog() != this.getMainDialog() && type != "mainDialog") {
			return;
		}
		
		//TODO rename function/keep with strings?
		this.changeElementWithKeyboardFocus(null);
		
		switch (type) {
		case "mainDialog":
			this.setActiveDialog(this.getMainDialog());
			break;
		case "saveDialog":
			this.setActiveDialog(new SaveDialog(0, 0, this.getWidth(), this.getHeight(), this.getEventReader()));
			break;
		case "bookmarkDialog":
			// TODO misschien toch nog anders doen?
			BookmarkDialog newBookmarkDialog = new BookmarkDialog(0, 0, this.getWidth(), this.getHeight(), this.getEventReader());
			String suggestedUrl = this.getSearchbar().getText();
			newBookmarkDialog.getUrlTextBox().setLeftText(suggestedUrl);
			this.setActiveDialog(newBookmarkDialog);
		default:
			break;
		}
	}

	public GUIElement getPressedElement() {
		return pressedElement;
	}

	public void setPressedElement(GUIElement pressedElement) {
		this.pressedElement = pressedElement;
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
	
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		Text bookmarkHyperlinkNameText = new Text(0, 0, bookmarkHyperlinkName);
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, bookmarkHyperlinkNameText, bookmarkHyperlinkUrl, this.getEventReader());
		this.getMainDialog().addBookmark(newBookmarkHyperlink);
	}
}
