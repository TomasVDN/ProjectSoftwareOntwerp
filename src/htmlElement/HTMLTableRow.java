package htmlElement;

import java.util.ArrayList;

import GUIElements.TableCellGUI;
import GUIElements.TableRowGUI;
import facades.Browsr;

public class HTMLTableRow {

	public HTMLTableRow() {
		
	}

	private ArrayList<HTMLTableCell> cells = new ArrayList<HTMLTableCell>();
	

	/**
	 * @return the cells
	 */
	public ArrayList<HTMLTableCell> getcells() {
		return cells;
	}

	/**
	 * @param cells the cells to set
	 */
	public void setRow(ArrayList<HTMLTableCell> cells) {
		if (cells == null) {
			throw new IllegalArgumentException("setRow method can't be given null as a parameter.");
		} 
		for (HTMLTableCell cell : cells) {
			if (cell == null) {
				throw new IllegalArgumentException("An element in the cells in setRow method can't be null.");
			}
		}
		this.cells = cells;
	}
	
	/**
	 * @return this.cells
	 */
	public ArrayList<HTMLTableCell> getRow() {
		return this.cells;
	}
	
	public void addCell(HTMLTableCell cellToAdd) {
		cells.add(cellToAdd);
	}

	@Override
	public String toString() {
		String row = "ROW: (";
		
		for (HTMLTableCell cell: cells) {
			row += cell.toString() + ",";
		}
		
		return row.substring(0, row.length() - 1) + ")";
	}
	
	public TableRowGUI transformToGUI(int x, int y, int width, int heigth) {
		ArrayList<TableCellGUI> cells = new ArrayList<TableCellGUI>();
		for(int i=0; i<this.getRow().size();i++) {
			TableCellGUI gui = this.getRow().get(i).transformToGUI(0, 0, width, heigth);
			cells.add(gui);
		}
		return new TableRowGUI(cells, x, y);
	}
}
