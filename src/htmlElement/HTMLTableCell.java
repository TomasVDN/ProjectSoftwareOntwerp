package htmlElement;

import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.TableCellGUI;
import utils.FontMetricsHandle;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		this.content = content;
	}
	
	public String toString() {
		return "CELL: " + content.toString();
	}

	public TableCellGUI transformToGUI(int width, int heigth, int y, int x,FontMetricsHandle f) {
		return new TableCellGUI(content.transformToGUI(width, heigth, y , x,f),x,y,width, heigth);
	}

}
