package container;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;

public class Container {
	
	public Container(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Container() {

	}
	
	private int x,y,width,height;
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the x
	 */
	public int getEndX() {
		return x + width;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return the y
	 */
	public int getEndY() {
		return y + height;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the elements
	 */
	public ArrayList<GUIElement> getElements() {
		return elements;
	}

	/**
	 * @param element the element to add
	 */
	public void addElement(GUIElement element) {
		this.elements.add(element);
	}

	public void paint(Graphics g) {
		elements.stream().forEach(element -> element.paint(g));
	}

	/**
	 *	Returns whether (x,y) is inside the bounds of this UIElement
	 * @param x 	X Coordinate
	 * @param y 	Y Coordinate
	 */
	public boolean containsPoint(int x,int y) {
		return (x >= getX() && y >= getY() && x <= getEndX() && y <= getEndY());
	}
	
	public GUIElement elementAt(int x, int y) {
		for (GUIElement e: elements) {
			if (e.checkCoordinates(x, y)) {
				return e;
			}
		}
		return null;
	}	
}