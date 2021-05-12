package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class TableCellGUI extends GUIElement {

	private GUIElement gui;
	
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
		this.setGui(guiElement);
	}

	/**
	 * Transmits the Graphics object to this.guiElement.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);
		this.getGui().paint(newG);
	}

	/**
	 * Returns the GUI if the given position is between its bounds
	 * @param x - the x coordinate to check
	 * @param y - the y coordinate to check
	 */
	@Override
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
	@Override
	public void setPosition(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("invalid position tableCell");
		}
		this.setX(x);
		this.setY(y);
	}
	
	

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {}

	@Override
	protected void handleUnselect() {}

	@Override
	public void handleClick() {}
	
	/**
	 * Returns an array with all the elements in this Container of the given class.
	 */
	@Override
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array){
		return this.getGui().getGuiClass(cls, array);
	}
	
}
