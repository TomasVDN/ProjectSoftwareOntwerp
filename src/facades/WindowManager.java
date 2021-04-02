package facades;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.MainDialog;
import GUIElements.SearchBar;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import GUIElements.Container;
import converter.HTMLToGUI;
import events.EventReader;
import events.SavePageEvent;
import htmlElement.ContentSpan;

public class WindowManager {

	private MainDialog mainPage;
	private Container saveDialog = new Container(0, 0, 600, 600);
	private Container bookmarkDialog = new Container(0, 0, 600, 600);
	
	private Container activeDialog;
	
	private SearchBar searchbar;
	
	private int width;
	private int height;
	private final int BAR_SIZE = 60;
	private final int BOOKMARK_SIZE = 60;
	private GUIElement activeElement;
	
	private EventReader eventReader;
	
	/**
	 * Constructor of the WindowManager class.
	 * @param width - the width of the linked window
	 * @param height - the height of the linked window
	 */
	public WindowManager (MyCanvasWindow window) {
		//Make new Browsr object.
		Browsr browsr = new Browsr(this);
		
		//Set width/height. If w/h < 50, set it to 600.
		this.setWidth(600);
		this.setHeight(600);
		
		//Initialize EventReader
		this.eventReader = new EventReader(browsr);
		
		//Make the bar and page containers
		Container searchBarContainer = new Container(0,0,this.getWidth(),BAR_SIZE);
		Container bookmarkBarContainer = new Container(0,BAR_SIZE,this.getWidth(),height - BAR_SIZE);
		Container pageContainer = new Container(0, BAR_SIZE + BOOKMARK_SIZE, this.getWidth(), height - BAR_SIZE - BOOKMARK_SIZE);
		
		this.mainPage = new MainDialog(0, 0, 600, 600, pageContainer, searchBarContainer, bookmarkBarContainer);
		this.setActiveDialog(mainPage);
		
		//Setup the welcome page
		Text text = new Text(50, 200, "Welcome my friend, take a seat and enjoy your surfing.");
		this.addGUIToPage(text);

		SearchBar searchBar = new SearchBar(10, 10, this.getWidth() - 20, 50, this.eventReader);
		this.setSearchbar(searchBar);
		this.getMainPage().getSearchBarContainer().addElement(searchBar);
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
		
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.getWidth(), this.getHeight(), htmlElements, this.eventReader);
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
	public void handleLeftMouse(int x, int y, int clickCount, int modifiers) {
		try {
			changeActive(getActiveDialog().getGUIAtPosition(x, y));	
		} catch (NullPointerException e) {
			changeActive(null);
		}		
	}
	
	/**
	 * This method changes the activeElement to the given element, and invokes element.handleClick. If the given element is already the activeElement, it only invokes element.handleClick.
	 * @param element - the new activeElement
	 */
	public void changeActive(GUIElement element) {
		if(element!=this.getActiveElement()) {
			//deactivate old activeElement
			if (activeElement != null && this.getActiveElement().isActive()) {
				activeElement.setActive(false);
			}
			
			//activate new activeElement
			this.setActiveElement(element);
			
			if (activeElement != null) {
				activeElement.handleClick();
				activeElement.setActive(true);
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
		this.changeActive(null);
		this.getSearchbar().replaceBox(url);
	}

	/**
	 * @return the activeElement
	 */
	public GUIElement getActiveElement() {
		return activeElement;
	}

	/**
	 * @param activeElement the activeElement to set
	 */
	public void setActiveElement(GUIElement activeElement) {
		this.activeElement = activeElement;
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
	
	/**
	 * @return this.searchBar
	 */
	public SearchBar getSearchbar() {
		return searchbar;
	}

	/**
	 * @param searchbar - the new value of this.searchBar
	 */
	public void setSearchbar(SearchBar searchbar) {
		this.searchbar = searchbar;
	}

	
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx) {
		//TODO modifiers => 128 = Ctrl, 512 = alt
		if (id == KeyEvent.KEY_PRESSED & modifiersEx != 128) {
			GUIElement element = this.getActiveElement();
			if (element != null) {
				element.handleKeyEvent(keyCode, keyChar, modifiersEx);
			}
		}
		
		if (id == KeyEvent.KEY_PRESSED & modifiersEx == 128) {
			System.out.print(keyCode);
			if (keyCode == 83) {
				SavePageEvent event = new SavePageEvent();
				this.getEventReader().readEvent(event);
			}
		}
		
		//Enkel op Tomas zijn keyboard.
        if (id == KeyEvent.KEY_TYPED && keyChar == "~".charAt(0)) {
            GUIElement element = this.getActiveElement();
            if (element != null) {
                element.handleKeyEvent(keyCode, keyChar, modifiersEx);
            }
        }
	}
	
	public String getBaseURLFromSearchBar() {
		return this.getSearchbar().getBaseURL();
	}

	/**
	 * @return the mainPage
	 */
	public MainDialog getMainPage() {
		return mainPage;
	}

	/**
	 * @param mainPage the mainPage to set
	 */
	public void setMainPage(MainDialog mainPage) {
		this.mainPage = mainPage;
	}

	/**
	 * @return the saveDialog
	 */
	public Container getSaveDialog() {
		return saveDialog;
	}

	/**
	 * @param saveDialog the saveDialog to set
	 */
	public void setSaveDialog(Container saveDialog) {
		this.saveDialog = saveDialog;
	}

	/**
	 * @return the bookmarkDialog
	 */
	public Container getBookmarkDialog() {
		return bookmarkDialog;
	}

	/**
	 * @param bookmarkDialog the bookmarkDialog to set
	 */
	public void setBookmarkDialog(Container bookmarkDialog) {
		this.bookmarkDialog = bookmarkDialog;
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
		this.activeDialog = activeDialog;
	}

}
