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
	 * Paints each GUIElement in the container.
	 * @param g - Graphics object used to paint the GUIElements.
	 */
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
	
	/**
	 * Returns the GUIElement at position (x,y). If such element does not exist, return null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return GUIElement at coordinate (x,y)
	 * 			| otherwise null
	 */
	public GUIElement elementAt(int x, int y) {
		//calculate the relativeX/Y
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
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		this.elements.addAll(guiList);
	}
	
	/**
	 * Empties container and adds GUIElements from given guiList to the container.
	 * @param guiList
	 */
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.elements.clear();
		this.addMultipleElements(guiList);
	}

	/**
	 * @return the x of the left side
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return the x of the right side
	 */
	public int getEndX() {
		return x + width;
	}

	/**
	 * Sets this.x to the given x. If this one is negative, set it to 0.
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
	 * @return the y of the upper side
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return the y of the lower side
	 */
	public int getEndY() {
		return y + height;
	}

	/**
	 * Sets this.y to the given y. If this one is negative, set it to 0.
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
	 * Sets this.width to the given width. If negative, set this.width to 0.
	 * @param width - the width to set
	 */
	public void setWidth(int width) {
		if (width < 0) {
			width = 0;
		}
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets this.height to the given width. If negative, set this.height to 0.
	 * @param height - the height to set
	 */
	public void setHeight(int height) {
		if (height < 0) {
			height = 0;
		}
		this.height = height;
	}

}
