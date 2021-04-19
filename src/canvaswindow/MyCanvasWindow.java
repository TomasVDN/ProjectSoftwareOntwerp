package canvaswindow;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import facades.WindowManager;

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
	
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Transmits the Graphics object of CanvasWindow, this.width and this.height to the windowManager.paint method.
	 */
	@Override
	protected void paint(Graphics g) {
		windowManager.paint(g,this.getWidth(),this.getHeight());
		updateFrameTitle();
	}

	/**
	 * Transmits the mouseEvents to this.windowManager.
	 */
	@Override
	public void handleMouseEvent(int id, int x, int y, int clickCount, int button, int modifiersEx) {
		// left mouseClick
		if (id == MouseEvent.MOUSE_CLICKED && button == MouseEvent.BUTTON1) {
			windowManager.handleClickLeftMouse(x, y, clickCount, modifiersEx);
		}
		//right muisClick
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
	
	public void setWindowTitle(String newTitle) {
		this.title = newTitle;
	}
	



}
