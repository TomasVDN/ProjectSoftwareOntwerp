package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableCellGUI;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		this.content = content;
	}
	
	public String toString() {
		return "CELL: " + content.toString();
	}

	public TableCellGUI transformToGUI(int width, int heigth, int y, int x) {
		return new TableCellGUI(content.transformToGUI(width, heigth, y , x),width, heigth, y, x);
	}

}
