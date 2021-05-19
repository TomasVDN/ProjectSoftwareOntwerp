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

class TestSplitPaneVertical {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.7: Split Pane (Vertical)")
	// Use Case 4.7
	public void TestSplitPane() throws InvocationTargetException, InterruptedException {
		
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		UrlRunningWithSearchBar.runUrlWithSearchBar(mainWindow, "https://people.cs.kuleuven.be/~bart.jacobs/swop/browsrformtest.html");
		
		// click on control H
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 72, 'h', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		SplitHTMLDocument root = (SplitHTMLDocument) mainWindow.getWindowManager().getMainDialog().getDocumentArea();
		HTMLDocument leftPanel = ((ScrollableHTMLDocument) root.getLeftPanel()).getHtmlDocument();
		HTMLDocument rightPanel = ((ScrollableHTMLDocument) root.getRightPanel()).getHtmlDocument();
		
		//click on the left pane
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 69, 420, 1, MouseEvent.BUTTON1, 0);
		
		assertEquals(leftPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, leftPanel.isActive());
		assertEquals(false, rightPanel.isActive());
		
		//click on the right pane
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 420, 420, 1, MouseEvent.BUTTON1, 0);
		
		assertEquals(rightPanel, mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument());
		assertEquals(true, rightPanel.isActive());
		assertEquals(false, leftPanel.isActive());

		
		//check if the panes are different
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
		
		// check if both panes have expected HTML code
		assertEquals(expectedHTMLCode, leftPanel.getHTMLCode());
		assertEquals(expectedHTMLCode, rightPanel.getHTMLCode());
		
		// check if they contain the same HTMLcode
		assertEquals(leftPanel.getHTMLCode(), rightPanel.getHTMLCode());
		
	}
	
}
