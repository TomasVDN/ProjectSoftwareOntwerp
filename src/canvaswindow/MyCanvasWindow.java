package canvaswindow;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GUIElements.GUIElement;
import facades.WindowManager;

public class MyCanvasWindow extends CanvasWindow {

	private WindowManager windowManager;
	
	public MyCanvasWindow(String title) {
		super(title);
		this.show();
		windowManager = new WindowManager(this.getWidth(),this.getHeight());

	}

	/**
	 * @return the windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}
	
	@Override
	protected void paint(Graphics g) {
		windowManager.paint(g,this.getWidth(),this.getHeight());
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
	        
	        //Enkel op Tomas zijn keyboard.
	        if (id == KeyEvent.KEY_TYPED && keyChar == "~".charAt(0)) {
	            GUIElement element = windowManager.getActiveElement();
	            if (element != null) {
	                element.handleKeyEvent(keyCode, keyChar, modifiersEx);
	            }
	        }
	        repaint();
	}



}
