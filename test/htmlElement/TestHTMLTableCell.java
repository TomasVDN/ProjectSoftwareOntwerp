package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import GUIElements.TableCellGUI;
import GUIElements.Text;
import facades.EventReader;

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
	EventReader eventReader = new EventReader(null);
	
	void setUpReference() {
		guiTextReference = new Text(10, 20, 30, 40, eventReader, "Hyperlink Text");
		guiHyperlinkReference = new Hyperlink(10, 20, eventReader, guiTextReference, "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html");
		guiTableCellReference = new TableCellGUI(guiHyperlinkReference, 10, 20, 30, 40, eventReader);
	}
	
	@Test
	@DisplayName("Test the normal behaviour of HTMLTableCell.")
	void testNormalBehaviour() {
		hyperlinkText = "Hyperlink Text";
		htmlText = new HTMLText(hyperlinkText);
		url = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";
		htmlHyperlink = new HTMLHyperlink(url, htmlText);
		htmlCell = new HTMLTableCell(htmlHyperlink);
		guiTableCell = htmlCell.transformToGUI(10, 20, 30, 40, eventReader);
		setUpReference();
		assertEquals(guiTableCellReference, guiTableCell);
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
