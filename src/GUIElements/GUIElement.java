package GUIElements;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import facades.EventReader;

public abstract class GUIElement {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private EventReader eventReader; //TODO moet mss naar runable verandert worden
	private boolean isActive = false;

	
	public GUIElement(int x, int y, int w, int h){//TODO durf nog niet te verwijderen
		this.setX(x);
		this.setY(y);
		this.setWidth(w);
		this.setHeight(h);
	}
	
//	public GUIElement(int x, int y, int w, int h,EventReader eventReader){
//		this.setX(x);
//		this.setY(y);
//		this.setWidth(w);
//		this.setHeight(h);
//		this.setEventReader(eventReader);
//	}
	
	/**
	 * Sets the x and y position
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.setX(x);
		this.setY(y);
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

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean newIsActive) {
		this.isActive = newIsActive;
		if (!newIsActive) {
			handleUnselect();
		}
	}

	

	/**
	 * All objects that get notified when this UIElement is clicked.
	 */
	protected ArrayList<Runnable> clickListeners = new ArrayList<Runnable>();
	
	/**
	 * All objects that get notified when this UIElement is unselected.
	 */
	protected ArrayList<Runnable> unselectListener = new ArrayList<Runnable>();
	
	//Dit is een dict die in functie van de keycode de corresponderende arraylist van runnables moet geven.
	/**
	 * HashMap that maps keycodes to a list of runnables that are to be executed
	 */
	protected HashMap<Integer, ArrayList<Runnable>> keyboardListeners = new HashMap<Integer, ArrayList<Runnable>>();
	
	/**
	 * adds a listener for a click action
	 * @param f: the listener to be added
	 */
	public void addClickListener(Runnable f) {
		clickListeners.add(f);
	}
	
	/**
	 * Attaches a function to a keyCode; the function will be executed when the key is pressed
	 * @param keyCode	Key code
	 * @param f			Function
	 */
	public void addKeyboardListener(int keyCode, Runnable f) {
		ArrayList<Runnable> r = keyboardListeners.get(keyCode);
		if (r == null) { //No Runnables for this keycode, create new ArrayList
			ArrayList<Runnable> singleton = new ArrayList<Runnable>();
			singleton.add(f);
			keyboardListeners.put(keyCode, singleton);
		}
		else { 	//Already some Runnables, add to existing ArrayList
			keyboardListeners.get(keyCode).add(f);
		}	
	}
	
	/**
	 * adds a listener for a unselect action
	 * @param f: the listener to be added
	 */
	public void addUnselectListener(Runnable f) {
		unselectListener.add(f);
	}
	
	
	public abstract void handleKeyEvent(int keyCode, char keyChar, int modifiersEx);
	
	protected abstract void handleUnselect();
	
	
	public abstract void paint(Graphics g, int xContainer, int yContainer);
	
	/**
	 * Draws text centered in the UIElement.
	 * @param g		Graphics object
	 * @param text	String to draw
	 */
	public void drawCenteredText(Graphics g, String text, int xContainer, int yContainer){
		Font font = g.getFont();
	    FontMetrics metrics = g.getFontMetrics(font);
	    int centeredX = this.getX() + xContainer + (this.getWidth()- metrics.stringWidth(text)) / 2;
	    int centeredY = this.getY() + yContainer +  ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.drawString(text, centeredX,centeredY);
	}

	/**
	 *	Returns whether (x,y) is inside the bounds of this UIElement
	 * @param x 	X Coordinate
	 * @param y 	Y Coordinate
	 */
	public boolean containsPoint(int x,int y) {
		return (x >= getX() && y >= getY() && x <= getEndX() && y <= getEndY());
	}

	public void handleClick() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	public GUIElement getGUIAtPosition(int x, int y) {
		if(this.containsPoint(x, y)) {
			return this;
		}
		//otherwise return null
		return null;
	}

	public EventReader getEventReader() {
		return eventReader;
	}

	public void setEventReader(EventReader eventReader) {
		this.eventReader = eventReader;
	}

	
}
