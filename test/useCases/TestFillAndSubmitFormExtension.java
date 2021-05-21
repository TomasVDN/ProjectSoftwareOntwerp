package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.HTMLDocument;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import helperFunctions.StringTyping;

class TestFillAndSubmitFormExtension {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
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
		assertEquals("BrowsrController", mainWindow.getTitle());
		
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
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 349, 163, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 349, 163, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 349, 163, 1, MouseEvent.BUTTON1, 0);
		
		//fill the text box
		StringTyping.generateKeyEventsForString(mainWindow, "t");
		
		// make select second text box
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 380, 188, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 380, 188, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 380, 188, 1, MouseEvent.BUTTON1, 0);
		
		//fill the text box
		StringTyping.generateKeyEventsForString(mainWindow, "5");
		
		//click submit button
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 41, 229, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 41, 229, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 41, 229, 1, MouseEvent.BUTTON1, 0);
		
		//check the error screen
		HTMLDocument htmlDocument = mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument();
		Text pageErrorText = (Text) (htmlDocument.getElements().get(0));
				
		assertEquals("Error occured. Make sure you entered a valid URL.", pageErrorText.getText());

	}

}
