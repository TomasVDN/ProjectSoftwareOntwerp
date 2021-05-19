package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;


import EventListeners.ScrollBarListener;

/**
 * Class that implements a Container. It is a GUIElement that contains other GUIElements.
 */
public class Container extends GUIElement implements ScrollBarListener {


	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	private int xOffset;
	private int yOffset;

	/**
	 * Constructor of the Container class. It extends the GUIElement class.
	 * @param x - x coordinate of this Container
     * @param y - y coordinate of this Container
     * @param w - width of this Container
     * @param h - height of this Container
	 */
	public Container(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * Paints all the GUIElements contained within this container.
	 * @param g - Graphics object used to paint.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);
		Graphics graphicsWithOffset= newG.create(this.getxOffset(),this.getyOffset(), this.maxXCoordinateOfElements()+1, this.maxYCoordinatOfElements()+1);
		elements.stream().forEach(element -> element.paint(graphicsWithOffset));
	}
	
	/**
	 * Returns the GUIElement at position (x,y). If such element does not exist, return null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return GUIElement at coordinate (x,y)
	 * 			| otherwise null
	 */
	public GUIElement elementAt(int x, int y) {
		int relativeX= x - this.getX()+this.getxOffset();
		int relativeY= y - this.getY() + this.getyOffset();
		
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
	 * Adds the given element to this container.
	 * @param element the element to add
	 */
	public void addElement(GUIElement element) {
		if(element==null) {
			throw new IllegalArgumentException("Can't add null to a container");
		}
		this.elements.add(element);
		this.notifyAdjustmentListenerKeepAtBeginning(this.getWidth(), this.maxXCoordinateOfElements(), this.getHeight(), this.maxYCoordinatOfElements());
	}
	
	/**
	 * Adds the elements in the given list to this container.
	 * @param guiList the elements to add
	 */
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		if(guiList==null) {
			throw new IllegalArgumentException("Can't add null to a container");
		}
		for(int i=0;i<guiList.size();i++) {
			this.addElement(guiList.get(i));
		}
	}
	
	/**
	 * Empties container and adds the GUIElements from the given list to the container.
	 * @param guiList
	 */
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.elements.clear();
		// if guilist is empty elements don't get added so notify is here also needed
		this.notifyAdjustmentListenerKeepAtBeginning(this.getWidth(), this.maxXCoordinateOfElements(), this.getHeight(), this.maxYCoordinatOfElements());
		this.addMultipleElements(guiList);
	}
	
	/**
	 * Returns the GUIElement at position (x,y). If such element does not exist, return null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return GUIElement at coordinate (x,y)
	 * 			| otherwise null
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		int relativeX= x - this.getX()-this.getxOffset();
		int relativeY= y - this.getY()-this.getyOffset();
		
		for (GUIElement e: elements) {
			GUIElement gui= e.getGUIAtPosition(relativeX,relativeY);
			if (gui!=null) {
				return gui;
			}
		}
		return null;
	}

	/**
	 * Returns the x coordinate of the right side of the right most GUIElement
	 * @return max(listOfGUIELements.forEach.getX)
	 */
	public int maxXCoordinateOfElements() {
		int maxWidth = this.getWidth(); // own width is minimal
		for(int i=0; this.getElements().size()>i;i++){
			GUIElement currentElement = this.getElements().get(i);
			int maxXofCurrentElement = currentElement.getEndX();
			if(maxXofCurrentElement>maxWidth) {
				maxWidth=maxXofCurrentElement;
			}
		}
		return maxWidth;
	}
	
	/**
	 * Returns the y coordinate of the lower boundary of the bottom most element.
	 * @return max(listOfGUIELements.forEach.getX)
	 */
	public int maxYCoordinatOfElements() {
		int maxHeight = this.getHeight(); // own height is minimal
		for(int i=0; this.getElements().size()>i;i++){
			GUIElement currentElement = this.getElements().get(i);
			int maxYofCurrentElement = currentElement.getEndY();
			if(maxYofCurrentElement>maxHeight) {
				maxHeight=maxYofCurrentElement;
			}
		}
		return maxHeight;
	}
	
	/**
	 * Notifies this container that the scrollbar has been moved
	 */
	@Override
	public void scrollBarMoved(double ratio, Direction direction) {
		if(direction == Direction.HORIZONTAL) {
			int newOffset = (int)( ratio * this.maxXCoordinateOfElements());
			this.setxOffset(-newOffset);
		}
		else {
			int newOffset = (int)( ratio * this.maxYCoordinatOfElements());
			this.setyOffset(-newOffset);
		}
	}
	
	/**
	 * Returns an array with all the elements in this Container of the given class.
	 */
	@Override
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array) {
		for(GUIElement element: this.getElements()) {
			element.getGuiClass(cls, array);
		}
		return array;
	}
	
	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {}

	@Override
	protected void handleUnselect() {}

	@Override
	public void handleClick() {}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	/**
	 * Updates the width to the given width and notifies the adjustListener that this has been updated.
	 * @param width - the new width
	 */
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		notifyAdjustmentListener(this.getWidth(), this.maxXCoordinateOfElements(), this.getHeight(), this.maxYCoordinatOfElements());
	}
	
	/**
	 * Updates the height to the given height and notifies the adjustListener that this has been updated.
	 * @param height - the new height
	 */
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		notifyAdjustmentListener(this.getWidth(), this.maxXCoordinateOfElements(), this.getHeight(), this.maxYCoordinatOfElements());
	}
}
