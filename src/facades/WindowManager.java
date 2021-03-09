package facades;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.SearchBar;
import canvaswindow.FontMetricGetter;
import canvaswindow.MyCanvasWindow;
import container.Container;
import converter.HTMLToGUI;
import events.EventReader;
import htmlElement.ContentSpan;

public class WindowManager {


	
	private Container bar;
	private SearchBar searchbar;
	private Container page;
	
	private int width;
	private int height;
	private final int BARSIZE = 100;
	
	private GUIElement activeElement;
	
	/**
	 * Constructor of the WindowManager class.
	 * @param width - the width of the linked window
	 * @param height - the height of the linked window
	 */
	public WindowManager (int width,int height, MyCanvasWindow window) {
		//Make new Browsr object.
		Browsr browsr = new Browsr(this);
		
		//Set width/height. If w/h < 50, set it to 600.
		this.setWidth(width);
		this.setHeight(height);
		
		//Initialize EventReader
		EventReader x = EventReader.getInstance();
		x.setBrowsr(browsr);
		
		//Initialize FontMetricGetter
		FontMetricGetter f = FontMetricGetter.getInstance();
		f.setWindow(window);
		
		//Make the bar and page containers
		this.setBar(new Container(0,0,this.getWidth(),BARSIZE));
		this.setPage(new Container(0, BARSIZE, this.getWidth(), height - BARSIZE));

		SearchBar searchBar = new SearchBar(10, 10, this.getWidth() - 20, 50);
		this.setSearchbar(searchBar);
		this.getBar().addElement(searchBar);
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
		this.getBar().paint(g);
		this.getPage().paint(g);
	}
	
	/**
	 * This method returns the container located at the (x,y) coordinates. Returns null otherwise.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return if (this.bar.contains(x,y): return this.bar
	 * 			| if (this.page.contains(x,y): return this.page
	 * 			| otherwise return null
	 */
	public Container containerAt(int x, int y) {
		if(this.getBar().containsPoint(x, y)) {
			return this.getBar();
		}
		else if(this.getPage().containsPoint(x, y)) {
			return this.getPage();
		}
		return null;
	}
	
	/**
	 * Transforms the given HTMLElements to GUIElements, and adds them to the active page.
	 * @param htmlElements - the list of HTMLElements to add to the active page.
	 */
	public void draw(ArrayList<ContentSpan> htmlElements) {
		HTMLToGUI converter = new HTMLToGUI();
		
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.getWidth(), this.getHeight(), htmlElements);
		this.getPage().resetAllElements(list);
	}
	
	/**
	 * Adds the given GUIElement to the active page.
	 * @param gui - the GUIElement to add to the active page.
	 */
	public void addGUIToPage(GUIElement gui) {
		this.getPage().addElement(gui);
	}
	
	/**
	 * Adds the given GUIElements to the active page.
	 * @param listOfGUI - the GUIElements to add to the active page.
	 */
	public void addAllGUIToPage(ArrayList<GUIElement> listOfGUI) {
		this.getPage().addMultipleElements(listOfGUI);
	}

	/**
	 * Checks if there is a GUIElement at coordinates (x,y), and transmits it to the inherit method. If there are none, it transmits null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param clickCount - the amount of clicks
	 * @param modifiers - the modifiers
	 */
	public void handleLeftMouse(int x, int y, int clickCount, int modifiers) {
		try {
			inherit(containerAt(x, y).elementAt(x, y));	
		} catch (NullPointerException e) {
			inherit(null);
		}		
	}
	
	/**
	 * This method changes the activeElement to the given element, and invokes element.handleClick. If the given element is already the activeElement, it only invokes element.handleClick.
	 * @param element - the new activeElement
	 */
	public void inherit(GUIElement element) {
		if(element!=this.getActiveElement()) {
			//deactivate old activeElement
			if (activeElement != null && this.getActiveElement().isActive()) {
				activeElement.setActive(false);
			}
			
			//activate new activeElement
			activeElement = element;
			
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
		this.inherit(null);
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
	 * @return this.bar
	 */
	public Container getBar() {
		return bar;
	}

	/**
	 * @param bar - the new value of this.bar
	 */
	public void setBar(Container bar) {
		this.bar = bar;
	}

	/**
	 * @return this.page
	 */
	public Container getPage() {
		return page;
	}

	/**
	 * @param page - the new value of this.page
	 */
	public void setPage(Container page) {
		this.page = page;
	}
	
	/**
	 * @return this.width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the value of this.width to the given value. If this value is < 50, this.width will be set to 600.
	 * @param width
	 */
	public void setWidth(int width) {
		if (width < 50) {
			this.width = 600;
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
	 * Sets the value of this.height to the given value. If this value is < 50, this.height will be set to 600.
	 * @param height
	 */
	public void setHeight(int height) {
		if (height < 50) {
			this.height = 600;
		} else {
			this.height = height;
		}
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
		//TODO modifiers => 64 = Shift, 128 = Ctrl, 512 = alt
		if (id == KeyEvent.KEY_PRESSED) {
			GUIElement element = this.getActiveElement();
			if (element != null) {
				element.handleKeyEvent(keyCode, keyChar, modifiersEx);
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

}
