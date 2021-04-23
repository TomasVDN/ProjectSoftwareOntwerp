package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.TableCellGUI;
import GUIElements.Text;

class TestHTMLTableCell {
	
	HTMLTableCell htmlCell;
	HTMLHyperlink htmlHyperlink;
	HTMLText htmlText;
	String hyperlinkText;
	String url;
	Hyperlink guiHyperlink;
	Hyperlink guiHyperlinkReference;
	Text guiTextReference;
	TableCellGUI guiTableCell;
	TableCellGUI guiTableCellReference;
	
	void setUpReference() {
		guiTextReference = new Text(10, 20, "Hyperlink Text");
		guiHyperlinkReference = new Hyperlink(10, 20, guiTextReference, "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html", null);
		guiTableCellReference = new TableCellGUI(guiHyperlinkReference, 10, 20, 30, 40);
	}
	
	@Test
	@DisplayName("Test the normal behaviour of HTMLTableCell.")
	void testNormalBehaviour() {
		hyperlinkText = "Hyperlink Text";
		htmlText = new HTMLText(hyperlinkText);
		url = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";
		htmlHyperlink = new HTMLHyperlink(url, htmlText);
		htmlCell = new HTMLTableCell(htmlHyperlink);
		guiTableCell = htmlCell.transformToGUI(10, 20, 30, 40, null);
		setUpReference();
		assertEquals(guiHyperlinkReference.getUrl(), ((Hyperlink) guiTableCell.getGui()).getUrl());
		assertEquals(guiHyperlinkReference.getText(), ((Hyperlink) guiTableCell.getGui()).getText());
		assertEquals(guiTableCell.getX(), guiTableCellReference.getX());
		assertEquals(guiTableCell.getY(), guiTableCellReference.getY());
		assertEquals(guiTableCell.getWidth(), guiTableCellReference.getWidth());
		assertEquals(guiTableCell.getHeight(), guiTableCellReference.getHeight());
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given in the constructor.")
	void testNullBehaviour() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new HTMLTableCell(null);
		});
		assertTrue(exception.getMessage().contains("A table cell can't be constructed with null argument."));
	}

}
