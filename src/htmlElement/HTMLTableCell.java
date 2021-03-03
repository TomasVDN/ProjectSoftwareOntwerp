package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		this.content = content;
	}
	
	public String toString() {
		return "CELL: " + content.toString();
	}

	public GUIElement transformToGUI(int width, int heigth, int y, int x) {
		return content.transformToGUI(width, heigth, y, x);
	}

}
