package htmlElement;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;

public abstract class GUIElement {
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;

	
	public GUIElement(int x, int y, int w, int h){
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
	}
	
	/**
	 * Sets the value xPos of this class
	 * 
	 * @param x - new value of this.xPos
	 */
	public void setX(int x) {
		this.xPos = x;
	}
	
	/**
	 * Returns the value xPos of this class
	 * 
	 * @return this.xPos
	 */
	public int getLeftX() {
		return this.xPos;
	}
	
	/**
	 * Returns the value of xPos + width of this class
	 * 
	 * @return this.xPos + this.width
	 */
	public int getRightX() {
		return this.xPos + this.width;
	}	
	
	/**
	 * Sets the value yPos of this class
	 * 
	 * @param y - new value of this.yPos
	 */
	public void setY(int y) {
		this.yPos = y;
	}

	/**
	 * Returns the value yPos of this class
	 * 
	 * @return this.yPos
	 */
	public int getUpperY() {
		return this.yPos;
	}
	
	/**
	 * Returns the value of xPos + height of this class
	 * 
	 * @return this.yPos + this.height
	 */
	public int getLowerY() {
		return this.yPos + this.height;
	}
	
	/**
	 * Sets the value width of this class
	 * 
	 * @param width - new value of this.width
	 */
	public void setWidth(int width) {
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
	 * Sets the value height of this class
	 * 
	 * @param height - new value of this.height
	 */
	public void setHeight(int height) {
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

	public abstract void paint(Graphics g);
	
	/**
	 * Updates all variables with given graphics,
	 * 
	 * for example text need a graphics to know the heigth so it should be updated
	 * @param g
	 */
	public void update(Graphics g) {
		
	}
	
	/**
	 * Checks if event is on GUIElement
	 * @param x - x coordinate of event
	 * @param y - y coordinate of event
	 * @return 	true if (getLeftX() <= x && getRightX() >= x) && (getUpperY() <= y && getLowerY() >= y)
	 * 			else false
	 */
	public boolean checkCoordinates(int x, int y) {
		if ((getLeftX() <= x && getRightX() >= x) && (getUpperY() <= y && getLowerY() >= y)) {
			return true;
		} else {
			return false;
		}	
	}
	
}
