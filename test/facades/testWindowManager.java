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
	// Use Case 4.5
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
	}
	
	@Test
	@DisplayName("Test changing to save dialog")
	// Use Case 4.5
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
	}
}
