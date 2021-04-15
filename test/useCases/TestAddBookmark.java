package useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.BookmarkDialog;
import GUIElements.BookmarkHyperlink;
import GUIElements.TableCellGUI;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import canvaswindow.MyCanvasWindow;

class TestAddBookmark {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.5: Add Bookmark main success scenario")
	// Use Case 4.5
	public void enterUrlNoRecording() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), null);
		
		// Step 4.5.1
		// User presses Ctrl + D
		// TODO die juiste combinaties vinden voor Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(BookmarkDialog.class));
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), null);
		
		// TODO Step 4.5.2 ??
		
		// Step 4.5.3
		// make name input TextBox get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox());
		assertTrue(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());

		// Step 4.5.4 and 4.5.5
		// type "test"
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		
		// make name input TextBox lose focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 165, 473, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 165, 473, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 165, 473, 1, MouseEvent.BUTTON1, 0);
		
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), null);
		assertFalse(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());
		// check if text in the name input TextBox is edited
		assertEquals("test", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getText());
		
		// type "hello"
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		
		// check if text in name input TextBox is unaffected
		assertEquals("test", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getText());
		
		// make name input TextBox get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox());
		assertTrue(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());
		
		// check if all text in name input TextBox is selected
		assertEquals("test", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getSelectedText());
		
		// press again to unselect all text in input name TextBox to keep editing the text
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 165, 73, 1, MouseEvent.BUTTON1, 0);
		
		// check if all text in name input TextBox is unselected and the text is at the left of the cursor
		assertEquals("", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getSelectedText());
		assertEquals("test", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getLeftText());

		// type "name"
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 65, 'a', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 77, 'm', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
		
		// check if text in the name input TextBox is edited
		assertEquals("testname", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getText());
		
		// Step 4.5.6
		// make URL input TextBox get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 155, 131, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 155, 131, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 155, 131, 1, MouseEvent.BUTTON1, 0);
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox());
		assertFalse(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());
		assertTrue(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().isActive());
		
		// type the url in the url input TextBox
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		
		// press left key
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 37, '\0', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 37, '\0', 0);
		
		// check if cursor moves correctly
		assertEquals("http", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().getLeftText());
		assertEquals("/", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().getRightText());
		
		// put some text in search bar
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, ':', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		
		// press right key
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 39, '\0', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 39, '\0', 0);
		
		// put some text in the url input TextBox
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 67, 'c', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 85, 'u', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 85, 'u', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 86, 'v', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 69, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 131, "~".charAt(0), 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 65, 'a', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 74, 'j', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 65, 'a', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 67, 'c', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 87, 'w', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 77, 'm', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
		
		// check if name and url input TextBoxes have the desired text
		assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().getText());
		assertEquals("testname", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getText());
		
		// Step 4.5.7
		// press Add Bookmark button
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 354, 215, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 354, 215, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 354, 215, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.5.8
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), null);
		
		// check if last bookmark is the bookmark just added
		ArrayList<TableCellGUI> bookmarkCellArrayList = mainWindow.getWindowManager().getMainDialog().getBookmarkBar().getGuiRows().get(0).getGuiElements();
		BookmarkHyperlink lastAddedBookmark = ((BookmarkHyperlink) bookmarkCellArrayList.get(bookmarkCellArrayList.size() - 1).getGui());
		
		// check if the last added bookmark has the right values
		assertEquals("testname", lastAddedBookmark.getText());
		assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", lastAddedBookmark.getUrl());
	}

}