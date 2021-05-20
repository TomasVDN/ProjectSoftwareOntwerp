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

class TestSelectFrameVertical {

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
	@DisplayName("Use Case 4.9: Select Frame (Vertical)")
	// Use Case 4.9
	public void testSelectFrameVertical() throws InvocationTargetException, InterruptedException {
		
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// check if search bar is currently empty
		assertEquals("", mainWindow.getWindowManager().getURLFromSearchBar());
		
		// run a url to later check if both panels have the correct html file
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, urlOne);
		
		// check if the correct url is in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
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
		
		// click on the upperPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		
		// check if the upperPanel is clicked
		assertEquals(upperPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, upperPanel.isActive());
		assertEquals(false, lowerPanel.isActive());
		
		// check if the correct url is in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if upperPanel Has expected html code
		assertEquals(htmlCodeOne, upperPanel.getHTMLCode());
		
		// Step 4.9.1
		// click on the lowerPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.9.2
		// check if the lowerPanel is clicked
		assertEquals(lowerPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, lowerPanel.isActive());
		assertEquals(false, upperPanel.isActive());
		
		// check if the correct url is in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if lowerPanel Has expected html code
		assertEquals(htmlCodeOne, lowerPanel.getHTMLCode());
		
		// check if both panels have the same html code
		assertEquals(upperPanel.getHTMLCode(), lowerPanel.getHTMLCode());
		
		// run another url while lowerPanel is active
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, urlTwo);
		
		// check if the lowerPanel remained active
		assertEquals(lowerPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, lowerPanel.isActive());
		assertEquals(false, upperPanel.isActive());
		
		// check if the new url is in the searchbar
		assertEquals(urlTwo, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if the html code of lowerPanel has changed and the html code of upperPanel remained unchanged
		assertEquals(htmlCodeOne, upperPanel.getHTMLCode());
		assertNotEquals(htmlCodeTwo, upperPanel.getHTMLCode());
		assertNotEquals(htmlCodeOne, lowerPanel.getHTMLCode());
		assertEquals(htmlCodeTwo, lowerPanel.getHTMLCode());
		
		// click on the upperPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 240, 1, MouseEvent.BUTTON1, 0);
		
		// check if the upperPanel is clicked
		assertEquals(upperPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, upperPanel.isActive());
		assertEquals(false, lowerPanel.isActive());
		
		// check if the url changed in the search bar
		assertEquals(urlOne, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// run another url while upperPanel is active
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, urlThree);
		
		// check if the upperPanel remained active
		assertEquals(upperPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, upperPanel.isActive());
		assertEquals(false, lowerPanel.isActive());
		
		// check if the new url is in the searchbar
		assertEquals(urlThree, mainWindow.getWindowManager().getURLFromSearchBar());
		
		// check if the html code of upperPanel has changed and the html code of lowerPanel remained unchanged
		assertEquals(htmlCodeThree, upperPanel.getHTMLCode());
		assertNotEquals(htmlCodeTwo, upperPanel.getHTMLCode());
		assertNotEquals(htmlCodeThree, lowerPanel.getHTMLCode());
		assertEquals(htmlCodeTwo, lowerPanel.getHTMLCode());
		
		// click on the lowerPanel
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		// check if the lowerPanel is clicked
		assertEquals(lowerPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, lowerPanel.isActive());
		assertEquals(false, upperPanel.isActive());
		
		// check if the correct url is in the search bar
		assertEquals(urlTwo, mainWindow.getWindowManager().getURLFromSearchBar());
	}

}
