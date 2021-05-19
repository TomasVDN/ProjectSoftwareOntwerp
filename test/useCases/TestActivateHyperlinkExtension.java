package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import GUIElements.Container;
import GUIElements.HTMLDocument;


class TestActivateHyperlinkExtension {

	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.1 Extension 2a: Activate Hyperlink")
	void test() {
		//load the page for the test
		mainWindow.getWindowManager().getSearchbar().replaceBox("https://konikoko.github.io/");
		mainWindow.getWindowManager().getSearchbar().handleEnter();
		
		//click on the broken the broken hyperlink
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 70, 180, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 70, 180, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 70, 180, 1, MouseEvent.BUTTON1, 0);

		//check the error screen
		HTMLDocument htmlDocument = mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument();
		Text pageErrorText = (Text) (htmlDocument.getElements().get(0));
		
		assertEquals("Error occured. Make sure you entered a valid URL.", pageErrorText.getText());
		
		//load the page for the test
		mainWindow.getWindowManager().getSearchbar().replaceBox("https://konikoko.github.io/");
		mainWindow.getWindowManager().getSearchbar().handleEnter();
		
		//click on the working hyperlink but with the broken html file
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 37, 217, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 37, 217, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 37, 217, 1, MouseEvent.BUTTON1, 0);

		//check the error screen
		HTMLDocument htmlDocument1 = mainWindow.getWindowManager().getMainDialog().getActiveHTMLDocument();
		Text pageErrorText1 = (Text) (htmlDocument.getElements().get(0));
		
		assertEquals("Error occured. Reason: not a valid Browsr document.", pageErrorText1.getText());
	}

}
