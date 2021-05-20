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
import GUIElements.Pane;
import GUIElements.SaveDialog;
import GUIElements.ScrollableHTMLDocument;
import GUIElements.ScrollableTextBox;
import GUIElements.SplitHTMLDocument;
import GUIElements.Text;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;
import helperFunctions.UrlRunningWithSearchBar;

class TestDragSeperatorVertical {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.10: Drag Seperator (Vertical)")
	// Use Case 4.10
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// run a url to later check if both panels have the correct html file
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		
		// Step 4.10.0 SETUP
		// click on control H
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		/// get panel objects
		Pane root = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		// check if it the documentArea is of SPlitHTMLDocument class
		assertTrue(root.getClass().equals(SplitHTMLDocument.class));
		SplitHTMLDocument castedRoot = (SplitHTMLDocument) root;
		HTMLDocument leftPanel = ((ScrollableHTMLDocument) castedRoot.getLeftPanel()).getHtmlDocument();
		HTMLDocument rightPanel = ((ScrollableHTMLDocument) castedRoot.getRightPanel()).getHtmlDocument();
		int seperatorbarX = castedRoot.getLeftPanel().getEndX() + root.getX();
		int leftPanelOriginalWidth = castedRoot.getLeftPanel().getWidth();
		int rightPanelOriginalWidth = castedRoot.getRightPanel().getWidth();
		int displacement = 50;

		// Step 4.10.1 
		// drag the seperator bar
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, seperatorbarX, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_DRAGGED, seperatorbarX+displacement, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, seperatorbarX+displacement, 300, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, seperatorbarX+displacement, 300, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.10.2
		//Check if the windows resized correctly
		int leftPanelNewHeight = castedRoot.getLeftPanel().getWidth();
		int rightPanelNewHeight = castedRoot.getRightPanel().getWidth();
		assertEquals(leftPanelOriginalWidth+displacement, leftPanelNewHeight, "The seperator didn't move correctly");
		assertEquals(rightPanelOriginalWidth-displacement, rightPanelNewHeight, "The seperator didn't move correctly");

		TestGUI testgui1 = new TestGUI(mainWindow, "TestDragSeperatorVertical");

	}
	
}
