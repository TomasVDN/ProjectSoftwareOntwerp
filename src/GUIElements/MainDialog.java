package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import facades.BrowsrController;

public class MainDialog extends Dialog  {
	
	private Pane documentArea;
	private HTMLDocument originalDocumentArea;
	private HTMLDocument activeHTMLDocument;
	private Container searchBarContainer;
	private Container bookmarkBarContainer;
	private ArrayList<Container> allContainers;

	private final int BAR_SIZE = 60;
	private final int BOOKMARK_SIZE = 60;
	
	private SearchBar searchbar;
	private TableGUI bookmarkBar;

	/**
	 * Constructor of the main
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param browsrController
	 */
	public MainDialog(int x, int y, int w, int h, BrowsrController browsrController) {
		super(x, y, w, h);
		
		this.initContainers(browsrController);
		this.initSearchBar(browsrController);
		this.initBookmarkBar();
		
		//Used for testing purpose TODO
		// BookmarkHyperlink 1
		Text t1 = new Text(0, 0, "link");
		BookmarkHyperlink hyperlinkTest1 = new BookmarkHyperlink(0, 0, t1, "https://konikoko.github.io/");
		hyperlinkTest1.addHyperLinkListener(browsrController);
		this.addBookmark(hyperlinkTest1);
		// BookmarkHyperlink 2
		Text t2 = new Text(0, 0, "form");
		BookmarkHyperlink hyperlinkTest2 = new BookmarkHyperlink(0, 0, t2, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		hyperlinkTest2.addHyperLinkListener(browsrController);
		this.addBookmark(hyperlinkTest2);
		// BookmarkHyperlink 3
		Text t3 = new Text(0, 0, "bigPage");
		BookmarkHyperlink hyperlinkTest3 = new BookmarkHyperlink(0, 0, t3, "https://stevenhgs.github.io/");
		hyperlinkTest3.addHyperLinkListener(browsrController);
		this.addBookmark(hyperlinkTest3);
	}
	
	/**
	 * Initializes the containers.
	 * @param documentArea
	 * @param searchBarContainer
	 * @param bookmarkBarContainer
	 */
	private void initContainers(BrowsrController browsrController) {
		//Initialize the searchbar container and bookmark container
		this.searchBarContainer = new Container(0,0,this.getWidth(),BAR_SIZE);
		this.bookmarkBarContainer = new Container(0,BAR_SIZE,this.getWidth(),BOOKMARK_SIZE);
		ScrollableHTMLDocument documentArea = new ScrollableHTMLDocument(0, BAR_SIZE + BOOKMARK_SIZE,new HTMLDocument(0, 0, this.getWidth()-10, this.getHeight() - BAR_SIZE - BOOKMARK_SIZE-10, "", "Welcome my friend, take a seat and enjoy your surfing."));
		this.setActiveHTMLDocument(documentArea.getHtmlDocument());

		documentArea.setActiveUnselect(true);

		// Add a reloadListener to the HTMLDocument, and load the page
		documentArea.getHtmlDocument().addReloadListener(browsrController);
		documentArea.getHtmlDocument().loadPage();
		
		this.originalDocumentArea = documentArea.getHtmlDocument().copy();
		this.documentArea = documentArea;
		
		this.allContainers = new ArrayList<Container>();
				
		allContainers.add(searchBarContainer);
		allContainers.add(bookmarkBarContainer);
		allContainers.add(documentArea);
	}
	
	/**
	 * Initializes the searchBar of this MainDialog.
	 * @param browsrController
	 */

	private void initSearchBar(BrowsrController browsrController) {
		SearchBar searchBar = new SearchBar(0, 0, this.getWidth() - 30, 40, browsrController);
		this.setSearchbar(searchBar);
		ScrollableTextBox scrollableSearchBar = new ScrollableTextBox(10 ,10 ,searchBar);
		this.getSearchBarContainer().addElement(scrollableSearchBar);
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
	public Pane getDocumentArea() {
		return documentArea;
	}

	/**
	 * @param page the page to set
	 */
	public void setDocumentArea(Pane page) {
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
	 * Paints all the components in this dialog.
	 */public void loadHTMLToGivenHTMLDocument(HTMLDocument htmlDocument, ArrayList<GUIElement> GUIElements, String path, String code,BrowsrController browsrController) { //TODO rename
			htmlDocument.loadHTML(GUIElements, path, code);
		}
	@Override
	public void paint(Graphics g) {
		Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);
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
	
	
	@Override
	public void handleClickLeftMouse(int x, int y, int clickCount, int modifiers)	{
		// activate the GUIElement at the given position
		changeElementWithKeyboardFocus(this.getGUIAtPosition(x, y));
		
		//sets the clicked panel to active
		HTMLDocument newActiveHTML = documentArea.setHTMLDocumentActive(x, y);
		if(newActiveHTML!=null) {
			this.setActiveHTMLDocument(newActiveHTML);
			this.changeSearchBar(newActiveHTML.getUrl());
		}
	}
	
	public void changeSearchBar(String url) {
		this.getSearchbar().replaceBox(url);
	}
	

	/**
	 * @return this.allContainers
	 */
	public ArrayList<Container> getAllContainers() {
		return allContainers;
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		if (modifiersEx == 128) {
			// ctrl + H
			if (keyCode == 72) {
				allContainers.remove(documentArea); //TODO smelly code
				documentArea = documentArea.splitActiveHTMLDocumentVertical();
				allContainers.add(documentArea);
			}
		}
		
		if (modifiersEx == 128) {
			if (keyCode == 86) {
				//ctrl + V
				allContainers.remove(documentArea); //TODO smelly code
				documentArea = documentArea.splitActiveHTMLDocumentHorizontal();
				allContainers.add(documentArea);
			}
		}
		
		if (modifiersEx == 128) {
			if (keyCode == 88) {
				//ctrl + X
				allContainers.remove(documentArea);
				//documentArea.resetActiveHTMLDocument();
				
				documentArea = documentArea.deleteActiveHTMLDocument();
				if (documentArea == null) { //TODO bug & smelly code
					ScrollableHTMLDocument originalPage = new ScrollableHTMLDocument(0, BAR_SIZE + BOOKMARK_SIZE,originalDocumentArea.copy());
					documentArea = originalPage;
					documentArea.setActiveUnselect(true);
					this.setActiveHTMLDocument(originalPage.getHtmlDocument());
				}
				this.setActiveHTMLDocument(documentArea.getActiveHTMLDocument());
				allContainers.add(documentArea);
			}
		}
				
		if (super.elementWithKeyBoardFocus != null & modifiersEx != 128) {
			super.elementWithKeyBoardFocus.handleKeyEvent(keyCode, keyChar, modifiersEx);
		}
	}

	public HTMLDocument getActiveHTMLDocument() {
		return activeHTMLDocument;
	}

	public void setActiveHTMLDocument(HTMLDocument activeHTMLDocument) {
		this.activeHTMLDocument = activeHTMLDocument;
	}
	

}