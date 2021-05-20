package useCases;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Button;
import GUIElements.GUIElement;
import GUIElements.HTMLDocument;
import GUIElements.SaveDialog;
import GUIElements.ScrollableHTMLDocument;
import GUIElements.ScrollableTextBox;
import GUIElements.SplitHTMLDocument;
import GUIElements.Text;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;
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
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// run a url to later check if both panels have the correct html file
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "https://stevenhgs.github.io/");
		
		// Step 4.11.0 SETUP		
		// get panel objects
		HTMLDocument root = (HTMLDocument) mainWindow.getWindowManager().getMainDialog().getDocumentArea().getActiveHTMLDocument();
		int horizontalScrollbarYposition = 595;
		int verticalScrollbarXposition = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getWidth();
		int verticalScrollbarYposition = mainWindow.getWindowManager().getMainDialog().getDocumentArea().getHeight() / 2;
		int originalOffsetRootX = root.getxOffset();
		int originalOffsetRootY = root.getyOffset();
		int displacementHorizontal = 65;
		int displacementVertical = 50;

		// Step 4.11.1 MOVE
		// drag the vertical scrollbar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, verticalScrollbarXposition, verticalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, verticalScrollbarXposition, verticalScrollbarYposition+displacementVertical, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, verticalScrollbarXposition, verticalScrollbarYposition+displacementVertical, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, verticalScrollbarXposition, verticalScrollbarYposition+displacementVertical, 1, MouseEvent.BUTTON1, 0);
	
		// drag the horizontal scrollbar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 20, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, 20+displacementHorizontal, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 20+displacementHorizontal, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 20+displacementHorizontal, horizontalScrollbarYposition, 1, MouseEvent.BUTTON1, 0);
			
		
		// Step 4.11.2 CHECK
		//Check if the content shows correctly
		int newOffsetRootX = root.getxOffset();
		int newOffsetRootY = root.getyOffset();
		assertNotEquals(newOffsetRootX,originalOffsetRootX,"The content didn't move");
		assertNotEquals(newOffsetRootY,originalOffsetRootY,"The content didn't move");

		// Step 4.11.0 SETUP WITH TEXTBOX
		// change the url to a very long string                 
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "thisisaverylongstringitissolongthatyoucantevenreadthissentenceproperlywithouthurtingyoureyes");
		int searchbarScrollbarPosition = mainWindow.getWindowManager().getSearchbar().getEndY() + 15;
		int displacementSearchbar = 50;
		int searchbarScrollbarOffsetOriginal = mainWindow.getWindowManager().getSearchbar().getxOffset();
		
		// Step 4.11.1 MOVE
		// drag the vertical search scrollbar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 20, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, 20+displacementSearchbar, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 20+displacementSearchbar, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 20+displacementSearchbar, searchbarScrollbarPosition, 1, MouseEvent.BUTTON1, 0);
		int searchbarScrollbarOffsetNew = mainWindow.getWindowManager().getSearchbar().getxOffset();

		// Step 4.11.2 CHECK
		//check if the contents of the searchbar moved correctly
		assertNotEquals(searchbarScrollbarOffsetOriginal,searchbarScrollbarOffsetNew,"The content didn't move");
		
		//testGUI
		TestGUI testgui1 = new TestGUI(mainWindow, "TestDragScrollbar");

	}
	
}
