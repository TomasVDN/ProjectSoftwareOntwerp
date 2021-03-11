package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestTableGUI {

	Text textGUI = new Text(0, 0, "");
	Text textGUIaLotOfText = new Text(0, 0, "blablablablabla");
	Text hyperText = new Text(0,0,"This is a hyperlink");
	Hyperlink hyper = new Hyperlink(0, 0, hyperText, "https://blabla.com");
	TableCellGUI cell1 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell2 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell3 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell4 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell5 = new TableCellGUI(textGUIaLotOfText, 0, 0, 0, 0);

	
	@Test
	void testTableNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			TableGUI guiTable = new TableGUI(null, 0, 0, 0, 0);
		});
		assertTrue(exception.getMessage().contains("rows of guiTable can't be null"));
	}
	

	@Test
	void testGetWidth() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cell1);
		tableRow.add(cell2);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
	}

	@Test
	void testGetHeight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetGUIAtPosition() {
		fail("Not yet implemented");
	}

	@Test
	void testGetColumn() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMaxWidth() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllColumnWidth() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllRowHeight() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllColumns() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMaxNumberOfColumns() {
		fail("Not yet implemented");
	}

}
