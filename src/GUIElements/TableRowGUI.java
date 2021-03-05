package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class TableRowGUI extends GUIElement {

	
	private ArrayList<TableCellGUI> guiElements;
	
	
	public TableRowGUI(ArrayList<TableCellGUI> cells, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGuiElements(cells);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<TableCellGUI> getGuiElements() {
		return guiElements;
	}

	public void setGuiElements(ArrayList<TableCellGUI> cells) {
		this.guiElements = cells;
	}



	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		for(int i=0; i< this.getGuiElements().size();i++) {
			this.getGuiElements().get(i).paint(g, xContainer, yContainer);
			// relativeX+=this.getGuiElements.get(i).getWidth();
		}
	}
	
	public int size() {
		return this.getGuiElements().size();
	}
	
	
	/**
	 * Sets the value height of each of its elements
	 * 
	 * @param height - new value of this.height
	 */
	public void setHeight(int height) {
		for(int i= 0; i<this.getGuiElements().size();i++) {
			this.getGuiElements().get(i).setHeight(height);
		}
	}
	
	/**
	 * search for the biggest height of his elements
	 */
	@Override
	public int getHeight(){
		int maxHeight=0;
		for(int i=0; i<this.getGuiElements().size();i++) {
			int height = this.getGuiElements().get(i).getGUIHeight();
			if(height>maxHeight) {
				maxHeight=height;
			}
		}
		return maxHeight;
	}
	

	
	public GUIElement getGUIAtGivenIndex(int index) {
		return this.getGuiElements().get(index).getGui();
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
