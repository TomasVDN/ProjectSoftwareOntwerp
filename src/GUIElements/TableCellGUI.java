package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import EventListeners.EventListener;

public class TableCellGUI extends GUIElement {

	private GUIElement gui;
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	/**
	 * Constructor of the TableCellGUI class
	 * @param guiElement - the GUIElement of the TableCell
	 * @param x - x coordinate of this TableCell
     * @param y - y coordinate of this TableCell
     * @param w - width of this TableCell
     * @param h - height of this TableCell
	 */
	public TableCellGUI(GUIElement guiElement, int x, int y, int w, int h) {
		super(x,y,w,h);
		//this.setPosition(x, y);
		//this.setWidth(w);
		//this.setHeight(h);
		this.setGui(guiElement);
	}

	/**
	 * Transmits the Graphics object to this.guiElement.
	 */
	public void paint(Graphics g) {
		g.translate(this.getX(), this.getY());
		this.getGui().paint(g);
		g.translate(-this.getX(), -this.getY());
	}

	/**
	 * Returns the GUI if the given position is between its bounds
	 * @param x - the x coordinate to check
	 * @param y - the y coordinate to check
	 */
	public GUIElement getGUIAtPosition(int x, int y) {
		return this.getGui().getGUIAtPosition(x-this.getX(), y - this.getY());
	}
	
	/**
	 * @return this.gui
	 */
	public GUIElement getGui() {
		return gui;
	}

	/**
	 * Set the this.gui to the given GUIElement.
	 * @param gui - the new value of this.gui
	 */
	public void setGui(GUIElement gui) {
		if(gui==null) {
			throw new IllegalArgumentException("not a valid gui in table cell");
		}
		this.gui = gui;
	}
	
	/**
	 * @return the width (dependent on width of this.gui)
	 */
	public int getGUIWidth() {
		return this.getGui().getWidth();
	}
	
	/**
	 * @return the height (dependent on height of this.gui)
	 */
	public int getGUIHeight() {
		return this.getGui().getHeight();
	}
	
	/**
	 * Sets the x and y position
	 * @param x - the new value of this.x
	 * @param y - the new value of this.x
	 */
	public void setPosition(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("invalid position tableCell");
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
			throw new IllegalArgumentException("invalid position tableCell");
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
			throw new IllegalArgumentException("invalid position tableCell");
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
			throw new IllegalArgumentException("invalid size tableCell");
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
			throw new IllegalArgumentException("invalid size tableCell");
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
	
	void addListener(EventListener listener) {
		this.getGui().addListener(listener);
	}

	
	void removeListener(EventListener listener) {
		this.removeListener(listener);
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleUnselect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public ArrayList<TextBox> getUsedTextBoxes() {
		return this.getGui().getUsedTextBoxes();
	}
	
}
