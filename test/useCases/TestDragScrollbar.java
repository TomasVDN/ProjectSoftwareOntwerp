package useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Direction;
import GUIElements.HTMLDocument;
import GUIElements.Pane;
import GUIElements.ScrollBar;
import GUIElements.ScrollableHTMLDocument;
import canvaswindow.MyCanvasWindow;
import helperFunctions.UrlRunningWithSearchBar;

class TestDragScrollbar {

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
	public void testDragScrollBar() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// run a url to load a big page
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "https://stevenhgs.github.io/");
				
		// get document area objects
		Pane documentArea = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		
		// check if it the documentArea is of ScrollableHTMLDocument class
		assertTrue(documentArea.getClass().equals(ScrollableHTMLDocument.class));
		
		// get html document objects
		ScrollableHTMLDocument scrollableHTMLDocument = (ScrollableHTMLDocument) documentArea;
		HTMLDocument htmlDocument = scrollableHTMLDocument.getHtmlDocument();
		
		// check if the first two elements of documentArea are scrollBars
		assertTrue(documentArea.getElements().get(0).getClass().equals(ScrollBar.class));
		assertTrue(documentArea.getElements().get(1).getClass().equals(ScrollBar.class));
		ScrollBar horizontalScrollBar = (ScrollBar) documentArea.getElements().get(0);
		ScrollBar verticalScrollBar = (ScrollBar) documentArea.getElements().get(1);
		double horizontalSmallBarPosition = horizontalScrollBar.getSmallBarPosition();
		double verticalSmallBarPosition = verticalScrollBar.getSmallBarPosition();
		
		// check if the scrollbars are in the right direction
		assertEquals(Direction.HORIZONTAL, horizontalScrollBar.getDirection());
		assertEquals(Direction.VERTICAL, verticalScrollBar.getDirection());
		
		// get positions to click on the scrollbars
		int horizontalScrollbarYposition = 595;
		int verticalScrollbarXposition = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getWidth();
		int verticalScrollbarYposition = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getHeight() / 2;
		int originalOffsetX = htmlDocument.getxOffset();
		int originalOffsetY = htmlDocument.getyOffset();
		int displacementHorizontal = 65;
		int displacementVertical = 50;
		
		// off sets should be zero in the beginning
		assertEquals(0, originalOffsetX);
		assertEquals(0, originalOffsetY);

		// Step 4.11.1
		// drag the vertical scrollbar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, verticalScrollbarXposition, verticalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, verticalScrollbarXposition, verticalScrollbarYposition+displacementVertical, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, verticalScrollbarXposition, verticalScrollbarYposition+displacementVertical, 1, MouseEvent.BUTTON1, 0);
		
		double verticalSmallBarPositionNew = verticalScrollBar.getSmallBarPosition();
		int newOffsetXAfterVertical = htmlDocument.getxOffset();
		int newOffsetYAfterVertical = htmlDocument.getyOffset();
		
		// Step 4.11.2
		// Check if the content has moved
		assertEquals(verticalSmallBarPosition + displacementVertical, verticalSmallBarPositionNew);
		assertEquals(newOffsetXAfterVertical, originalOffsetX); // the x offset should not have changed after moving the vertical scrollbar
		assertNotEquals(newOffsetYAfterVertical, originalOffsetY); // y offset should have changed 
		assertEquals((int) (-1 * verticalScrollBar.getScrollBarRatio() * htmlDocument.maxYCoordinatOfElements()), newOffsetYAfterVertical);
	
		// Step 4.11.1
		// drag the horizontal scrollbar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 20, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, 20+displacementHorizontal, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 20+displacementHorizontal, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		
		double horizontalSmallBarPositionNew = horizontalScrollBar.getSmallBarPosition();
		int newOffsetXAfterHorizontal = htmlDocument.getxOffset();
		int newOffsetYAfterHorizontal = htmlDocument.getyOffset();
		
		// Step 4.11.2
		// Check if the content has moved
		assertEquals(horizontalSmallBarPosition + displacementHorizontal, horizontalSmallBarPositionNew);
		assertEquals(newOffsetYAfterHorizontal, newOffsetYAfterVertical); // the y offset should not have changed after moving the horizontal scrollbar
		assertNotEquals(newOffsetXAfterHorizontal, newOffsetXAfterVertical); // x offset should have changed 
		assertEquals((int) (-1 * horizontalScrollBar.getScrollBarRatio() * htmlDocument.maxXCoordinateOfElements()), newOffsetXAfterHorizontal);

		//testGUI
		TestGUI testgui1 = new TestGUI(mainWindow, "TestDragScrollbar");
	}
}
