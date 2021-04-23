package GUIElements;

import java.util.ArrayList;

public class TableGUIWithOffset extends TableGUI {
	
	int offSet;
	
	public TableGUIWithOffset(ArrayList<TableRowGUI> guiRows, int x, int y, int offSet) {
		super(guiRows, x, y);
		this.offSet = offSet;
	}
	
	public void setOffSet(int newOffset) {
		this.offSet = newOffset;
	}
	
	public int getOffSet() {
		return this.offSet;
	}
	
	/**
	 * When this method is called all table cells would be aligned such that:
	 * 		height is the max height in the row
	 * 		width is the max width in the column
	 * Furthermore their relative positions are also updated
	 */
	@Override
	protected void updateTableCells() {
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
				xPosition+=colomnWidth.get(j) + this.getOffSet();// add the column width to the position
			}
		}
	}

}
