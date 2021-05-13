package GUIElements;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class GUIElement {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private boolean isActive = false;

	/**
	 * Constructor of GUIElement.
	 * @param x - x coordinate of the GUIElement
	 * @param y - y coordinate of the GUIElement
	 * @param w - width of the GUIElement
	 * @param h - height of the GUIElement
	 */
	public GUIElement(int x, int y, int w, int h){
		if (x < 0 || y < 0 || w < 0 || h < 0) {
			throw new IllegalArgumentException("The x position, y position, width and height of a GUIElement have to be positive.");
		}
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
	}
	
	/**
	 * Alternative constructor of GUIElement without width and height.
	 * @param x - x coordinate of the GUIElement
	 * @param y - y coordinate of the GUIElement
	 */
	public GUIElement(int x, int y){
		if (x < 0 || y < 0 ) {
			throw new IllegalArgumentException("The x position, y position, width and height of a GUIElement have to be positive.");
		}
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Sets the x and y position
	 * @param x - the new value of this.x
	 * @param y - the new value of this.x
	 */
	public void setPosition(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("The x position and y position of a GUIElement have to be positive.");
		}
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Sets the value xPos of this class.
	 * 
	 * @param x - new value of this.xPos
	 */
	public void setX(int x) {
		if (x < 0) {
			throw new IllegalArgumentException("The x position of a GUIElement has to be positive.");
		}
		this.xPos = x;
	}
	
	/**
	 * Returns the value xPos of this class
	 * 
	 * @return this.xPos
	 */
	public int getX() {
		return this.xPos;
	}
	
	/**
	 * Returns the value of xPos + width of this class
	 * 
	 * @return this.xPos + this.width
	 */
	public int getEndX() {
		return this.getX() + this.getWidth();
	}	
	
	/**
	 * Sets the value yPos of this class
	 * 
	 * @param y - new value of this.yPos
	 */
	public void setY(int y) {
		if (y < 0) {
			throw new IllegalArgumentException("The y position of a GUIElement has to be positive.");
		}
		this.yPos = y;
	}

	/**
	 * Returns the value yPos of this class
	 * 
	 * @return this.yPos
	 */
	public int getY() {
		return this.yPos;
	}
	
	/**
	 * Returns the value of xPos + height of this class
	 * 
	 * @return this.yPos + this.height
	 */
	public int getEndY() {
		return this.getY() + this.getHeight();
	}
	
	/**
	 * Sets the value width of this class. If the new value is negative, set it to 0 instead.
	 * 
	 * @param width - new value of this.width
	 */
	public void setWidth(int width) {
		if (width < 0) {
			throw new IllegalArgumentException("The width of a GUIElement has to be positive.");
		}
		this.width = width;
	}
	
	/**
	 * Returns the value width of this class
	 * 
	 * @return this.width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Sets the value height of this class. If the new value is negative, set it to 0 instead.
	 * 
	 * @param height - new value of this.height
	 */
	public void setHeight(int height) {
		if (height < 0) {
			throw new IllegalArgumentException("The height of a GUIElement has to be positive.");
		}
		this.height = height;
	}
	
	/**
	 * Returns the value height of this class
	 * 
	 * @return this.height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets if this GUIElement is active or not. If it is set to inactive, execute corresponding code.
	 * @param newIsActive - the new value of isActive to set
	 */
	public void setActive(boolean newIsActive) {
		this.isActive = newIsActive;
		if (!newIsActive) {
			handleUnselect();
		}
	}
	
	public void setActiveNoUnselect(boolean newIsActive) {
		this.isActive = newIsActive;
	}

	public abstract void handleKeyEvent(int keyCode, char keyChar, int modifiersEx);
	
	protected abstract void handleUnselect();
	
	public abstract void handleClick();
	
	public void handlePressClick() {}
	
	public void handleReleaseClick(boolean releasedOn) {}
	
	public void handleDragMouse(int x, int y, int clickCount, int modifiers) {}
	
	public abstract void paint(Graphics g);
	
	/**
	 * Draws text centered in the GUIElement.
	 * @param g		Graphics object
	 * @param text	String to draw
	 */
	public void drawCenteredText(Graphics g, String text){
		Font font = g.getFont();
	    FontMetrics metrics = g.getFontMetrics(font);
	    int centeredX = this.getX() + (this.getWidth()- metrics.stringWidth(text)) / 2;
	    int centeredY = this.getY() +  ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.drawString(text, centeredX,centeredY);
	}

	/**
	 *	Returns whether (x,y) is inside the bounds of this UIElement
	 * @param x - x coordinate
	 * @param y - y	coordinate
	 */
	public boolean containsPoint(int x,int y) {
		return (x >= getX() && y >= getY() && x <= getEndX() && y <= getEndY());
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 * @param x - the x coordinate from the position to check
	 * @param y - the y coordinate from the position to check
	 */
	public GUIElement getGUIAtPosition(int x, int y) {
		if(this.containsPoint(x, y)) {
			return this;
		}
		
		return null;
	}

	/**
	 * Returns all the elements of given GUI back into an array
	 * @param <T>
	 * @param cls
	 * @param array
	 * @return
	 */
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array){
		if(cls.isInstance(this)) {
			array.add( (T) this); //TODO dit is niet veilig blijkbaar
		}
		return array;
	}
	

}
