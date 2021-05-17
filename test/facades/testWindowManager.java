package facades;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.BookmarkDialog;
import GUIElements.SaveDialog;
import canvaswindow.MyCanvasWindow;

class testWindowManager {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Test changing to bookmark dialog")
	public void testChangeToBookmarkDialog() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
				
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// User presses Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs changed correctly
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(BookmarkDialog.class));
		assertNotEquals(SaveDialog.class, mainWindow.getWindowManager().getActiveDialog().getClass());
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		BookmarkDialog bookmarkDialog1 = (BookmarkDialog) mainWindow.getWindowManager().getActiveDialog();
		
		// check if right title is shown
		assertEquals("Add Bookmark", mainWindow.getTitle());
		
		// User presses Ctrl + S
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs did not change
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(BookmarkDialog.class));
		assertNotEquals(SaveDialog.class, mainWindow.getWindowManager().getActiveDialog().getClass());
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		
		// check if right title is shown
		assertEquals("Add Bookmark", mainWindow.getTitle());
		
		// change to main dialog

		mainWindow.getWindowManager().changeActiveDialog("mainDialog");

		
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
						
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// User presses Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs changed correctly
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(BookmarkDialog.class));
		assertNotEquals(SaveDialog.class, mainWindow.getWindowManager().getActiveDialog().getClass());
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		BookmarkDialog bookmarkDialog2 = (BookmarkDialog) mainWindow.getWindowManager().getActiveDialog();
		
		// check if right title is shown
		assertEquals("Add Bookmark", mainWindow.getTitle());
		
		assert(bookmarkDialog1 != bookmarkDialog2);
		
		// User presses Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// This checks that not a new bookmark dialog is created when ctrl + D is pressed when already in a bookmark dialog
		assert(mainWindow.getWindowManager().getActiveDialog() != bookmarkDialog1);
		assert(mainWindow.getWindowManager().getActiveDialog() == bookmarkDialog2);
	}
	
	@Test
	@DisplayName("Test changing to save dialog")
	public void testChangeToSaveDialog() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
				
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// User presses Ctrl + S
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs changed correctly
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(SaveDialog.class));
		assertNotEquals(BookmarkDialog.class, mainWindow.getWindowManager().getActiveDialog().getClass());
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		SaveDialog saveDialog1 = (SaveDialog) mainWindow.getWindowManager().getActiveDialog();
		
		// check if right title is shown
		assertEquals("Save As", mainWindow.getTitle());
		
		// User presses Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs did not change
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(SaveDialog.class));
		assertNotEquals(BookmarkDialog.class, mainWindow.getWindowManager().getActiveDialog().getClass());
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		
		// check if right title is shown
		assertEquals("Save As", mainWindow.getTitle());
		
		// change to main dialog

		mainWindow.getWindowManager().changeActiveDialog("mainDialog");

		
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
						
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// User presses Ctrl + S
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 83, 's', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// check if dialogs changed correctly
		assertThat(mainWindow.getWindowManager().getActiveDialog(), instanceOf(SaveDialog.class));
		assertNotEquals(BookmarkDialog.class, mainWindow.getWindowManager().getActiveDialog().getClass());
		assertNotEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		SaveDialog saveDialog2 = (SaveDialog) mainWindow.getWindowManager().getActiveDialog();
		
		// check if right title is shown
		assertEquals("Save As", mainWindow.getTitle());
		
		assert(saveDialog1 != saveDialog2);
		
		// User presses Ctrl + D
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '?', 128); 
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 68, 'd', 128);
		mainWindow.handleKeyEvent(KeyEvent.KEY_RELEASED, 17, '?', 128);
		
		// This checks that not a new save dialog is created when ctrl + S is pressed when already in a save dialog
		assert(mainWindow.getWindowManager().getActiveDialog() != saveDialog1);
		assert(mainWindow.getWindowManager().getActiveDialog() == saveDialog2);
	}
}
