package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.EventListener;

public class TableGUI extends GUIElement {

	private ArrayList<TableRowGUI> guiRows;
	
	/**
	 * Constructor of the table GUI class.
	 * @param guiRows - list of TableRowGUI
	 * @param x - x coordinate of this class
	 * @param y - y coordinate of this class
	 */
	public TableGUI(ArrayList<TableRowGUI> guiRows, int x, int y) {
		super(x, y);
		this.setGuiRows(guiRows);
	}

	/**
	 * @return this.guiRows
	 */
	public ArrayList<TableRowGUI> getGuiRows() {
		return guiRows;
	}

	public void setGuiRows(ArrayList<TableRowGUI> guiElements) throws IllegalArgumentException {
		if(guiElements==null) {
			throw new IllegalArgumentException("rows of guiTable can't be null");
		}
		this.guiRows = guiElements;
		updateTableCells();
	}

	@Override
	public void paint(Graphics g) {
		g.translate(this.getX(), this.getY());		
		for(int i=0; i< this.getGuiRows().size();i++) {
			this.getGuiRows().get(i).paint(g);
		}
		g.translate(-this.getX(), -this.getY());
	}
	
	/**
	 * Calculates the height of the table by iterating through all rows.
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
	 * Calculates the height of the table by iterating through all rows.
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
	 * @return column[index]
	 */
	public ArrayList<GUIElement> getColumn(int index){
		ArrayList<GUIElement> column= new ArrayList<GUIElement>();
		for(int i = 0 ; i<this.getGuiRows().size();i++) {
			GUIElement gui = this.getGuiRows().get(i).getGUIAtGivenIndex(index);
			if(gui!=null) {
				column.add(gui);
			}
		}
		return column;
	}
	
	/**
	 * Returns the maximum width in the given array.
	 * @param array
	 * @return max(array.element.width)
	 */
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
	
	/**
	 * Returns the width of all the columns.
	 * @return columnWidth
	 */
	public ArrayList<Integer> getAllColumnWidth(){
		ArrayList<ArrayList<GUIElement>> allColumns = this.getAllColumns();
		ArrayList<Integer> columnWidth = new ArrayList<Integer>();
		for(int i=0;i<allColumns.size();i++) {
			columnWidth.add(getMaxWidth(allColumns.get(i)));
		}
		return columnWidth;
	}
	
	/**
	 * Returns the height of all the rows.
	 * @return rowHeight
	 */
	public ArrayList<Integer> getAllRowHeight(){
		ArrayList<Integer> rowHeight = new ArrayList<Integer>();
		for(int i=0;i<this.getGuiRows().size();i++) {
			rowHeight.add(this.getGuiRows().get(i).getHeight());
		}
		return rowHeight;
	}
	
	/**
	 * Gives back all columns
	 * @return
	 */
	public ArrayList<ArrayList<GUIElement>> getAllColumns(){
		ArrayList<ArrayList<GUIElement>> allColumns = new ArrayList<ArrayList<GUIElement>>();
		int size= this.getMaxNumberOfColumns();
		for(int i=0; i<size;i++) {
			allColumns.add(this.getColumn(i));
		}
		return allColumns;
	}
	
	/**
	 * Returns the max number of columns.
	 * @return
	 */
	public int getMaxNumberOfColumns() {
		int max=0;
		for(int i=0;i<this.getGuiRows().size();i++) {
			int size= this.getGuiRows().get(i).size();
			if(size>max) {
				max=size;
			}
		}
		return max;
	}
	
	/**
	 * When this method is called all table cells would be aligned such that:
	 * 		height is the max height in the row
	 * 		width is the max width in the column
	 * Furthermore their relative positions are also updated
	 */
	public void updateTableCells() { // TODO terug private maken, even public gemaakt om table van bookmarks juist te tekenen in de MainDialog
		ArrayList<Integer> rowHeight =this.getAllRowHeight();
		ArrayList<Integer> colomnWidth =this.getAllColumnWidth();
		int yPosition=0;
		for(int i=0;i<rowHeight.size();i++) {//row
			int xPosition=0;
			this.getGuiRows().get(i).setPosition(0, yPosition);// set the row to the new position
			this.getGuiRows().get(i).setHeight(rowHeight.get(i));
			yPosition+=rowHeight.get(i); // add the height 
			for(int j=0; j<this.getGuiRows().get(i).size();j++) { // column
				this.getGuiRows().get(i).getGuiElements().get(j).setPosition(xPosition,0);
				this.getGuiRows().get(i).getGuiElements().get(j).setWidth(colomnWidth.get(j));
				xPosition+=colomnWidth.get(j);// add the column width to the position
			}
		}
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	@Override
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
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
	}
	
	@Override
	public void handleUnselect() {
	}
	
	@Override
	public void handleClick() {		
	}

	
	
	@Override
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array){
			for(GUIElement element: this.getGuiRows()) {
			element.getGuiClass(cls, array);
		}
		return array;
	}

}



