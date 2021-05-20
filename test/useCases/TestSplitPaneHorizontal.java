package useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.HTMLDocument;
import GUIElements.Pane;
import GUIElements.ScrollableHTMLDocument;
import GUIElements.SplitHTMLDocument;
import canvaswindow.MyCanvasWindow;
import helperFunctions.UrlRunningWithSearchBar;

class TestSplitPaneHorizontal {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.7: Split Pane (Horizontal)")
	// Use Case 4.7
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// run a url to later check if both panels have the correct html file
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		
		// Step 4.7.1
		// click on control H
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// get panel objects
		Pane root = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		// check if it the documentArea is of SPlitHTMLDocument class
		assertTrue(root.getClass().equals(SplitHTMLDocument.class));
		SplitHTMLDocument castedRoot = (SplitHTMLDocument) root;
		HTMLDocument leftPanel = ((ScrollableHTMLDocument) castedRoot.getLeftPanel()).getHtmlDocument();
		HTMLDocument rightPanel = ((ScrollableHTMLDocument) castedRoot.getRightPanel()).getHtmlDocument();
		
		//click on the left panel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the leftPanel is clicked
		assertEquals(leftPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, leftPanel.isActive());
		assertEquals(false, rightPanel.isActive());
		
		// click on the right panel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the rightPanel is clicked
		assertEquals(rightPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, rightPanel.isActive());
		assertEquals(false, leftPanel.isActive());
		
		// Step 4.7.2
		//check if the panels are different
		assertNotEquals(leftPanel, rightPanel,"The original pane has not been split correctly");
		
		String expectedHTMLCode = "<form action=\"browsrformactiontest.php\">\n"
				+ "  <table>\n"
				+ "    <tr><td>List words from the Woordenlijst Nederlandse Taal\n"
				+ "    <tr><td>\n"
				+ "      <table>\n"
				+ "        <tr><td>Words that start with<td><input type=\"text\" name=\"starts_with\">\n"
				+ "        <tr><td>Maximum number of words to show<td><input type=\"text\" name=\"max_nb_results\">\n"
				+ "      </table>\n"
				+ "    <tr><td><input type=\"submit\">\n"
				+ "  </table>\n"
				+ "</form>\n"
				+ "";
		
		// check if both panels have the expected HTML code
		assertEquals(expectedHTMLCode, leftPanel.getHTMLCode());
		assertEquals(expectedHTMLCode, rightPanel.getHTMLCode());
		
		// check if the panels contain the same HTMLcode
		assertEquals(leftPanel.getHTMLCode(), rightPanel.getHTMLCode());
		
		TestGUI testgui1 = new TestGUI(mainWindow, "TestSplitPaneHorizontal");
	}
	
}
