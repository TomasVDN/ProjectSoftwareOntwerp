package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class that implements the table structure for the HTML table. It contains TableRowGUI's.
 *
 */
public class TableGUI extends GUIElement {

	private ArrayList<TableRowGUI> guiRows;
	int offSet=0;
	
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
	 * Constructor of the table GUI class with an offset between the TableRowGUI
	 * @param guiRows - list of TableRowGUI
	 * @param x - x coordinate of this class
	 * @param y - y coordinate of this class
	 * @param offSet - the offset of the y coordinate between TableRowGUI
	 */
	public TableGUI(ArrayList<TableRowGUI> guiRows, int x, int y, int offSet) {
		super(x,y);
		this.setGuiRows(guiRows);
		this.offSet = offSet;
	}
	
	/**
	 * Sets the offset of the TableGUI
	 */
	public void setOffSet(int newOffset) {
		this.offSet = newOffset;
	}
	
	/**
	 * @return returns the offset of the TableGUI
	 */
	public int getOffSet() {
		return this.offSet;
	}
	

	/**
	 * @return this.guiRows
	 */
	public ArrayList<TableRowGUI> getGuiRows() {
		return guiRows;
	}

	/**
	 * @param guiElements - new value of this.guiRows
	 * @throws IllegalArgumentException - thrown if guiElements is null
	 */
	public void setGuiRows(ArrayList<TableRowGUI> guiElements) throws IllegalArgumentException {
		if(guiElements==null) {
			throw new IllegalArgumentException("rows of guiTable can't be null");
		}
		this.guiRows = guiElements;
		updateTableCells();
	}

	/**
	 * Paints all the components of this table.
	 */
	@Override
	public void paint(Graphics g) {		
		Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);//TODO dit 52+26 maar waarom dit getal
		for(int i=0; i< this.getGuiRows().size();i++) {
			this.getGuiRows().get(i).paint(newG);
		}
	}
	
	/**
	 * Calculates the height of the table by iterating through all rows.
	 */
	@Override
	public int getHeight(){
		int totalHeight=0;
		for(int i=0; i<this.getGuiRows().size();i++) {
			int height = this.getGuiRows().get(i).getHeight();
			if(i!=0) {
				height+=this.getOffSet();
			}
			totalHeight+=height;
		}
		return totalHeight;
	}
	
	/**
	 * Calculates the height of the table by iterating through all rows.
	 */
	@Override
	public int getWidth(){
		int maxWidth=0;
		for(int i=0; i<this.getGuiRows().size();i++) {
			int width = this.getGuiRows().get(i).getWidth();
			if(maxWidth<width) {
				maxWidth=width;
			}
		}
		return maxWidth;
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
	private void updateTableCells() { // TODO terug private maken, even public gemaakt om table van bookmarks juist te tekenen in de MainDialog
		ArrayList<Integer> rowHeight =this.getAllRowHeight();
		ArrayList<Integer> colomnWidth =this.getAllColumnWidth();
		int yPosition=0;
		for(int i=0;i<rowHeight.size();i++) {//row
			if(i!=0) {
				yPosition+=this.getOffSet();
			}
			this.getGuiRows().get(i).setPosition(0, yPosition);// set the row to the new position
			this.getGuiRows().get(i).setHeight(rowHeight.get(i));
			yPosition+=rowHeight.get(i); // add the height 
			for(int j=0; j<this.getGuiRows().get(i).size();j++) { // column
				this.getGuiRows().get(i).updateTableCells(colomnWidth.get(j), j);
			}
		}
	}
	
	/**
	 * Put the given GUI in a TableCell.
	 * Then adds this table cell to the tableRow at the given index.
	 * @param gui - the given GUIElement to add
	 * @param index
	 */
	public void addGUITo(GUIElement gui,int index) {
		TableCellGUI cell = new TableCellGUI(gui, 0, 0, 0, 0); // Values aren't important because an update is going to happen
		this.getGuiRows().get(index).addTableCell(cell);
		this.updateTableCells();
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

	/**
	 * Returns an array with all the elements in this Container of the given class.
	 */
	@Override
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array){
			for(GUIElement element: this.getGuiRows()) {
			element.getGuiClass(cls, array);
		}
		return array;
	}

}



