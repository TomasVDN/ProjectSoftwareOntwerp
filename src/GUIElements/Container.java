package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class Container extends GUIElement {

	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();

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
		elements.stream().forEach(element -> element.paint(newG));
	}
	
	/**
	 * Returns the GUIElement at position (x,y). If such element does not exist, return null.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return GUIElement at coordinate (x,y)
	 * 			| otherwise null
	 */
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
	 */
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.elements.clear();
		this.addMultipleElements(guiList);
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 * @param x - the x coordinate from the position to check
	 * @param y - the y coordinate from the position to check
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
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


}
