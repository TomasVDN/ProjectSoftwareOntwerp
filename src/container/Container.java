package container;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;

public class Container {
	
	private int x,y,width,height;
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	
	/**
	 * Constructor of the Container class.
	 * @param x - x coordinate of this Container
	 * @param y - y coordinate of this Container
	 * @param width - width of this Container
	 * @param height - height of this Container
	 */
	public Container(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

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
		if (x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
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
		if (y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
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
	
	/**
	 * @param element the element to add
	 */
	public void addAllElement(ArrayList<GUIElement> guiList) {
		this.elements.addAll(guiList);
	}
	
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.elements.clear();
		this.addAllElement(guiList);
	}

	public void paint(Graphics g) {
		elements.stream().forEach(element -> element.paint(g,this.getX(), this.getY()));
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
		int relativeX= x - this.getX();
		int relativeY= y - this.getY();
		for (GUIElement e: elements) {
			GUIElement gui= e.getGUIAtPosition(relativeX,relativeY);
			if (gui!=null) {
				return gui;
			}
		}
		return null;
	}	
}
