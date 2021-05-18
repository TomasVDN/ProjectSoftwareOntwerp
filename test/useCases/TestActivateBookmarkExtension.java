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

class TestActivateBookmarkExtension {

	private MyCanvasWindow mainWindow;

	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.4.2a: Activate Bookmark with malformed url")
	// Use Case 4.5
	public void activateBookmarkMalformedUrl() throws InvocationTargetException, InterruptedException {
		// create new bookmark
		String newBookmarkyperlinkString = "New Bookmark";
		Text newBookmarkHyperlinkText = new Text(0, 0, newBookmarkyperlinkString);
		String newBookmarkUrl = "A wrong url";
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, newBookmarkHyperlinkText, newBookmarkUrl);
		// TODO newBookmarkHyperlink.addHyperLinkListener(mainWindow.getWindowManager().getBrowsr());
		
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
		
		// Step 4.4.2.a
		//check the error screen
		Container pageContainer1 = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		Text pageErrorText = (Text) (pageContainer1.getElements().get(0));
				
		assertEquals("Error occured. Make sure you entered a valid URL.", pageErrorText.getText());
	}
	
}
