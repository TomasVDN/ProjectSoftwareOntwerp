package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableCellGUI;
import events.EventReader;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		if (content == null) {
			throw new IllegalArgumentException("A table cell can't be constructed with null argument.");
		}
		this.content = content;
	}
	
	public String toString() {
		return "CELL: " + content.toString();
	}
	
	public ContentSpan getContent() {
		return this.content;
	}

	public TableCellGUI transformToGUI(int x, int y, int width, int heigth) {
		return new TableCellGUI(content.transformToGUI(x, y, width, heigth), x, y, width, heigth);
	}

}
