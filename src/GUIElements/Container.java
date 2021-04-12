package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import EventListeners.EventListener;

public class Container extends GUIElement {

	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();

	public Container(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		
	}

	@Override
	protected void handleUnselect() {}

	@Override
	public void handleClick() {
		
	}

	@Override
	public void paint(Graphics g) {
		g.translate(getX(), getY());
		elements.stream().forEach(element -> element.paint(g));
		g.translate(-getX(), -getY());
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
		if(element==null) {
			throw new IllegalArgumentException("Can't add null to a container");
		}
		this.elements.add(element);
	}
	
	/**
	 * @param element the element to add
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


	@Override
	void addListener(EventListener listener) {
		for(GUIElement elem : this.getElements()) {
			elem.addListener(listener);
		}
	}


	@Override
	void removeListener(EventListener listener) {
		for(GUIElement elem : this.getElements()) {
			elem.removeListener(listener);
		}
	}

	@Override
	public ArrayList<TextBox> getUsedTextBoxes() {
		ArrayList<TextBox> textBoxList = new ArrayList<TextBox>();
		for(GUIElement element: this.getElements()) {
			ArrayList<TextBox> foundElements = element.getUsedTextBoxes();
			textBoxList.addAll(foundElements);
		}
		return textBoxList;
	}

}
