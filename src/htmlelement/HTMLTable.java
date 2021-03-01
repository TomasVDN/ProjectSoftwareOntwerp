package htmlelement;

import java.util.ArrayList;

public class HTMLTable extends ContentSpan {

	public HTMLTable() {
		
	}
	
	private ArrayList<HTMLTableRow> rows;
	

	/**
	 * @return the rows
	 */
	public ArrayList<HTMLTableRow> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void addRows(ArrayList<HTMLTableRow> rows) {
		this.rows = rows;
	}

}
