package GUIElements;

import java.awt.Graphics;

public class TableCellGUI extends GUIElement {

	private GUIElement gui;
	
	public TableCellGUI(GUIElement guiElement, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGui(guiElement);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
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
	
}
