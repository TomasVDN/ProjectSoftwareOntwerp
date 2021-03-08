package facades;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.SearchBar;
import GUIElements.TextBox;
import container.Container;
import converter.HTMLToGUI;
import htmlElement.ContentSpan;

public class WindowManager {

	private Browsr browsr;
	//private ArrayList<Container> listOfContainers = new ArrayList<Container>();
	private Container bar;
	private SearchBar searchbar;
	private Container page;
	private int width;
	private int height;

	

	
	public WindowManager (int newWidth,int newHeight) {
		EventReader x = EventReader.getInstance();
		x.setBrowsr(browsr);
		
		browsr = new Browsr(this);
		int BARSIZE = 100;
		//bar is a container that should always be shown, on all windows. For the moment, it only contains one element: a searchBar
		this.setBar(new Container(0,0,this.getWidth(),BARSIZE)); //TODO window.getHeight kan enkel opgeroepen worden nadat show is opgeroepen geweest
		this.setPage(new Container(0, BARSIZE, newWidth, newHeight));

		bar = new Container(0,0,600,100); //TODO resize bar when resizing window
		SearchBar searchBar = new SearchBar(10, 10, 580, 50);//this.getWidth()-10
		this.setSearchbar(searchBar);
		bar.addElement(searchBar);
		//listOfContainers.add(bar);
		
		//activePage = new Container(0,100,600,500);
		//listOfContainers.add(activePage);

	}

	/**
	 * @return the browsr
	 */
	public Browsr getBrowsr() {
		return browsr;
	}

	public void paint(Graphics g,int width,int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.getBar().paint(g);
		this.getPage().paint(g);
	}
	
	
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
	 * @return the listOfContainers
	 */
	/*public ArrayList<Container> getListOfContainers() {
		return listOfContainers;
	}
*/
	/**
	 * @param listOfContainers the listOfContainers to set
	 */
	/*public void addToListOfContainers(Container container) {
		this.listOfContainers.add(container);
	}*/


	private GUIElement activeElement;

	public void handleLeftMouse(int x, int y, int clickCount, int modifiers) {
		
		try {
			inherit(containerAt(x, y).elementAt(x, y));	
		} catch (NullPointerException e) {
			inherit(null);
		}		
	}
	
	/**
	 * This sets the previous & active elements.
	 */
	public void inherit(GUIElement element) {
		if(element!=this.getActiveElement()) {	
			if (activeElement != null && this.getActiveElement().isActive()) {
				activeElement.setActive(false);
			}
			
			activeElement = element;
			
			if (activeElement != null) {
				activeElement.handleClick();
				activeElement.setActive(true);
			}
		}
		else {
			if(element!=null) {
				element.handleClick();
			}
		}
		
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

	public void draw(ArrayList<ContentSpan> htmlElements) {
		HTMLToGUI converter = new HTMLToGUI();
		
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.getWidth(), this.getHeight(), htmlElements);
		this.getPage().resetAllElements(list);
	}


	public Container getBar() {
		return bar;
	}

	public void setBar(Container bar) {
		this.bar = bar;
	}

	public Container getPage() {
		return page;
	}

	public void setPage(Container page) {
		this.page = page;
	}
	
	public void addGUIToPage(GUIElement gui) {
		this.getPage().addElement(gui);
	}
	
	public void addAllGUIToPage(ArrayList<GUIElement> listOfGUI) {
		this.getPage().addAllElement(listOfGUI);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public SearchBar getSearchbar() {
		return searchbar;
	}

	public void setSearchbar(SearchBar searchbar) {
		this.searchbar = searchbar;
	}
	
	/**
	 * does all function to update the url
	 */
	public void updateURL(String url) {
		this.inherit(null);
		this.getSearchbar().replaceBox(url);
		
	}
	
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx) {
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
	
	/**
	 * @return the activePage
	 */
	/*public Container getActivePage() {
		return activePage;
	}*/

	/**
	 * @param activePage the activePage to set
	 */
	/*public void setActivePage(Container activePage) {
		this.activePage = activePage;
	}*/

}
