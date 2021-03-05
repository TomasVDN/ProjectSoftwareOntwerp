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

	@Override
	public void handleClick() {
		new ArrayList<>(clickListeners).forEach(l -> l.run());
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
		if (new HashMap<Integer, ArrayList<Runnable>>(keyboardListeners).get(keyCode) == null)
			return;
		
		new HashMap<Integer, ArrayList<Runnable>>(keyboardListeners).get(keyCode).stream().forEach(l -> l.run());
	}
	
	@Override
	public void handleUnselect() {
		new ArrayList<Runnable>(unselectListener).stream().forEach(l -> l.run());
	}
	
	
	
}
