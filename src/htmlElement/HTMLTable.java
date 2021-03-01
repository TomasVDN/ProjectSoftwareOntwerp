package htmlElement;

import java.util.ArrayList;

public class HTMLTable extends ContentSpan {

	public HTMLTable() {
		
	}
	
	private ArrayList<HTMLTableRow> rows = new ArrayList<HTMLTableRow>();
	

	/**
	 * @return the rows
	 */
	public ArrayList<HTMLTableRow> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(ArrayList<HTMLTableRow> rows) {
		this.rows = rows;
	}

	public void addRow(HTMLTableRow rowToAdd) {
		rows.add(rowToAdd);
	}

	@Override
	public String toString() {
		String table = "TABLE: (";
		
		for (HTMLTableRow row: rows) {
			table += rows.toString() + ",";
		}
		
		return table.substring(0, table.length() - 1) + ")";
	}

}
