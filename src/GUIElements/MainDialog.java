package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import facades.Browsr;

public class MainDialog extends Dialog {
	
	private Container documentArea;
	private Container searchBarContainer;
	private Container bookmarkBarContainer;
	private ArrayList<Container> allContainers;
	
	private SearchBar searchbar;
	private TableGUI bookmarkBar;

	public MainDialog(int x, int y, int w, int h, Container pageContainer, Container searchBarContainer, Container bookmarkBarContainer, Browsr browsr) {
		super(x, y, w, h);
		
		this.setContainers(pageContainer, searchBarContainer, bookmarkBarContainer);
		this.initSearchBar(browsr);
		this.initBookmarkBar();
		
		//Used for testing purpose TODO
		Text t2 = new Text(0, 0, "link");
		BookmarkHyperlink hyperlinkTest = new BookmarkHyperlink(0, 0, t2, "https://konikoko.github.io/");
		hyperlinkTest.addHyperLinkListener(browsr);
		this.addBookmark(hyperlinkTest);
		Text t3 = new Text(0, 0, "form");
		BookmarkHyperlink hyperlinkTest2 = new BookmarkHyperlink(0, 0, t3, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		hyperlinkTest2.addHyperLinkListener(browsr);
		this.addBookmark(hyperlinkTest2);
		
	}
	
	/**
	 * Initializes the containers.
	 * @param documentArea
	 * @param searchBarContainer
	 * @param bookmarkBarContainer
	 */
	private void setContainers(Container documentArea, Container searchBarContainer, Container bookmarkBarContainer) {
		this.documentArea = documentArea;
		this.searchBarContainer = searchBarContainer;
		this.bookmarkBarContainer = bookmarkBarContainer;
		this.allContainers = new ArrayList<Container>();
		
		int[] middleContainer = new int[] {documentArea.getWidth()/2,documentArea.getHeight()/2};
		documentArea.addElement(new SeperatorBar(documentArea, middleContainer, Direction.VERTICAL));//TODO haal weg
		
		allContainers.add(searchBarContainer);
		allContainers.add(bookmarkBarContainer);
		allContainers.add(documentArea);
	}
	
	/**
	 * Initializes the searchBar of this MainDialog.
	 * @param browsr
	 */
	private void initSearchBar(Browsr browsr) {
		SearchBar searchBar = new SearchBar(10, 10, this.getWidth() - 20, 50, browsr);
		this.setSearchbar(searchBar);
		this.getSearchBarContainer().addElement(searchBar);
	}
	
	/**
	 * Initializes the bookmarkBar of this MainDialog. Uses a table to line up all the elements.
	 */
	private void initBookmarkBar() {
		Text t = new Text(0, 0, "Bookmarks:");
		TableCellGUI c = new TableCellGUI(t, 0, 0, 0, 0);
		ArrayList<TableCellGUI> bookmarkCells = new ArrayList<TableCellGUI>();
		bookmarkCells.add(c);
		TableRowGUI emptyTableRow = new TableRowGUI(bookmarkCells, 0, 0,5);
		ArrayList<TableRowGUI> bookmarkRow = new ArrayList<TableRowGUI>();
		bookmarkRow.add(emptyTableRow);
		TableGUI bookmarkBar = new TableGUI(bookmarkRow, 10, 10);//new TableGUIWithOffset(bookmarkRow, 10, 10, 5);
		this.setBookmarkBar(bookmarkBar);
		this.getBookmarkBarContainer().addElement(this.getBookmarkBar());
	}

	/**
	 * @return the page
	 */
	public Container getDocumentArea() {
		return documentArea;
	}

	/**
	 * @param page the page to set
	 */
	public void setDocumentArea(Container page) {
		this.documentArea = page;
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
	
	/**
	 * Adds a bookmark to the BookmarkBar of this MainDialog
	 * @param newBookmark
	 */
	public void addBookmark(BookmarkHyperlink newBookmark) {
		this.bookmarkBar.addGUITo(newBookmark, 0);
	}
	
	/**
	 * @param element the element to add
	 */
	@Override
	public void addElement(GUIElement element) {
		this.getDocumentArea().addElement(element);
	}
	
	/**
	 * @param guiList the elements to add
	 */
	@Override
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		this.getDocumentArea().addMultipleElements(guiList);
	}
	
	/**
	 * Empties container and adds GUIElements from given guiList to the container.
	 * @param guiList
	 */
	@Override
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.getDocumentArea().resetAllElements(guiList);
	}
	
	/**
	 * Paints all the components in this dialog.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics newG= g.create(getX(), getY(), getWidth(), getHeight());
		allContainers.stream().forEach(element -> element.paint(newG));
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
	 * @return this.allContainers
	 */
	public ArrayList<Container> getAllContainers() {
		return allContainers;
	}



}
