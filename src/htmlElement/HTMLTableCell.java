package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableCellGUI;
import facades.EventReader;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		this.content = content;
	}
	
	public String toString() {
		return "CELL: " + content.toString();
	}

	public TableCellGUI transformToGUI(int width, int heigth, int y, int x,EventReader e) {
		return new TableCellGUI(content.transformToGUI(width, heigth, y , x,e),x,y,width, heigth,e);
	}

}
