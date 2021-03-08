package htmlElement;

import htmlElement.HTMLTable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestHTMLTable {
	
	HTMLTable table;
	HTMLTableRow row1;
	HTMLTableRow row2;
	HTMLTableRow row3;
	HTMLTableCell cell1;
	HTMLTableCell cell2;
	HTMLTableCell cell3;
	HTMLTableCell cell4;
	HTMLTableCell cell5;
	HTMLTableCell cell6;
	HTMLTableCell cell7;
	HTMLTableCell cell8;
	HTMLTableCell cell9;
	HTMLText text1;
	HTMLText text2;
	HTMLText text3;
	HTMLText text4;
	HTMLText text5;
	HTMLText text6;
	HTMLText text7;
	HTMLText text8;
	HTMLText text9;

	@BeforeEach
	void setup() {
		// set up new table
		table = new HTMLTable();
		// set up new rows
		row1 = new HTMLTableRow();
		row2 = new HTMLTableRow();
		row3 = new HTMLTableRow();
		// set up new HTMLText elements
		text1 = new HTMLText("value 1");
		text2 = new HTMLText("value 2");
		text3 = new HTMLText("value 3");
		text4 = new HTMLText("value 4");
		text5 = new HTMLText("value 5");
		text6 = new HTMLText("value 6");
		text7 = new HTMLText("value 7");
		text8 = new HTMLText("value 8");
		text9 = new HTMLText("value 9");
		// set up new cells
		cell1 = new HTMLTableCell(text1);
		cell2 = new HTMLTableCell(text2);
		cell3 = new HTMLTableCell(text3);
		cell4 = new HTMLTableCell(text4);
		cell5 = new HTMLTableCell(text5);
		cell6 = new HTMLTableCell(text6);
		cell7 = new HTMLTableCell(text7);
		cell8 = new HTMLTableCell(text8);
		cell9 = new HTMLTableCell(text9);
	}
	
	@Test
	@DisplayName("Test the behaviour normal behaviour of HTMLTable.")
	void testNormalBehaviour() {
		ArrayList<HTMLTableCell> row1Data = new ArrayList();
		row1Data.add(cell1);
		row1Data.add(cell2);
		row1Data.add(cell3);
		row1.setRow(row1Data);
		ArrayList<HTMLTableCell> row2Data = new ArrayList();
		row1Data.add(cell4);
		row1Data.add(cell5);
		row1Data.add(cell6);
		row2.setRow(row2Data);
		ArrayList<HTMLTableCell> row3Data = new ArrayList();
		row1Data.add(cell7);
		row1Data.add(cell8);
		row1Data.add(cell9);
		row3.setRow(row3Data);
		ArrayList<HTMLTableRow> rows = new ArrayList();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		table.setRows(rows);
		
		HTMLTableRow[] rowsReference = new HTMLTableRow[]{row1, row2, row3};
		assertArrayEquals(rowsReference, rows.toArray());
	}
	
	@Test
	@DisplayName("Test the transformation to a GUI element.")
	void testTransformToGUI() {
		ArrayList<HTMLTableCell> row1Data = new ArrayList();
		row1Data.add(cell1);
		row1Data.add(cell2);
		row1Data.add(cell3);
		row1.setRow(row1Data);
		ArrayList<HTMLTableCell> row2Data = new ArrayList();
		row1Data.add(cell4);
		row1Data.add(cell5);
		row1Data.add(cell6);
		row2.setRow(row2Data);
		ArrayList<HTMLTableCell> row3Data = new ArrayList();
		row1Data.add(cell7);
		row1Data.add(cell8);
		row1Data.add(cell9);
		row3.setRow(row3Data);
		ArrayList<HTMLTableRow> rows = new ArrayList();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		table.setRows(rows);
		//TODO add test
	}
	
	@Test
	@DisplayName("Test the behaviour of HTMLTable when rows have different amount of cells.")
	void testDifferentAmountOfElementsInRows() {
		ArrayList<HTMLTableCell> row1Data = new ArrayList();
		row1Data.add(cell1);
		row1Data.add(cell2);
		row1Data.add(cell3);
		row1.setRow(row1Data);
		ArrayList<HTMLTableCell> row2Data = new ArrayList();
		row1Data.add(cell4);
		row1Data.add(cell5);
		row2.setRow(row2Data);
		ArrayList<HTMLTableCell> row3Data = new ArrayList();
		row1Data.add(cell7);
		row1Data.add(cell8);
		row1Data.add(cell9);
		row3.setRow(row3Data);
		ArrayList<HTMLTableRow> rows = new ArrayList();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			table.setRows(rows);
		});
		assertTrue(exception.getMessage().contains("All rows in the table need to have the same amount of elements."));
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given in the method setRows.")
	void testSetNullBehaviour() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			table.setRows(null);
		});
		assertTrue(exception.getMessage().contains("Rows can't be null."));
	
	}
}
