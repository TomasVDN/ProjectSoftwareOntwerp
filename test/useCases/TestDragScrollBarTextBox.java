package useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.GUIElement;
import GUIElements.ScrollBar;
import GUIElements.ScrollableTextBox;
import GUIElements.SearchBar;
import canvaswindow.MyCanvasWindow;
import helperFunctions.UrlRunningWithSearchBar;

class TestDragScrollBarTextBox {
	
	private MyCanvasWindow mainWindow;

	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.11: Drag Scrollbar")
	// Use Case 4.11
	public void testDragScrollBarTextBox() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// SETUP WITH TEXTBOX
		// get searchBar scrollBar
		GUIElement guiElement = mainWindow.getWindowManager().getMainDialog().getSearchBarContainer().getElements().get(0);
		// check if it the guiElement is of ScrollableTextBox class
		assertTrue(guiElement.getClass().equals(ScrollableTextBox.class));
		ScrollableTextBox scrollableSearchBar = (ScrollableTextBox) guiElement;
		GUIElement guiElementTwo = scrollableSearchBar.getElements().get(1);
		// check if it the guiElement is of ScrollBar class
		assertTrue(guiElementTwo.getClass().equals(ScrollBar.class));
		ScrollBar searchBarScrollBar = (ScrollBar) guiElementTwo;
		SearchBar searchBar = mainWindow.getWindowManager().getSearchbar();
		searchBarScrollBar.setSmallBarPositionWithRatio(0); // dit is voor de zekerheid
		double searchBarScrollBarSmallBarPosition = searchBarScrollBar.getSmallBarPosition();
		
		// change the url to a very long string                 
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "thisisaverylongstringitissolongthatyoucantevenreadthissentenceproperlywithouthurtingyoureyes");
		int searchbarScrollbarPosition = searchBar.getEndY() + 15;
		int displacementSearchbar = 50;
		int searchbarScrollbarOffsetOriginal = searchBar.getxOffset();
		
		
		assertEquals(0, searchbarScrollbarOffsetOriginal); 
		
		// Step 4.11.1 MOVE
		// drag the vertical search scrollbar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 20, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, 20+displacementSearchbar, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 20+displacementSearchbar, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 20+displacementSearchbar, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		
		double searchBarScrollBarSmallBarPositionNew = searchBarScrollBar.getSmallBarPosition();
		int searchbarScrollbarOffsetNew = searchBar.getxOffset();

		// Step 4.11.2
		//check if the contents of the searchbar moved correctly
		assertEquals(searchBarScrollBarSmallBarPosition + displacementSearchbar, searchBarScrollBarSmallBarPositionNew);
		assertNotEquals(searchbarScrollbarOffsetOriginal, searchbarScrollbarOffsetNew);
		assertEquals((int) (-1 * searchBarScrollBar.getScrollBarRatio() * searchBar.getWidthText()), searchbarScrollbarOffsetNew);
		
	}
}
