package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import events.EventReader;

public class TableRowGUI extends GUIElement {

	
	private ArrayList<TableCellGUI> guiElements;
	
	
	public TableRowGUI(ArrayList<TableCellGUI> cells, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGuiElements(cells);
	}
	
	public ArrayList<TableCellGUI> getGuiElements() {
		return guiElements;
	}

	public void setGuiElements(ArrayList<TableCellGUI> cells) {
		this.guiElements = cells;
	}



	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		int relativeX = xContainer;
		for(int i=0; i< this.getGuiElements().size();i++) {
			this.getGuiElements().get(i).paint(g, relativeX, yContainer);
			relativeX+=this.getGuiElements().get(i).getWidth();
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
		if(this.getGuiElements()!=null) {
			for(int i= 0; i<this.getGuiElements().size();i++) {
				this.getGuiElements().get(i).setHeight(height);
			}
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
	
	/**
	 * sum up all the widths of the row
	 */
	@Override
	public int getWidth(){
		int totalWidth=0;
		for(int i=0; i<this.getGuiElements().size();i++) {
			int width = this.getGuiElements().get(i).getWidth();
			totalWidth+=width;
		}
		return totalWidth;
	}
	

	
	public GUIElement getGUIAtGivenIndex(int index) {
		return this.getGuiElements().get(index).getGui();
	}
	

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
	}
	
	@Override
	public void handleUnselect() {
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	public GUIElement getGUIAtPosition(int x, int y) {
		for(int i=0; i<this.getGuiElements().size();i++) {
			GUIElement e =this.getGuiElements().get(i).getGUIAtPosition(x-this.getX(), y-this.getY());
			if(e!=null) {
				return e;
			}
		}
		return null;
	}

	@Override
	public void handleClick() {
	}
	

}
