package useCases;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.BookmarkDialog;
import GUIElements.BookmarkHyperlink;
import GUIElements.Container;
import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import GUIElements.SearchBar;
import GUIElements.TableCellGUI;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;

class TestActivateBookmark {
	
	private MyCanvasWindow mainWindow;

	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.4: Activate Bookmark main success scenario")
	// Use Case 4.5
	public void activateBookmarkSuccess() throws InvocationTargetException, InterruptedException {
		// create new bookmark
		String newBookmarkyperlinkString = "New Bookmark";
		Text newBookmarkHyperlinkText = new Text(0, 0, newBookmarkyperlinkString);
		String newBookmarkUrl = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, newBookmarkHyperlinkText, newBookmarkUrl);
		//TODO newBookmarkHyperlink.addHyperLinkListener(mainWindow.getWindowManager().getBrowsr());
		
		// add it to the bookmarkBar
		mainWindow.getWindowManager().getMainDialog().addBookmark(newBookmarkHyperlink);
		
		// get positions to click TODO misschien een betere manier vinden voor dit?
		// get Y position
		int yPosOfBookmarkBarContainer = mainWindow.getWindowManager().getMainDialog().getBookmarkBarContainer().getY();
		int yPosOfBookmarkBar = mainWindow.getWindowManager().getMainDialog().getBookmarkBar().getY();
		int yPosToClick = yPosOfBookmarkBarContainer + yPosOfBookmarkBar;
		
		// get X position
		int xPosOfBookmarkBarContainer = mainWindow.getWindowManager().getMainDialog().getBookmarkBarContainer().getX();
		int xPosOfBookmarkBar = mainWindow.getWindowManager().getMainDialog().getBookmarkBar().getX();
		int elementsInBookmarkBarOffSet = 0;
		ArrayList<TableCellGUI> cellsInBookmarkBar = mainWindow.getWindowManager().getMainDialog().getBookmarkBar().getGuiRows().get(0).getGuiElements();
		// get the width of all the elements in the bookmarkBar to click on the last bookmark
		for (TableCellGUI cell : cellsInBookmarkBar) {
			elementsInBookmarkBarOffSet += cell.getGui().getWidth();
		}
		int xPosToClick = xPosOfBookmarkBarContainer + xPosOfBookmarkBar + elementsInBookmarkBarOffSet;
		
		// check if the position that will be clicked is the last added bookmark
		// if these tests fail the check if the yPosToClick and xPosToClick are right
		GUIElement elementAtPositionThatWillBeClicked = mainWindow.getWindowManager().getMainDialog().getGUIAtPosition(xPosToClick, yPosToClick);
		assertThat(elementAtPositionThatWillBeClicked, instanceOf(BookmarkHyperlink.class));
		assertEquals(newBookmarkyperlinkString, ((BookmarkHyperlink) elementAtPositionThatWillBeClicked).getText());
		assertEquals(newBookmarkUrl, ((BookmarkHyperlink) elementAtPositionThatWillBeClicked).getUrl());
		
		// Step 4.4.1
		// click on the last added bookmark
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, xPosToClick, yPosToClick, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, xPosToClick, yPosToClick, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, xPosToClick, yPosToClick, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.4.2
		// check if everything gets loaded correctly
		SearchBar mainBar = mainWindow.getWindowManager().getSearchbar();
		
		int referenceBarContainerHeight = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getHeight();
		int referenceBarContainerWidth = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getWidth();
		int referenceBarContainerX = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getX();
		int referenceBarContainerY = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getY();
				
		int mainBarContainerHeight =mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getHeight();
		int mainBarContainerWidth = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getWidth();
		int mainBarContainerX = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getX();
		int mainBarContainerY = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getY();
		
		//checks if the windows are correctly placed, even after searching
		assertEquals(mainBarContainerHeight, referenceBarContainerHeight);
		assertEquals(mainBarContainerWidth, referenceBarContainerWidth);
		assertEquals(mainBarContainerX, referenceBarContainerX);
		assertEquals(mainBarContainerY, referenceBarContainerY);

		//testing GUI elements
		Container pageContainer = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		TableGUI pageTable = (TableGUI) (pageContainer.getElements().get(0));
		TableRowGUI pageTableRow1 = pageTable.getGuiRows().get(0);
		TableRowGUI pageTableRow2 = pageTable.getGuiRows().get(1);
		
		Text pageTableRow1Text = (Text) (pageTableRow1.getGuiElements().get(0).getGui());
		
		TableGUI pageTableRow2Table = (TableGUI) (pageTableRow2.getGuiElements().get(0).getGui());
		
		Hyperlink pageTableRow2TableHyperlink1 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(0).getGuiElements().get(0).getGui());
		Hyperlink pageTableRow2TableHyperlink2 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(1).getGuiElements().get(0).getGui());
		Hyperlink pageTableRow2TableHyperlink3= (Hyperlink) (pageTableRow2Table.getGuiRows().get(2).getGuiElements().get(0).getGui());
		Hyperlink pageTableRow2TableHyperlink4 = (Hyperlink) (pageTableRow2Table.getGuiRows().get(3).getGuiElements().get(0).getGui());

		Text pageTableRow2TableHyperlink1Text = (Text) (pageTableRow2Table.getGuiRows().get(0).getGuiElements().get(1).getGui());
		Text pageTableRow2TableHyperlink2Text = (Text) (pageTableRow2Table.getGuiRows().get(1).getGuiElements().get(1).getGui());
		Text pageTableRow2TableHyperlink3Text = (Text) (pageTableRow2Table.getGuiRows().get(2).getGuiElements().get(1).getGui());
		Text pageTableRow2TableHyperlink4Text = (Text) (pageTableRow2Table.getGuiRows().get(3).getGuiElements().get(1).getGui());

		//check hyperlink urls
		assertEquals("HTML elements partially supported by BrowsrController:", pageTableRow1Text.getText());
		assertEquals("a.html", pageTableRow2TableHyperlink1.getUrl());
		assertEquals("table.html", pageTableRow2TableHyperlink2.getUrl());
		assertEquals("tr.html", pageTableRow2TableHyperlink3.getUrl());
		assertEquals("td.html", pageTableRow2TableHyperlink4.getUrl());
		
		//check hyperlink text
		assertEquals("a", pageTableRow2TableHyperlink1.getText());
		assertEquals("table", pageTableRow2TableHyperlink2.getText());
		assertEquals("tr", pageTableRow2TableHyperlink3.getText());
		assertEquals("td", pageTableRow2TableHyperlink4.getText());
		
		//check table text
		assertEquals("Hyperlink anchors", pageTableRow2TableHyperlink1Text.getText());
		assertEquals("Tables", pageTableRow2TableHyperlink2Text.getText());
		assertEquals("Table rows", pageTableRow2TableHyperlink3Text.getText());
		assertEquals("Table cells containing table data", pageTableRow2TableHyperlink4Text.getText());
		
		//check the current active element is null and check the url
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", mainBar.getText());
	}

}
