package canvaswindow;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import GUIElements.GUIElement;
import facades.WindowManager;

public class MyCanvasWindow extends CanvasWindow {

	private WindowManager windowManager;
	
	/**
	 * Constructor of myCanvasWindow.
	 * @param title - the title of this CanvasWindow
	 */
	public MyCanvasWindow(String title) {
		super(title);
		this.show();
		windowManager = new WindowManager(this.getWidth(),this.getHeight());

	}

	/**
	 * @return this.windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}
	
	/**
	 * Transmits the Graphics object of CanvasWindow, this.width and this.height to the windowManager.paint method.
	 */
	@Override
	protected void paint(Graphics g) {
		windowManager.paint(g,this.getWidth(),this.getHeight());
	}

	/**
	 * Transmits the mouseEvents to this.windowManager.
	 */
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
	
	/**
	 * Transmits the keyBoardEvents to this.windowManager.
	 */
	@Override
	public void handleKeyEvent(int id, int keyCode, char keyChar, int modifiersEx){
		this.getWindowManager().handleKeyEvent(id, keyCode, keyChar, modifiersEx);	
		repaint();
	}



}
