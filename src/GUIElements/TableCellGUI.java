package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class TableCellGUI extends GUIElement {

	private GUIElement gui;
	
	public TableCellGUI(GUIElement guiElement, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGui(guiElement);
	}

	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		this.getGui().paint(g, xContainer, yContainer);
	}

	public GUIElement getGui() {
		return gui;
	}

	public void setGui(GUIElement gui) {
		this.gui = gui;
	}
	
	public int getGUIWidth() {
		return this.getGui().getWidth();
	}
	
	public int getGUIHeight() {
		return this.getGui().getHeight();
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	public GUIElement getGUIAtPosition(int x, int y) {
		return this.getGui().getGUIAtPosition(x-this.getX(), y - this.getY());
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
	}

	@Override
	protected void handleUnselect() {
	}
	
}
