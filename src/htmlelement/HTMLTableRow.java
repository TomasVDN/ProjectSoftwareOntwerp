package htmlelement;

import java.util.ArrayList;

public class HTMLTableRow {

	public HTMLTableRow() {
		
	}

	private ArrayList<HTMLTableCell> cells;
	

	/**
	 * @return the cells
	 */
	public ArrayList<HTMLTableCell> getcells() {
		return cells;
	}

	/**
	 * @param cells the cells to set
	 */
	public void addRows(ArrayList<HTMLTableCell> cells) {
		this.cells = cells;
	}

}
