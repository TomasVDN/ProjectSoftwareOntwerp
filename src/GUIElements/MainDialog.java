package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class MainDialog extends Container {
	
	private Container pageContainer;
	private Container searchBarContainer;
	private Container bookmarkBarContainer;
	private ArrayList<Container> allContainers;
	// test

	public MainDialog(int x, int y, int w, int h, Container page, Container searchBar, Container bookmarkBar) {
		super(x, y, w, h);
		this.pageContainer = page;
		this.searchBarContainer = searchBar;
		this.bookmarkBarContainer = bookmarkBar;
		this.allContainers = new ArrayList<Container>();
		allContainers.add(bookmarkBarContainer);
		allContainers.add(pageContainer);
		allContainers.add(searchBarContainer);
	}

	/**
	 * @return the page
	 */
	public Container getPageContainer() {
		return pageContainer;
	}

	/**
	 * @param page the page to set
	 */
	public void setPageContainer(Container page) {
		this.pageContainer = page;
	}

	/**
	 * @return the searchBar
	 */
	public Container getSearchBarContainer() {
		return searchBarContainer;
	}

	/**
	 * @param searchBar the searchBar to set
	 */
	public void setSearchBarContainer(Container searchBar) {
		this.searchBarContainer = searchBar;
	}

	/**
	 * @return the bookmarkBar
	 */
	public Container getBookmarkBarContainer() {
		return bookmarkBarContainer;
	}

	/**
	 * @param bookmarkBar the bookmarkBar to set
	 */
	public void setBookmarkBarContainer(Container bookmarkBar) {
		this.bookmarkBarContainer = bookmarkBar;
	}
	
	/**
	 * @param element the element to add
	 */
	public void addElement(GUIElement element) {
		this.getPageContainer().addElement(element);
	}
	
	/**
	 * @param element the element to add
	 */
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		this.getPageContainer().addMultipleElements(guiList);
	}
	
	/**
	 * Empties container and adds GUIElements from given guiList to the container.
	 * @param guiList
	 */
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.getPageContainer().resetAllElements(guiList);
	}
	
	@Override
	public void paint(Graphics g) {
		g.translate(getX(), getY());
		allContainers.stream().forEach(element -> element.paint(g));
		g.translate(-getX(), -getY());
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 * @param x - the x coordinate from the position to check
	 * @param y - the y coordinate from the position to check
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		for (GUIElement e: allContainers) {
			GUIElement gui= e.getGUIAtPosition(x, y);
			if (gui!=null) {
				return gui;
			}
		}
		return null;
	}

	/**
	 * @return the allContainers
	 */
	public ArrayList<Container> getAllContainers() {
		return allContainers;
	}
}
