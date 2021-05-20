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

class TestSelectFrameHorizontal {

	private MyCanvasWindow mainWindow;
	
	private String urlOne = "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html";
	private String urlTwo = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";
	private String urlThree = "https://stevenhgs.github.io";
	private String htmlCodeOne = "<form action=\"browsrformactiontest.php\">\n"
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
	private String htmlCodeTwo = "<table>\n"
			+ "  <tr><td>HTML elements partially supported by Browsr:\n"
			+ "  <tr><td>\n"
			+ "    <table>\n"
			+ "      <tr><td><a href=\"a.html\">a</a><td>Hyperlink anchors\n"
			+ "      <tr><td><a href=\"table.html\">table</a><td>Tables\n"
			+ "      <tr><td><a href=\"tr.html\">tr</a><td>Table rows\n"
			+ "      <tr><td><a href=\"td.html\">td</a><td>Table cells containing table data\n"
			+ "    </table>\n"
			+ "</table>\n"
			+ "";
	private String htmlCodeThree = "<table>\n"
			+ "  <tr><td>Welcome to this BIG test page!\n"
			+ "  <tr><td>\n"
			+ "    <table>\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td><a href=\"form.html\">form</a>\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>veel tekst veel tekst veel tekst veel tekst veel tekst veel tekst veel tekst veel tekst veel tekst\n"
			+ "      <td><a href=\"goodwork.html\">Working link</a><td>Fully working hyperlink\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>tekst\n"
			+ "      <tr><td>derde laatste\n"
			+ "      <tr><td>voorlaatste\n"
			+ "      <tr><td>einde\n"
			+ "    </table>\n"
			+ "</table>\n"
			+ "";
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.9: Select Frame (Horizontal)")
	// Use Case 4.9
	public void testSelectFrameHorizontal() throws InvocationTargetException, InterruptedException {
		
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// check if search bar is currently empty
		assertEquals("", mainWindow.getWindowManager().getURLFromSearchBar());
		
		// run a url to later check if both panels have the correct html file
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, urlOne);
		
		// check if the correct url is in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
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
		
		// click on the leftPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the leftPanel is clicked
		assertEquals(leftPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, leftPanel.isActive());
		assertEquals(false, rightPanel.isActive());
		
		// check if the correct url is in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if leftPanel Has expected html code
		assertEquals(htmlCodeOne, leftPanel.getHTMLCode());
		
		// Step 4.9.1
		// click on the rightPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.9.2
		// check if the rightPanel is clicked
		assertEquals(rightPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, rightPanel.isActive());
		assertEquals(false, leftPanel.isActive());
		
		// check if the correct url is in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if rightPanel Has expected html code
		assertEquals(htmlCodeOne, rightPanel.getHTMLCode());
		
		// check if both panels have the same html code
		assertEquals(leftPanel.getHTMLCode(), rightPanel.getHTMLCode());
		
		// run another url while rightPanel is active
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, urlTwo);
		
		// check if the rightPanel remained active
		assertEquals(rightPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, rightPanel.isActive());
		assertEquals(false, leftPanel.isActive());
		
		// check if the new url is in the searchbar
		assertEquals(urlTwo, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if the html code of rightPanel has changed and the html code of leftPanel remained unchanged
		assertEquals(htmlCodeOne, leftPanel.getHTMLCode());
		assertNotEquals(htmlCodeTwo, leftPanel.getHTMLCode());
		assertNotEquals(htmlCodeOne, rightPanel.getHTMLCode());
		assertEquals(htmlCodeTwo, rightPanel.getHTMLCode());
		
		// click on the leftPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the leftPanel is clicked
		assertEquals(leftPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, leftPanel.isActive());
		assertEquals(false, rightPanel.isActive());
		
		// check if the url changed in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// run another url while leftPanel is active
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, urlThree);
		
		// check if the leftPanel remained active
		assertEquals(leftPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, leftPanel.isActive());
		assertEquals(false, rightPanel.isActive());
		
		// check if the new url is in the searchbar
		assertEquals(urlThree, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if the html code of leftPanel has changed and the html code of rightPanel remained unchanged
		assertEquals(htmlCodeThree, leftPanel.getHTMLCode());
		assertNotEquals(htmlCodeTwo, leftPanel.getHTMLCode());
		assertNotEquals(htmlCodeThree, rightPanel.getHTMLCode());
		assertEquals(htmlCodeTwo, rightPanel.getHTMLCode());
		
		// click on the rightPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the rightPanel is clicked
		assertEquals(rightPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, rightPanel.isActive());
		assertEquals(false, leftPanel.isActive());
		
		// check if the correct url is in the search bar
		assertEquals(urlTwo, mainWindow.getWindowManager().getURLFromSearchBar());
	}
		
}
