package canvaswindow;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import facades.WindowManager;

/**
 * CanvasWindow extension used to transmit all inputs to our program.
 *
 */
public class MyCanvasWindow extends CanvasWindow {

	private WindowManager windowManager;
	
	/**
	 * Constructor of myCanvasWindow.
	 * @param title - the title of this CanvasWindow
	 */
	public MyCanvasWindow(String title) {
		super(title);
		windowManager = new WindowManager(this);
	}

	/**
	 * @return this.windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}
	
	/**
	 * @return this.title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Transmits the Graphics object of CanvasWindow, this.width and this.height to the this.windowManager.paint method.
	 * @param g - graphics to transmit.
	 */
	@Override
	protected void paint(Graphics g) {
		windowManager.paint(g,this.getWidth(),this.getHeight());
		updateFrameTitle();
	}

	/**
 	 * Transmits the mouseEvents to the appropriate method in this.windowManager.
	 */
	@Override
	public void handleMouseEvent(int id, int x, int y, int clickCount, int button, int modifiersEx) {
		// left mouse click
		if (id == MouseEvent.MOUSE_CLICKED && button == MouseEvent.BUTTON1) {
			windowManager.handleClickLeftMouse(x, y, clickCount, modifiersEx);
		}
		
		//right mouse click
		if (id == MouseEvent.MOUSE_CLICKED && button == MouseEvent.BUTTON3) {
			
		}
		
		// left mouse Pressed
		if(id == MouseEvent.MOUSE_PRESSED && button == MouseEvent.BUTTON1) {
			windowManager.handlePressLeftMouse(x, y, clickCount, modifiersEx);
		}
		
		// left mouse released
		if(id == MouseEvent.MOUSE_RELEASED && button == MouseEvent.BUTTON1) {
			windowManager.handleReleaseLeftMouse(x, y, clickCount, modifiersEx);
		}
		
		//mouse drag
		if (id == MouseEvent.MOUSE_DRAGGED) { //&& button == MouseEvent.BUTTON1) {
			windowManager.handleDragLeftMouse(x, y, clickCount, modifiersEx);
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
	
	/**
	 * Sets the title of this window to the newTitle
	 * @param newTitle - the new title
	 */
	public void setWindowTitle(String newTitle) {
		this.title = newTitle;
	}
}
