package canvaswindow;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import GUIElements.GUIElement;
import facades.WindowManager;

public class MyCanvasWindow extends CanvasWindow {

	private WindowManager windowManager;
	
	public MyCanvasWindow(String title) {
		super(title);
		windowManager = new WindowManager();
	}

	/**
	 * @return the windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}
	
	@Override
	protected void paint(Graphics g) {
		windowManager.paint(g);
	}

	@Override
	public void handleMouseEvent(int id, int x, int y, int clickCount, int button, int modifiersEx) {
		// left mouseClick
		if (id == MouseEvent.MOUSE_CLICKED && button == MouseEvent.BUTTON1) {
			windowManager.handleLeftMouse(x, y, clickCount, modifiersEx);
		}
		//right muisClick
		if (id == MouseEvent.MOUSE_CLICKED && button == MouseEvent.BUTTON3) {
			
		}
		//mouse drag
		if (id == MouseEvent.MOUSE_DRAGGED && button == MouseEvent.BUTTON1) {
			
		}
		
		repaint();
	}
	
	@Override
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx){
		//TODO modifiers => 64 = Shift, 128 = Ctrl, 512 = alt
		if (id == KeyEvent.KEY_PRESSED) {
			GUIElement element = windowManager.getActiveElement();
			if (element != null) {
				element.handleKeyEvent(keyCode, keyChar, modifiersEx);
			}
		}
		
		
		repaint();
	}

}
