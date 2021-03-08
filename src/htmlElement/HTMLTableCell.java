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

	public TableCellGUI transformToGUI(int x, int y, int width, int heigth, EventReader e) {
		return new TableCellGUI(content.transformToGUI(x, y, width, heigth, e), x, y, width, heigth,e);
	}

}
