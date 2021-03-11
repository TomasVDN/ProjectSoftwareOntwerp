package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;


public class HTMLTable extends ContentSpan {

	/**
	 * The distance between the guiRows in the table
	 */
	private static int YSPACE= 3;
	
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
		if (rows == null) {
			throw new IllegalArgumentException("setRows method can't be given null as a parameter.");
		} 
		this.rows = rows;
	}
	

	public void addRow(HTMLTableRow rowToAdd) {
		rows.add(rowToAdd);
	}

	@Override
	public String toString() {
		String table = "TABLE: (";
		
		for (HTMLTableRow row: rows) {
			table += row.toString() + ",";
		}
		
		return table.substring(0, table.length() - 1) + ")";
	}
	/**
	 * x and y are the relative top left side, creates a TableGUI element
	 */
	@Override
	public TableGUI transformToGUI(int x, int y, int width, int height) {
		ArrayList<TableRowGUI> GUIRows= new ArrayList<TableRowGUI>();
		for(int i=0; i<this.getRows().size();i++) {
			TableRowGUI gui = this.getRows().get(i).transformToGUI(0, 0, width, height);
			GUIRows.add(gui);
		}
		return new TableGUI(GUIRows, x, y, width, height);
	}
}
