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
		// click on control V
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 86, 'v', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 86, 'v', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// get panel objects
		Pane root = mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		// check if it the documentArea is of SPlitHTMLDocument class
		assertTrue(root.getClass().equals(SplitHTMLDocument.class));
		SplitHTMLDocument castedRoot = (SplitHTMLDocument) root;
		HTMLDocument upperPanel = ((ScrollableHTMLDocument) castedRoot.getLeftPanel()).getHtmlDocument();
		HTMLDocument lowerPanel = ((ScrollableHTMLDocument) castedRoot.getRightPanel()).getHtmlDocument();
		
		// click on the upper panel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		
		// check if the upperPanel is clicked
		assertEquals(upperPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, upperPanel.isActive());
		assertEquals(false, lowerPanel.isActive());
		
		// click on the Lower panel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the lowerPanel is clicked
		assertEquals(lowerPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, lowerPanel.isActive());
		assertEquals(false, upperPanel.isActive());
		
		// Step 4.7.2
		// check if the panels are different
		assertNotEquals(upperPanel, lowerPanel,"The original pane has not been split correctly");
		
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
		assertEquals(expectedHTMLCode, upperPanel.getHTMLCode());
		assertEquals(expectedHTMLCode, lowerPanel.getHTMLCode());
		
		// check if the panels contain the same HTMLcode
		assertEquals(upperPanel.getHTMLCode(), lowerPanel.getHTMLCode());
		
		TestGUI testgui1 = new TestGUI(mainWindow, "TestSplitPaneHorizontal");

	}
	
}
