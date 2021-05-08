package htmlElement;

import GUIElements.TableCellGUI;
import facades.Browsr;

public class HTMLTableCell {

	private ContentSpan content;
	
	public HTMLTableCell(ContentSpan content) {
		if (content == null) {
			throw new IllegalArgumentException("A table cell can't be constructed with null argument.");
		}
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "CELL: " + content.toString();
	}
	
	public ContentSpan getContent() {
		return this.content;
	}

	public TableCellGUI transformToGUI(int x, int y, int width, int heigth, Browsr browsr) {
		return new TableCellGUI(content.transformToGUI(0, 0, width, heigth, browsr), x, y, width, heigth);
	}

}
