package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableRowGUI;

public class HTMLTableRow {

	/**
	 * The distance between the guiElements in the row
	 */
	private static int XSPACE= 3;
	
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
		this.cells = cells;
	}
	
	/**
	 * @param cells the cells to set
	 */
	public ArrayList<HTMLTableCell> getRow() {
		return this.cells;
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
	
	public TableRowGUI transformToGUI(int width, int heigth, int y, int x) {
		int nextX =x;
		ArrayList<GUIElement> GUIRows= new ArrayList<GUIElement>();
		for(int i=0; i<this.getRow().size();i++) {
			GUIElement gui = this.getRow().get(i).transformToGUI(width, heigth, y, nextX);
			nextX+= gui.getWidth() + XSPACE;
			GUIRows.add(gui);
		}
		return new TableRowGUI(GUIRows,x,y,width,heigth);
	}
}
