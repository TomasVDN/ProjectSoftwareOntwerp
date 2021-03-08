package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableCellGUI;
import events.EventReader;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		this.content = content;
	}
	
	public String toString() {
		return "CELL: " + content.toString();
	}

	public TableCellGUI transformToGUI(int x, int y, int width, int heigth) {
		return new TableCellGUI(content.transformToGUI(x, y, width, heigth), x, y, width, heigth);
	}

}
