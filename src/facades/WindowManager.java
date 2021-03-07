package facades;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TextBox;
import container.Container;
import converter.HTMLToGUI;
import htmlElement.ContentSpan;

public class WindowManager {

	private Browsr browsr;
	//private ArrayList<Container> listOfContainers = new ArrayList<Container>();
	private Container bar;
	private Container page;
	private int width;
	private int height;
	private EventReader eventReader;

	

	
	public WindowManager (int newWidth,int newHeight) {
		browsr = new Browsr(this);
		int BARSIZE = 100;
		//bar is a container that should always be shown, on all windows. For the moment, it only contains one element: a searchBar
		this.setEventReader(new EventReader(browsr));
		this.setBar(new Container(0,0,this.getWidth(),BARSIZE)); //TODO window.getHeight kan enkel opgeroepen worden nadat show is opgeroepen geweest
		this.setPage(new Container(0, BARSIZE, newWidth, newHeight));

		bar = new Container(0,0,600,100); //TODO resize bar when resizing window
		TextBox searchBar = new TextBox(10, 10, 580, 50);
		searchBar.addUnselectListener(() -> {
			browsr.runUrl(searchBar.getText());
		});
		searchBar.addKeyboardListener(10, () -> {
			this.inherit(null);
		});
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

	public void paint(Graphics g) {
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
			if (containerAt(x, y).elementAt(x, y) != null) {
				inherit(containerAt(x, y).elementAt(x, y));
			}		
		} catch (NullPointerException e) {
			inherit(null);
		}		
	}
	
	/**
	 * This sets the previous & active elements.
	 */
	public void inherit(GUIElement element) {
		if (activeElement != null) {
			activeElement.setActive(false);
		}
		
		activeElement = element;
		
		if (activeElement != null) {
			activeElement.handleClick();
			activeElement.setActive(true);
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
		HTMLToGUI converter = new HTMLToGUI(0, 100); //TODO edit this
		
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.getWidth(), this.getHeight(), htmlElements);
		this.getPage().addAllElement(list);
		/*for (GUIElement e: list) {
			activePage.addElement(e);
		}*/
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

	public EventReader getEventReader() {
		return eventReader;
	}

	public void setEventReader(EventReader eventReader) {
		this.eventReader = eventReader;
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
