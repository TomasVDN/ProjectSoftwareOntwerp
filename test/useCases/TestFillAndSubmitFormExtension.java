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
import GUIElements.Button;
import GUIElements.Container;
import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import GUIElements.TableCellGUI;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import GUIElements.TextBox;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;

class TestFillAndSubmitFormExtension {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.3 Extension 6a: Fill and Submit Form")
	// Use Case 4.3
	public void addBookmarkSuccess() throws InvocationTargetException, InterruptedException {
		// check if active dialog is main dialog
		assertEquals(mainWindow.getWindowManager().getMainDialog(), mainWindow.getWindowManager().getActiveDialog());
		assertEquals(null, mainWindow.getWindowManager().getElementWithKeyboardFocus());
		
		// check if right title is shown
		assertEquals("Browsr", mainWindow.getTitle());
		
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		//got to the correct address
		StringTyping.generateKeyEventsForString(mainWindow, "https://konikoko.github.io/form.html");
		
		// make search bar lose focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 200, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 200, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 200, 200, 1, MouseEvent.BUTTON1, 0);
		
		// make select first text box
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 180, 150, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 180, 150, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 180, 150, 1, MouseEvent.BUTTON1, 0);
		
		//fill the text box
		StringTyping.generateKeyEventsForString(mainWindow, "t");
		
		// make select second text box
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 180, 180, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 180, 180, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 180, 180, 1, MouseEvent.BUTTON1, 0);
		
		//fill the text box
		StringTyping.generateKeyEventsForString(mainWindow, "5");
		
		//click submit button
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 55, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 55, 200, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 55, 200, 1, MouseEvent.BUTTON1, 0);
		
		//check the error screen
		Container pageContainer = mainWindow.getWindowManager().getMainDialog().getPageContainer();
		Text pageErrorText = (Text) (pageContainer.getElements().get(0));
				
		assertEquals("Error occured. Make sure you entered a valid URL.", pageErrorText.getText());

	}

}
