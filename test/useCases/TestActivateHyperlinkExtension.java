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


class TestActivateHyperlinkExtension {

	private MyCanvasWindow window;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			window = new MyCanvasWindow("BrowsrController");
		});
	}
	
	@Test
	@DisplayName("Use Case 4.1 Extension 2a: Activate Hyperlink")
	void test() {
		//load the page for the test
		window.getWindowManager().getSearchbar().replaceBox("https://konikoko.github.io/");
		window.getWindowManager().getSearchbar().handleEnter();
		
		//click on the broken the broken hyperlink
		window.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 70, 180, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 70, 180, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 70, 180, 1, MouseEvent.BUTTON1, 0);

		//check the error screen
		Container pageContainer = window.getWindowManager().getMainDialog().getDocumentArea();
		Text pageErrorText = (Text) (pageContainer.getElements().get(0));
		
		assertEquals("Error occured. Make sure you entered a valid URL.", pageErrorText.getText());
		
		//load the page for the test
		window.getWindowManager().getSearchbar().replaceBox("https://konikoko.github.io/");
		window.getWindowManager().getSearchbar().handleEnter();
		
		//click on the working hyperlink but with the broken html file
		window.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 37, 205, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 37, 205, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 37, 205, 1, MouseEvent.BUTTON1, 0);

		//check the error screen
		Container pageContainer1 = window.getWindowManager().getMainDialog().getDocumentArea();
		Text pageErrorText1 = (Text) (pageContainer1.getElements().get(0));
		
		assertEquals("Error occured. Reason: not a valid BrowsrController document.", pageErrorText1.getText());
	}

}
