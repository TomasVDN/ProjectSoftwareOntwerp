package helperFunctions;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import canvaswindow.MyCanvasWindow;

public class UrlRunningWithSearchBar {
	
	public static void runUrlWithSearchBar(MyCanvasWindow mainWindow, String url) {
		// make search bar get focus
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_PRESSED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_RELEASED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		mainWindow.handleMouseEvent(MouseEvent.MOUSE_CLICKED, 132, 28, 1, MouseEvent.BUTTON1, 0);
		
		// type url
		StringTyping.generateKeyEventsForString(mainWindow, url);
		
		// press enter
		mainWindow.handleKeyEvent(KeyEvent.KEY_PRESSED, 10, '\n', 0);
	}
}
