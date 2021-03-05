package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableGUI;
import GUIElements.TableRowGUI;
import utils.FontMetricsHandle;

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
	public GUIElement transformToGUI(int width, int height, int y, int x,FontMetricsHandle f) {
		int nextY=y;
		ArrayList<TableRowGUI> GUIRows= new ArrayList<TableRowGUI>();
		for(int i=0; i<this.getRows().size();i++) {
			TableRowGUI gui = this.getRows().get(i).transformToGUI(width, height, nextY, x,f);
			//int Nexty = y + gui.getHeight() ;//+ YSPACE;
			GUIRows.add(gui);
		}
		return new TableGUI(GUIRows,x,y,width,height);
	}

}
