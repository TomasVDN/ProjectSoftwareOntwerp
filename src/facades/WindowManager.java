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
	private ArrayList<Container> listOfContainers = new ArrayList<Container>();
	
	public WindowManager (MyCanvasWindow myCanvasWindow) {
		browsr = new Browsr(this);
		fontMetricsHandler = new FontMetricsHandle(myCanvasWindow);
		
		//bar is a container that should always be shown, on all windows. For the moment, it only contains one element: a searchBar
		Container bar = new Container(0,0,600,100); //TODO window.getHeight kan enkel opgeroepen worden nadat show is opgeroepen geweest
		TextBox searchBar = new TextBox(10, 10, 580, 50);
		searchBar.addUnselectListener(() -> {
			browsr.runUrl();
		});
		searchBar.addKeyboardListener(10, () -> {
			this.inherit(null);
		});
		bar.addElement(searchBar);
		
		listOfContainers.add(bar);
	}

	/**
	 * @return the browsr
	 */
	public Browsr getBrowsr() {
		return browsr;
	}

	public void paint(Graphics g) {
		listOfContainers.stream().forEach(container -> container.paint(g));
	}
	
	public Container containerAt(int x, int y) {
		for(Container c: listOfContainers) {
			if (c.containsPoint(x, y)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * @return the listOfContainers
	 */
	public ArrayList<Container> getListOfContainers() {
		return listOfContainers;
	}

	/**
	 * @param listOfContainers the listOfContainers to set
	 */
	public void addToListOfContainers(Container container) {
		this.listOfContainers.add(container);
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
}
