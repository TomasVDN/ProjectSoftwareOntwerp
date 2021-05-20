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

class TestDragSeperatorHorizontal {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.10: Drag Seperator (Horizontal)")
	// Use Case 4.10
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// run a url to later check if both panels have the correct html file
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		
		// Step 4.10.0 SETUP
		// click on control V
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 86, 'v', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 86, 'v', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// get panel objects
		SplitHTMLDocument root = (SplitHTMLDocument) mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		HTMLDocument upperPanel = ((ScrollableHTMLDocument) root.getLeftPanel()).getHtmlDocument();
		HTMLDocument lowerPanel = ((ScrollableHTMLDocument) root.getRightPanel()).getHtmlDocument();
		int seperatorbarY = root.getLeftPanel().getEndY() + root.getY();
		int upperPanelOriginalHeight = root.getLeftPanel().getHeight();
		int lowerPanelOriginalHeight = root.getRightPanel().getHeight();
		int displacement = 50;

		// Step 4.10.1 
		// drag the seperator bar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 10, seperatorbarY, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, 10, seperatorbarY+displacement, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 10, seperatorbarY+displacement, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 10, seperatorbarY+displacement, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.10.2
		//Check if the windows resized correctly
		int upperPanelNewHeight = root.getLeftPanel().getHeight();
		int lowerPanelNewHeight = root.getRightPanel().getHeight();
		assertEquals(upperPanelOriginalHeight+displacement, upperPanelNewHeight, "The seperator didn't move correctly");
		assertEquals(lowerPanelOriginalHeight-displacement, lowerPanelNewHeight, "The seperator didn't move correctly");

		TestGUI testgui1 = new TestGUI(mainWindow, "TestDragSeperatorHorizontal");

	}
	
}
