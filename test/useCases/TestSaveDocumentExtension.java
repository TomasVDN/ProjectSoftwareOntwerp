package useCases;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Button;
import GUIElements.GUIElement;
import GUIElements.SaveDialog;
import GUIElements.Text;
import GUIElements.TextBox;
import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;

class TestSaveDocumentExtension {
	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.6.7a: Save Document cancel scenario")
	// Use Case 4.6
	public void saveDocumentCancel() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// check if the file that will be created doesn't exist yet
		// if this fails delete that file because that file should not exist at this point in time
		File fileThatShouldNotExist = new File("savedPages/TestSaveDocument123.html");
		assertFalse(fileThatShouldNotExist.isFile());
		
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		// check if search bar has focus
		assertEquals(mainWindow.getWindowManager().getSearchbar(), mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertTrue(mainWindow.getWindowManager().getSearchbar().isActive());
		
		// type url in search bar
		StringTyping.generateKeyEventsForString(mainWindow, "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html");
		
		// check if url is typed in search bar
		assertEquals("https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", mainWindow.getWindowManager().getSearchbar().getText());
		
		
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// Step 4.6.1
		// User presses Ctrl + S
		// TODO die juiste combinaties vinden voor Ctrl + S
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs changed correctly
		SaveDialog saveDialog = (SaveDialog) mainWindow.getWindowManager().getActiveDialog();
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(SaveDialog.class));
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		
		// check if no element has keyboard focus
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertFalse(((SaveDialog) mainWindow.getWindowManager().getActiveDialog()).getTextBox().isActive());
		
		// Step 4.6.2
		// check if right title is shown
		assertEquals("Save As", mainWindow.getTitle());
		
		// check if all required Elements are contained by the saveDialog
		TextBox textInputFieldBox = null;
		Button cancelButton = null;
		
		ArrayList<GUIElement> saveDialogElements = mainWindow.getWindowManager().getActiveDialog().getElements();
		boolean containsNameLabel = false;
		boolean containsAtLeastOneTextBox = false;
		boolean containsCancelButton = false;
		boolean containsSaveButton = false;
		
		for (GUIElement element : saveDialogElements) {
			if (element.getClass().equals(Text.class)) {
				if (((Text) element).getText().equals("File name:")) { // check if the name label is present
					containsNameLabel = true;
				} 
			} 
			else if (element.getClass().equals(TextBox.class)) {
				if (!containsAtLeastOneTextBox) { // check if there is at least one TextBox
					textInputFieldBox = (TextBox) element;
					containsAtLeastOneTextBox = true;
				}
			} else if (element.getClass().equals(Button.class)) {
				if (((Button) element).getText().getText().equals("Cancel")) { // check if the Cancel button is present
					cancelButton = (Button) element;
					containsCancelButton = true;
				} 
				else if (((Button) element).getText().getText().equals("Save")) { // check if the Save button is present
					containsSaveButton = true;
				}
			}
		}
		
		// actual check if all required Elements are contained by the saveDialog
		assertTrue(containsNameLabel);
		assertTrue(containsAtLeastOneTextBox);
		assertTrue(containsCancelButton);
		assertTrue(containsSaveButton);
		
		// check if right coordinates will be clicked for text input
		assertEquals(((SaveDialog) mainWindow.getWindowManager().getActiveDialog()).getTextBox(), mainWindow.getWindowManager().getActiveDialog().getGUIAtPosition(textInputFieldBox.getX(), textInputFieldBox.getY()));
		
		// Step 4.6.3
		// User clicks on text input field
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, textInputFieldBox.getX(), textInputFieldBox.getY(), 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, textInputFieldBox.getX(), textInputFieldBox.getY(), 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, textInputFieldBox.getX(), textInputFieldBox.getY(), 1, MouseEvent.BUTTON1, 0);
		
		// Step 4.6.4
		// check if right element is focused
		assertEquals(mainWindow.getWindowManager().getElementWithKeyboardFocus(), ((SaveDialog) mainWindow.getWindowManager().getActiveDialog()).getTextBox());
		assertTrue(((SaveDialog) mainWindow.getWindowManager().getActiveDialog()).getTextBox().isActive());
		
		// Step 4.6.5 (editing text in TextBoxes is tested in other tests extensively)
		// type the name of how the document should be named
		StringTyping.generateKeyEventsForString(mainWindow, "TestSaveDocument123");
		
		// Step 4.6.6
		// check if the contents of the input field have been edited
		assertEquals("TestSaveDocument123", ((SaveDialog) mainWindow.getWindowManager().getActiveDialog()).getTextBox().getText());
		
		// check if right coordinates will be clicked for save button
		assertThat(mainWindow.getWindowManager().getActiveDialog().getGUIAtPosition(cancelButton.getX(), cancelButton.getY()), instanceOf(Button.class));
		assertEquals("Cancel", ((Button) mainWindow.getWindowManager().getActiveDialog().getGUIAtPosition(cancelButton.getX(), cancelButton.getY())).getText().getText());
		
		// Step 4.6.7a
		// User clicks on the cancel button
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, cancelButton.getX(), cancelButton.getY(), 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, cancelButton.getX(), cancelButton.getY(), 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, cancelButton.getX(), cancelButton.getY(), 1, MouseEvent.BUTTON1, 0);
	
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		assertFalse(saveDialog.getTextBox().isActive());
		assertFalse(cancelButton.isActive());
		
		// Step 4.6.8
		// check if the file is not created
		File fileThatIsNotCreated = new File("savedPages/TestSaveDocument123.html");
		assertFalse(fileThatIsNotCreated.isFile());
	}

}