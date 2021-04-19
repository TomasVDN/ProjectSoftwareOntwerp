package useCases;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Text;
import canvaswindow.MyCanvasWindow;
import GUIElements.Container;

class TestEnterURLFail {
	
	private MyCanvasWindow mainWindow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			mainWindow = new MyCanvasWindow("Browsr");
		});
	}
	
	
	@Test
	@DisplayName("Use Case 4.2 Extension 6a: Enter URL, URL Malformed")
	void TestURLMalformed() {
	// make search bar get focus
	mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
	mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
	mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
			
	assertTrue(mainWindow.getWindowManager().getSearchbar().isActive());
			
	// put malformed url in searchbar
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, ':', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 71, 'g', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 85, 'u', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
	
	assertEquals("https://konikoko.github.io/thislinkisbroken", mainWindow.getWindowManager().getSearchbar().getText());
	
	// press enter
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 10, '\n', 0);
	
	//check if the error screen is opened
	assertEquals("Error occured. Make sure you entered a valid URL.", ((Text) mainWindow.getWindowManager().getMainDialog().getDocumentArea().getElements().get(0)).getText());
	}
	
	@Test
	@DisplayName("Use Case 4.2 Extension 6a: Enter URL, broken HTML file")
	void TestBadHTMLFile() {
	// make search bar get focus
	mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
	mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
	mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
			
	assertTrue(mainWindow.getWindowManager().getSearchbar().isActive());
			
	// put malformed url in searchbar
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 80, 'p', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 83, 's', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, ':', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 71, 'g', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 85, 'u', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 513, '/', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 66, 'b', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 82, 'r', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 79, 'o', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 75, 'k', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 70, 'f', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 73, 'i', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'e', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 59, '.', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 72, 'h', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 84, 't', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 77, 'm', 0);
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 76, 'l', 0);
	
	
	assertEquals("https://konikoko.github.io/brokenfile.html", mainWindow.getWindowManager().getSearchbar().getText());
	
	// press enter key
	mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 10, '\n', 0);
	
	//check if the error screen is opened
	assertEquals("Error occured. Reason: not a valid Browsr document.", ((Text) mainWindow.getWindowManager().getMainDialog().getDocumentArea().getElements().get(0)).getText());
	}
}
