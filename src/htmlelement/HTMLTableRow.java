package htmlelement;

import java.util.ArrayList;

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
	public void setRows(ArrayList<HTMLTableCell> cells) {
		this.cells = cells;
	}
	
	public void addCell(HTMLTableCell cellToAdd) {
		cells.add(cellToAdd);
	}

	public String toString() {
		String row = "ROW: (";
		
		for (HTMLTableCell cell: cells) {
			row += cell.toString() + ",";
		}
		
		return row.substring(0, row.length() - 1) + ")";
	}
}
