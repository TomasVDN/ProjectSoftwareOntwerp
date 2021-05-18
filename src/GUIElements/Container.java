package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.ScrollBarListener;

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
	
	public int maxCoordinateOfElements() {
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
	 * Paints all the GUIElements contained within this container.
	 * @param g - Graphics object used to paint.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);
		Graphics graphicsWithOffset= newG.create(this.getxOffset(),this.getyOffset(), this.maxCoordinateOfElements()+1, this.maxYCoordinatOfElements()+1);
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
	 * @param element the element to add
	 */
	public void addElement(GUIElement element) {
		if(element==null) {
			throw new IllegalArgumentException("Can't add null to a container");
		}
		this.elements.add(element);
		this.notifyAdjustmentListenerReset(this.getWidth(), this.maxCoordinateOfElements(), this.getHeight(), this.maxYCoordinatOfElements());
	}
	
	/**
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
	 * Empties container and adds GUIElements from given guiList to the container.
	 * @param guiList
	 * @param code 
	 * @param path 
	 */
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.elements.clear();
		// if guilist is empty elements don't get added so notify is here also needed
		this.notifyAdjustmentListenerReset(this.getWidth(), this.maxCoordinateOfElements(), this.getHeight(), this.maxYCoordinatOfElements());
		this.addMultipleElements(guiList);
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 * @param x - the x coordinate from the position to check
	 * @param y - the y coordinate from the position to check
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

	@Override
	public void scrollBarMoved(double ratio, Direction direction) {
		if(direction == Direction.HORIZONTAL) {
			int newOffset = (int)( ratio*this.maxCoordinateOfElements());
			this.setxOffset(-newOffset);
		}
		else {
			int newOffset = (int)( ratio*this.maxYCoordinatOfElements());
			this.setyOffset(-newOffset);
		}
	}


}
