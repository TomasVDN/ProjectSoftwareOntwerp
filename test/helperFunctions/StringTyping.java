package helperFunctions;

import java.awt.event.KeyEvent;

import canvaswindow.MyCanvasWindow;

public class StringTyping {
	
	public static void generateKeyEventsForString(MyCanvasWindow window, String string) {
		for (int i = 0; i < string.length(); i++) {
			char keyChar = string.charAt(i);
			int keyCode = java.awt.event.KeyEvent.getExtendedKeyCodeForChar(keyChar);
			window.handleKeyEvent(KeyEvent.KEY_PRESSED, keyCode, keyChar, 0);
		}
	}
}
