package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class MainDialog extends Container {
	
	private Container pageContainer;
	private Container searchBarContainer;
	private Container bookmarkBarContainer;

	public MainDialog(int x, int y, int w, int h, Container page, Container searchBar, Container bookmarkBar) {
		super(x, y, w, h);
		this.pageContainer = page;
		this.searchBarContainer = searchBar;
		this.bookmarkBarContainer = bookmarkBar;
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
		this.getBookmarkBarContainer().paint(g);
		this.getPageContainer().paint(g);
		this.getSearchBarContainer().paint(g);
		g.translate(-getX(), -getY());
	}
}
