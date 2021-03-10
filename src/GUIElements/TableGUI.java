package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import events.EventReader;

public class TableGUI extends GUIElement {

	private ArrayList<TableRowGUI> guiRows;
	
	//TODO geen breedte en hoogt meegeven
	public TableGUI(ArrayList<TableRowGUI> guiRows, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGuiRows(guiRows);
		this.updateTableCells();

	}

	
	public ArrayList<TableRowGUI> getGuiRows() {
		return guiRows;
	}

	public void setGuiRows(ArrayList<TableRowGUI> guiElements) {
		this.guiRows = guiElements;
	}
	

	
	
	

	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		int relativeY=yContainer;
		for(int i=0; i< this.getGuiRows().size();i++) {
			this.getGuiRows().get(i).paint(g, xContainer, relativeY);
			relativeY+=this.getGuiRows().get(i).getHeight();
		}
	}
	
	/**
	 * Telt de hoogtes van elke row op
	 */
	@Override
	public int getHeight(){
		int totalHeight=0;
		for(int i=0; i<this.getGuiRows().size();i++) {
			int height = this.getGuiRows().get(i).getHeight();
			totalHeight+=height;
		}
		return totalHeight;
	}
	
	/**
	 * Telt de width van de row 
	 */
	@Override
	public int getWidth(){
		int totalWidth=0;
		for(int i=0; i<this.getGuiRows().size();i++) {
			int width = this.getGuiRows().get(i).getWidth();
			totalWidth+=width;
		}
		return totalWidth;
	}
	
	/**
	 * return the column at the given index
	 * @param index
	 * @return
	 */
	public ArrayList<GUIElement> getColumn(int index){
		if (index > this.getGuiRows().size()) {
			throw new IndexOutOfBoundsException("ColumnIndex not valid");
		}
		ArrayList<GUIElement> column= new ArrayList<GUIElement>();
		for(int i = 0 ; i<this.getGuiRows().size();i++) {
			GUIElement gui = this.getGuiRows().get(i).getGUIAtGivenIndex(index);
			column.add(gui);
		}
		return column;
	}
	
	public static int getMaxWidth(ArrayList<GUIElement> array) {
		int maxWidth=0;
		for(int i=0; i<array.size();i++) {
			int width = array.get(i).getWidth();
			if(width>maxWidth) {
				maxWidth= width;
			}
		}
		return maxWidth;
	}
	
	public ArrayList<Integer> getAllColumnWidth(){
		ArrayList<ArrayList<GUIElement>> allColumns = this.getAllColumns();
		ArrayList<Integer> columnWidth = new ArrayList<Integer>();
		for(int i=0;i<allColumns.size();i++) {
			columnWidth.add(getMaxWidth(allColumns.get(i)));
		}
		return columnWidth;
	}
	
	public ArrayList<Integer> getAllRowHeight(){
		ArrayList<Integer> rowHeight = new ArrayList<Integer>();
		for(int i=0;i<this.getGuiRows().size();i++) {
			rowHeight.add(this.getGuiRows().get(i).getHeight());
		}
		return rowHeight;
	}
	
	/**
	 * gives back all columns
	 * @return
	 */
	public ArrayList<ArrayList<GUIElement>> getAllColumns(){
		ArrayList<ArrayList<GUIElement>> allColumns = new ArrayList<ArrayList<GUIElement>>();
		if(this.getGuiRows().size()!=0) {
			int size= this.getGuiRows().get(0).size();
			for(int i=0; i<size;i++) {
				allColumns.add(this.getColumn(i));
			}
		}
		return allColumns;
	}
	
	/**
	 * When this method is called all table cells would be aligned such that:
	 * 		height is the max height in the row
	 * 		width is the max width in the column
	 * Furthermore their relative positions are also updated
	 */
	private void updateTableCells() {
		ArrayList<Integer> rowHeight =this.getAllRowHeight();
		ArrayList<Integer> colomnWidth =this.getAllColumnWidth();
		int yPosition=0;
		for(int i=0;i<rowHeight.size();i++) {//row
			int xPosition=0;
			this.getGuiRows().get(i).setPosition(0, yPosition);// set the row to the new position
			this.getGuiRows().get(i).setHeight(rowHeight.get(i));
			yPosition+=rowHeight.get(i); // add the height 
			for(int j=0; j<colomnWidth.size();j++) { // column
				this.getGuiRows().get(i).getGuiElements().get(j).setPosition(xPosition,0);
				this.getGuiRows().get(i).getGuiElements().get(j).setWidth(colomnWidth.get(j));
				xPosition+=colomnWidth.get(j);// add the column width to the position
			}
		}
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
		for(int i=0; i<this.getGuiRows().size();i++) {
			GUIElement e =this.getGuiRows().get(i).getGUIAtPosition(x-this.getX(), y - this.getY());
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
