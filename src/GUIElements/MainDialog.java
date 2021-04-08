package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import GUIElements.SearchBar;
import events.EventReader;

public class MainDialog extends Container {
	
	private Container pageContainer;
	private Container searchBarContainer;
	private Container bookmarkBarContainer;
	private ArrayList<Container> allContainers;
	
	
	private SearchBar searchbar;
	
	private TableGUI bookmarkBar;

	public MainDialog(int x, int y, int w, int h, EventReader eventReader, Container pageContainer, Container searchBarContainer, Container bookmarkBarContainer) {
		super(x, y, w, h);
		this.pageContainer = pageContainer;
		this.searchBarContainer = searchBarContainer;
		this.bookmarkBarContainer = bookmarkBarContainer;
		this.allContainers = new ArrayList<Container>();
		
		// set up searchbar
		SearchBar searchBar = new SearchBar(10, 10, this.getWidth() - 20, 50, eventReader);
		this.setSearchbar(searchBar);
		this.getSearchBarContainer().addElement(this.getSearchbar());
		
		// set up bookmarkBar
		Text t = new Text(0, 0, "hello");
		TableCellGUI c = new TableCellGUI(t, 0, 0, 10, 10);
		ArrayList<TableCellGUI> bookmarkCells = new ArrayList<TableCellGUI>();
		bookmarkCells.add(c);
		TableRowGUI emptyTableRow = new TableRowGUI(bookmarkCells, 0, 0);
		ArrayList<TableRowGUI> bookmarkRow = new ArrayList<TableRowGUI>();
		bookmarkRow.add(emptyTableRow);
		TableGUI bookmarkBar = new TableGUI(bookmarkRow, 10, 10);
		this.setBookmarkBar(bookmarkBar);
		this.getBookmarkBarContainer().addElement(this.getBookmarkBar());
		
		
		allContainers.add(bookmarkBarContainer);
		allContainers.add(pageContainer);
		allContainers.add(searchBarContainer);
		
		Text t2 = new Text(0, 0, "link");
		Hyperlink hyperlinkTest = new Hyperlink(0, 0, t2, "https://konikoko.github.io/", eventReader);
		this.addBookmark(hyperlinkTest);
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
	

	public TableGUI getBookmarkBar() {
		return bookmarkBar;
	}

	public void setBookmarkBar(TableGUI bookmarkBar) {
		this.bookmarkBar = bookmarkBar;
	}
	
	public void addBookmark(Hyperlink newBookmark) {
		ArrayList<TableCellGUI> bookmarkCells = this.bookmarkBar.getGuiRows().get(0).getGuiElements();
		TableCellGUI newBookmarkCell = new TableCellGUI(newBookmark, 0, 0, 10, 10); // TODO die posities 
		bookmarkCells.add(newBookmarkCell);
		this.getBookmarkBar().getGuiRows().get(0).setGuiElements(bookmarkCells); // TODO die get(0) misschien op een andere manier doen?
		this.getBookmarkBar().updateTableCells();
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
