package useCases;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
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
import GUIElements.Button;
import GUIElements.GUIElement;
import GUIElements.TableCellGUI;
import GUIElements.Text;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;

class TestAddBookmarkCancel {

private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.5.7a: Add Bookmark cancel scenario")
	// Use Case 4.5
	public void addBookmarkCancel() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// get amount of elements in bookmarkBar before adding the new bookmark
		int amountOfElementsInBookmarkTableBefore = mainWindow.getWindowManager().getMainDialog().getBookmarkBar().getGuiRows().get(0).getGuiElements().size();
		
		// Step 4.5.1
		// User presses Ctrl + D
		// TODO die juiste combinaties vinden voor Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs changed correctly
		BookmarkDialog bookmarkDialog = (BookmarkDialog) mainWindow.getWindowManager().getActiveDialog();
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(BookmarkDialog.class));
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		
		// check if no element has keyboard focus
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertFalse(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());
		assertFalse(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().isActive());
		
		// Step 4.5.2
		// check if right title is shown
		assertEquals("Add Bookmark", mainWindow.getTitle());
		
		// check if all required Elements are contained by the BookmarkDialog
		Button cancelButton = null;
		
		ArrayList<GUIElement> bookmarkDialogElements = mainWindow.getWindowManager().getActiveDialog().getElements();
		boolean containsNameLabel = false;
		boolean containsUrlLabel = false;
		boolean containsAtLeastOneTextBox = false;
		boolean containsAtLeastTwoTextBoxes = false;
		TextBox firstTextBox = null;
		boolean containsCancelButton = false;
		boolean containsAddBookmarkButton = false;
		
		for (GUIElement element : bookmarkDialogElements) {
			if (element.getClass().equals(Text.class)) {
				if (((Text) element).getText().equals("Name:")) { // check if the name label is present
					containsNameLabel = true;
				} 
				else if (((Text) element).getText().equals("URL:")) { // check if the url label is present
					containsUrlLabel = true;
				}
			} 
			else if (element.getClass().equals(TextBox.class)) {
				if (!containsAtLeastOneTextBox) { // check if there is at least one TextBox
					containsAtLeastOneTextBox = true;
					firstTextBox = (TextBox) element;
				}
				else if (!containsAtLeastTwoTextBoxes && !firstTextBox.equals((TextBox) element)) { // check if there are at least two TextBoxes and both TextBoxes are different from each other
					containsAtLeastTwoTextBoxes = true;
				}
			} else if (element.getClass().equals(Button.class)) {
				if (((Button) element).getText().getText().equals("Cancel")) { // check if the Cancel button is present
					cancelButton = (Button) element;
					containsCancelButton = true;
				} 
				else if (((Button) element).getText().getText().equals("Add Bookmark")) { // check if the Add Bookmark button is present
					containsAddBookmarkButton = true;
				}
			}
		}
		
		// actual check if all required Elements are contained by the BookmarkDialog
		assertTrue(containsNameLabel);
		assertTrue(containsUrlLabel);
		assertTrue(containsAtLeastOneTextBox);
		assertTrue(containsAtLeastTwoTextBoxes);
		assertTrue(containsCancelButton);
		assertTrue(containsAddBookmarkButton);
		
		// Step 4.5.3
		// make name input TextBox get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 165, 60, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 165, 60, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 165, 60, 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.5.4
		// check if input field has focus
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox());
		assertTrue(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());

		// Step 4.5.5
		// type "testname"
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 65, 'a', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 77, 'm', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
		
		// check if text in the name input TextBox is edited
		assertEquals("testname", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().getText());
		
		// Step 4.5.6
		// make URL input TextBox get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 155, 114, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 155, 114, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 155, 114, 1, MouseEvent.BUTTON1, 0);
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox());
		assertFalse(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getNameTextBox().isActive());
		assertTrue(((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().isActive());
		
		// check if the suggested url has been automatically filled in and selected after clicking on the URL input TextBox in this case it should be an empty string
		assertEquals("", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().getText());
		assertEquals("", ((BookmarkDialog) mainWindow.getWindowManager().getActiveDialog()).getUrlTextBox().getSelectedText());
		
		// type the url in the url input TextBox
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, ':', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
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
		
		// Step 4.5.7a
		// check if right coordinates will be clicked for cancel button
		assertThat(mainWindow.getWindowManager().getActiveDialog().getGUIAtPosition(cancelButton.getX(), cancelButton.getY()), instanceOf(Button.class));
		assertEquals("Cancel", ((Button) mainWindow.getWindowManager().getActiveDialog().getGUIAtPosition(cancelButton.getX(), cancelButton.getY())).getText().getText());
		
		// press Cancel button
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, cancelButton.getX(), cancelButton.getY(), 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, cancelButton.getX(), cancelButton.getY(), 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, cancelButton.getX(), cancelButton.getY(), 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.5.8
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertFalse(bookmarkDialog.getNameTextBox().isActive());
		assertFalse(bookmarkDialog.getUrlTextBox().isActive());
		assertFalse(cancelButton.isActive());
		
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// check if the input TextBoxes of bookmarkDialog are not active
		assertFalse(bookmarkDialog.getNameTextBox().isActive());
		assertFalse(bookmarkDialog.getUrlTextBox().isActive());
		
		// check if the bookmarkBar has the same amount of elements
		int amountOfElementsInBookmarkTableAfter = mainWindow.getWindowManager().getMainDialog().getBookmarkBar().getGuiRows().get(0).getGuiElements().size();
		assert amountOfElementsInBookmarkTableBefore == amountOfElementsInBookmarkTableAfter;
	}

}
