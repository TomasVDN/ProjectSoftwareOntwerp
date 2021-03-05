package facades;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;
import container.Container;
import utils.FontMetricsHandle;

public class WindowManager {

	private Browsr browsr;
	private FontMetricsHandle fontMetricsHandler;
	//private ArrayList<Container> listOfContainers = new ArrayList<Container>();
	private Container bar;
	private Container page;
	private int width;
	private int height;
	
	
	public WindowManager (MyCanvasWindow myCanvasWindow, int newWidth,int newHeight) {
		browsr = new Browsr(this);
		fontMetricsHandler = new FontMetricsHandle(myCanvasWindow);
		int BARSIZE = 100;
		//bar is a container that should always be shown, on all windows. For the moment, it only contains one element: a searchBar
		this.setBar(new Container(0,0,this.getWidth(),BARSIZE)); //TODO window.getHeight kan enkel opgeroepen worden nadat show is opgeroepen geweest
		this.setPage(new Container(0, BARSIZE, newWidth, newHeight));
		TextBox searchBar = new TextBox(10, 10, 580, 50);
		searchBar.addUnselectListener(() -> {
			browsr.runUrl();
		});
		searchBar.addKeyboardListener(10, () -> {
			this.inherit(null);
		});
		bar.addElement(searchBar);
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
	


	private GUIElement activeElement;
	private GUIElement previousActive;

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
		previousActive = activeElement;
		activeElement = element;
		
		if (previousActive != null) {
			previousActive.setActive(false);
		}
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

	/**
	 * @return the previousActive
	 */
	public GUIElement getPreviousActive() {
		return previousActive;
	}

	/**
	 * @param previousActive the previousActive to set
	 */
	public void setPreviousActive(GUIElement previousActive) {
		this.previousActive = previousActive;
	}

	/**
	 * @return the fontMetricsHandler
	 */
	public FontMetricsHandle getFontMetricsHandler() {
		return fontMetricsHandler;
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
	
	
}
