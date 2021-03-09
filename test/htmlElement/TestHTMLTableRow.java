package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import GUIElements.TableRowGUI;
import canvaswindow.MyCanvasWindow;
import GUIElements.TableCellGUI;

class TestHTMLTableRow {

	String textValue1 = "text example one";
	String textValue2 = "text example two";
	String textValue3 = "text example three";
	String url = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";
	HTMLTableRow htmlRow;
	HTMLTableCell htmlCell1;
	HTMLTableCell htmlCell2;
	HTMLTableCell htmlCell3;
	HTMLText htmlText1;
	HTMLText htmlText2;
	HTMLText htmlText3;
	HTMLHyperlink htmlHyperlink1;
	TableRowGUI guiRow;
	
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		java.awt.EventQueue.invokeAndWait(() -> {
			MyCanvasWindow window = new MyCanvasWindow("Browsr");
		});
		htmlText1 = new HTMLText(textValue1);
		htmlText2 = new HTMLText(textValue2);
		htmlText3 = new HTMLText(textValue3);
		htmlHyperlink1 = new HTMLHyperlink(url, htmlText3);
		htmlCell1 = new HTMLTableCell(htmlText1);
		htmlCell2 = new HTMLTableCell(htmlHyperlink1);
		htmlCell3 = new  HTMLTableCell(htmlText3);
		htmlRow = new HTMLTableRow();
	}
	
	@Test
	@DisplayName("Test the behaviour normal behaviour of HTMLTableRow.")
	void testNormalBehaviour() {
		ArrayList<HTMLTableCell> rowData = new ArrayList<HTMLTableCell>();
		rowData.add(htmlCell1);
		rowData.add(htmlCell2);
		rowData.add(htmlCell3);
		htmlRow.setRow(rowData);
		htmlRow.transformToGUI(10, 20, 30, 40);
		// TODO juiste testen toevoegen
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given in the method setRow.")
	void testSetRowNullBehaviour() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			htmlRow.setRow(null);
		});
		assertTrue(exception.getMessage().contains("setRow method can't be given null as a parameter."));
	}
	
	@Test
	@DisplayName("Test the behaviour if one element is null in the method setRow.")
	void testSetRowWithANullElementBehaviour() {
		ArrayList<HTMLTableCell> rowData = new ArrayList<HTMLTableCell>();
		rowData.add(htmlCell1);
		rowData.add(null);
		rowData.add(htmlCell3);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			htmlRow.setRow(rowData);
		});
		assertTrue(exception.getMessage().contains("An element in the setRow method can't be null."));
	}
}
