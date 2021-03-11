package htmlElement;

import htmlElement.HTMLTable;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.*;
import canvaswindow.MyCanvasWindow;

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
	void setUp() {
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
		ArrayList<HTMLTableCell> row1Data = new ArrayList<HTMLTableCell>();
		row1Data.add(cell1);
		row1Data.add(cell2);
		row1Data.add(cell3);
		row1.setRow(row1Data);
		ArrayList<HTMLTableCell> row2Data = new ArrayList<HTMLTableCell>();
		row2Data.add(cell4);
		row2Data.add(cell5);
		row2Data.add(cell6);
		row2.setRow(row2Data);
		ArrayList<HTMLTableCell> row3Data = new ArrayList<HTMLTableCell>();
		row3Data.add(cell7);
		row3Data.add(cell8);
		row3Data.add(cell9);
		row3.setRow(row3Data);
		ArrayList<HTMLTableRow> rows = new ArrayList<HTMLTableRow>();
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
		ArrayList<HTMLTableCell> row1Data = new ArrayList<HTMLTableCell>();
		row1Data.add(cell1);
		row1Data.add(cell2);
		row1Data.add(cell3);
		row1.setRow(row1Data);
		ArrayList<HTMLTableCell> row2Data = new ArrayList<HTMLTableCell>();
		row2Data.add(cell4);
		row2Data.add(cell5);
		row2Data.add(cell6);
		row2.setRow(row2Data);
		ArrayList<HTMLTableCell> row3Data = new ArrayList<HTMLTableCell>();
		row3Data.add(cell7);
		row3Data.add(cell8);
		row3Data.add(cell9);
		row3.setRow(row3Data);
		ArrayList<HTMLTableRow> rows = new ArrayList<HTMLTableRow>();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		table.setRows(rows);
		TableGUI tableGui = table.transformToGUI(10, 10,100, 100);
		assertTrue(tableGui.getGuiRows().get(0).getGUIAtGivenIndex(0) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(0).getGUIAtGivenIndex(1) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(0).getGUIAtGivenIndex(2) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(1).getGUIAtGivenIndex(1) instanceof Text);
		assertTrue(tableGui.getGuiRows().get(2).getGUIAtGivenIndex(2) instanceof Text);
		Text textInTable1 = (Text) tableGui.getGuiRows().get(0).getGUIAtGivenIndex(0);
		Text textInTable2 = (Text) tableGui.getGuiRows().get(0).getGUIAtGivenIndex(1);
		Text textInTable3 = (Text) tableGui.getGuiRows().get(0).getGUIAtGivenIndex(2);
		Text textInTable5 = (Text) tableGui.getGuiRows().get(1).getGUIAtGivenIndex(1);
		Text textInTable9 = (Text) tableGui.getGuiRows().get(2).getGUIAtGivenIndex(2);
		assertEquals(textInTable1.getText(),"value 1");
		assertEquals(textInTable2.getText(),"value 2");
		assertEquals(textInTable3.getText(),"value 3");
		assertEquals(textInTable5.getText(),"value 5");
		assertEquals(textInTable9.getText(),"value 9");

		
	}
	
	
	@Test
	@DisplayName("Test the behaviour if null is given in the method setRows.")
	void testSetNullBehaviour() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			table.setRows(null);
		});
		assertTrue(exception.getMessage().contains("setRows method can't be given null as a parameter."));
	}
}
