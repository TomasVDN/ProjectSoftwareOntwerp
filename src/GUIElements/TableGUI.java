package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class TableGUI extends GUIElement {

	private ArrayList<TableRowGUI> guiRows;
	
	
	public TableGUI(ArrayList<TableRowGUI> guiRows, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.setGuiRows(guiRows);
		
		// TODO Auto-generated constructor stub
	}

	
	public ArrayList<TableRowGUI> getGuiRows() {
		return guiRows;
	}

	public void setGuiRows(ArrayList<TableRowGUI> guiElements) {
		this.guiRows = guiElements;
	}
	


	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Telt de hoogtes van elke row op
	 */
	@Override
	public int getHeight(){
		int totalHeight=0;
		for(int i=0; i<this.getGuiRows().size();i++) {
			int height = this.getGuiRows().get(i).getHeight();
			totalHeight+=height;
		}
		return totalHeight;
	}

}
