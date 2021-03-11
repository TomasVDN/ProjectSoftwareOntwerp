package useCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.SearchBar;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import container.Container;
import events.Event;
import events.EventReader;
import events.RunUrlEvent;

class TestActivateHyperlinkExtension {

	private MyCanvasWindow window;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			window = new MyCanvasWindow("Browsr");
		});
	}
	
	@Test
	void test() {
		Event event = new RunUrlEvent("https://konikoko.github.io/");
		EventReader e = EventReader.getInstance();
		e.readEvent(event);
		
		window.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 58, 160, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 58, 160, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 58, 160, 1, MouseEvent.BUTTON1, 0);

		Container pageContainer = window.getWindowManager().getPage();
		Text pageErrorText = (Text) (pageContainer.getElements().get(0));
		
		assertEquals("Error 404", pageErrorText.getText());
		
		Event event2 = new RunUrlEvent("https://konikoko.github.io/");
		EventReader e2 = EventReader.getInstance();
		e2.readEvent(event2);
		
		window.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 41, 182, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 41, 182, 1, MouseEvent.BUTTON1, 0);
		window.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 41, 182, 1, MouseEvent.BUTTON1, 0);

		Container pageContainer1 = window.getWindowManager().getPage();
		Text pageErrorText1 = (Text) (pageContainer1.getElements().get(0));
		
		assertEquals("Error occured. Reason: not a valid Browsr document.", pageErrorText1.getText());
	}

}
