package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import EventListeners.EventListener;

public class TableRowGUI extends GUIElement {

	private int xPos, yPos;
	
	private ArrayList<TableCellGUI> tableCellList;
	
	/**
	 * Constructor of the TableRowGUI class
	 * @param cells - list of TableCellGUI
	 * @param x - x coordinate of the TableRowGUI
	 * @param y - y coordinate of the TableRowGUI
	 * @param w - width of the TableRowGUI
	 * @param h - height of the TableRowGUI
	 */
	public TableRowGUI(ArrayList<TableCellGUI> cells, int x, int y) {
		super(x,y);
		//this.setX(x);
		//this.setY(y);
		this.setGuiElements(cells);
	}

	
	
	
	/**
	 * Paint all the TableCellGUI while updating the relative x.
	 */
	public void paint(Graphics g) {
		g.translate(this.getX(), this.getY());
		for(int i=0; i< this.getGuiElements().size();i++) {
			this.getGuiElements().get(i).paint(g);
		}
		g.translate(-this.getX(), -this.getY());
	}
	
	/**
	 * Returns the number of table cells in this row.
	 * @return this.tableCellList.size
	 */
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
	 * Returns the height of the tallest tableCell.
	 * @return this.height
	 */
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
	 * Sum up all the widths of the row to calculate the width of the row.
	 * @return this.width
	 */
	public int getWidth(){
		int totalWidth=0;
		for(int i=0; i<this.getGuiElements().size();i++) {
			int width = this.getGuiElements().get(i).getWidth();
			totalWidth+=width;
		}
		return totalWidth;
	}
	
	/**
	 * Returns the GUIElement in the cell with index index.
	 * @param index - index of the element to querry
	 * @return this.tableCellList.get(index).getGui
	 */
	public GUIElement getGUIAtGivenIndex(int index) {
		if(index>=this.getGuiElements().size()) { // given row has not the given index
			return null;
		}
		return this.getGuiElements().get(index).getGui();
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
	
	/**
	 * Returns the list of TableCells
	 * @return this.tableCellList
	 */
	public ArrayList<TableCellGUI> getGuiElements() {
		return tableCellList;
	}

	/**
	 * Sets this.tableCellList to the given list of TableCellGUI's
	 * @param cells - the new value of this.tableCellList
	 */
	public void setGuiElements(ArrayList<TableCellGUI> cells) {
		if (cells == null) {
			return;
		}
		this.tableCellList = cells;
	}

	
	void addListener(EventListener listener) {
		for(TableCellGUI cell : this.getGuiElements()) {
			cell.addListener(listener);
		}
	}


	void removeListener(EventListener listener) {
		for(TableCellGUI cell : this.getGuiElements()) {
			cell.removeListener(listener);
		}
	}
	
	
	@Override
	public ArrayList<TextBox> getUsedTextBoxes() {
		ArrayList<TextBox> textBoxList = new ArrayList<TextBox>();
		for(TableCellGUI element: this.getGuiElements()) {
			ArrayList<TextBox> foundElements = element.getUsedTextBoxes();
			textBoxList.addAll(foundElements);
		}
		return textBoxList;
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
	
}