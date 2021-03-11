package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestTableGUI {

	Text textGUI = new Text(0, 0, "");
	Text mediumText = new Text(0,0,"bla");
	Text mediumText2 = new Text(0,0,"blabla");
	Text textGUIaLotOfText = new Text(0, 0, "blablablablabla");
	Text hyperText = new Text(0,0,"This is a hyperlink");
	Hyperlink hyper = new Hyperlink(0, 0, hyperText, "https://blabla.com");
	TableCellGUI cell1 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell2 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell3 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cell4 = new TableCellGUI(textGUI, 0, 0, 0, 0);
	TableCellGUI cellLot = new TableCellGUI(textGUIaLotOfText, 0, 0, 0, 0);
	TableCellGUI cellmed = new TableCellGUI(mediumText, 0, 0, 0, 0);
	TableCellGUI cellLot2 = new TableCellGUI(textGUIaLotOfText, 0, 0, 0, 0);
	TableCellGUI cellmed2 = new TableCellGUI(mediumText2, 0, 0, 0, 0);
	TableCellGUI cellLot3 = new TableCellGUI(textGUIaLotOfText, 0, 0, 0, 0);
	TableCellGUI cellmed3 = new TableCellGUI(mediumText2, 0, 0, 0, 0);

	
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
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		assertEquals(guiTable.getWidth(),0);
	}
	
	@Test
	void testGetWidth2() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cellLot);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		assertEquals(guiTable.getWidth(),textGUIaLotOfText.getWidth());
	}

	@Test
	void testGetHeight() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cell1);
		tableRow.add(cell2);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		assertEquals(guiTable.getHeight(),textGUI.getHeight()); //TODO lege cell ook een hoogte?
	}

	@Test
	void testGetGUIAtPosition() {
		ArrayList<TableCellGUI> tableRow1 = new ArrayList<>();
		tableRow1.add(cellmed);
		tableRow1.add(cellmed2);
		TableRowGUI row1 = new TableRowGUI(tableRow1, 0, 0, 0, 0);
		ArrayList<TableCellGUI> tableRow2 = new ArrayList<>();
		tableRow2.add(cellLot);
		tableRow2.add(cellmed3);
		TableRowGUI row2 = new TableRowGUI(tableRow2, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row1);
		tableRowList.add(row2);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		assertEquals(guiTable.getGUIAtPosition(0, 0),mediumText);
		assertEquals(guiTable.getGUIAtPosition(0, mediumText.getHeight()+1), textGUIaLotOfText);
		
		
	}

	@Test
	void testGetColumn() {
		ArrayList<TableCellGUI> tableRow1 = new ArrayList<>();
		tableRow1.add(cellmed);
		tableRow1.add(cellmed2);
		TableRowGUI row1 = new TableRowGUI(tableRow1, 0, 0, 0, 0);
		ArrayList<TableCellGUI> tableRow2 = new ArrayList<>();
		tableRow2.add(cellLot);
		tableRow2.add(cellmed3);
		TableRowGUI row2 = new TableRowGUI(tableRow2, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row1);
		tableRowList.add(row2);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		ArrayList<GUIElement> column1= guiTable.getColumn(0);
		assertEquals(column1.get(0),mediumText);
		assertEquals(column1.get(1),textGUIaLotOfText);
	}

	@Test
	void testGetMaxWidth() {
		ArrayList<TableCellGUI> tableRow1 = new ArrayList<>();
		tableRow1.add(cellmed);
		tableRow1.add(cellmed2);
		TableRowGUI row1 = new TableRowGUI(tableRow1, 0, 0, 0, 0);
		ArrayList<TableCellGUI> tableRow2 = new ArrayList<>();
		tableRow2.add(cellLot);
		tableRow2.add(cellmed3);
		TableRowGUI row2 = new TableRowGUI(tableRow2, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row1);
		tableRowList.add(row2);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		ArrayList<GUIElement> column1= guiTable.getColumn(0);
		assertEquals(TableGUI.getMaxWidth(column1), textGUIaLotOfText.getWidth());
	}

	@Test
	void testGetAllColumnWidth() {
		ArrayList<TableCellGUI> tableRow1 = new ArrayList<>();
		tableRow1.add(cellmed);
		tableRow1.add(cellLot2);
		TableRowGUI row1 = new TableRowGUI(tableRow1, 0, 0, 0, 0);
		ArrayList<TableCellGUI> tableRow2 = new ArrayList<>();
		tableRow2.add(cellLot);
		tableRow2.add(cellmed3);
		TableRowGUI row2 = new TableRowGUI(tableRow2, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row1);
		tableRowList.add(row2);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		ArrayList<Integer> maxWidths = guiTable.getAllColumnWidth();
		assertEquals(maxWidths.get(0), textGUIaLotOfText.getWidth());
		assertEquals(maxWidths.get(1), textGUIaLotOfText.getWidth());
	}

	@Test
	void testGetAllRowHeight() {
		ArrayList<TableCellGUI> tableRow1 = new ArrayList<>();
		tableRow1.add(cellmed);
		tableRow1.add(cellLot2);
		TableRowGUI row1 = new TableRowGUI(tableRow1, 0, 0, 0, 0);
		ArrayList<TableCellGUI> tableRow2 = new ArrayList<>();
		tableRow2.add(cellLot);
		tableRow2.add(cellmed3);
		TableRowGUI row2 = new TableRowGUI(tableRow2, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row1);
		tableRowList.add(row2);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		ArrayList<Integer> maxHeight = guiTable.getAllRowHeight();
		assertEquals(maxHeight.get(0), textGUIaLotOfText.getHeight());
		assertEquals(maxHeight.get(1), textGUIaLotOfText.getHeight());
	}


	@Test
	void testGetMaxNumberOfColumns() {
		ArrayList<TableCellGUI> tableRow1 = new ArrayList<>();
		tableRow1.add(cellmed);
		tableRow1.add(cellLot2);
		TableRowGUI row1 = new TableRowGUI(tableRow1, 0, 0, 0, 0);
		ArrayList<TableCellGUI> tableRow2 = new ArrayList<>();
		tableRow2.add(cellLot);
		tableRow2.add(cellmed3);
		tableRow2.add(cell3);
		TableRowGUI row2 = new TableRowGUI(tableRow2, 0, 0, 0, 0);
		ArrayList<TableRowGUI> tableRowList= new ArrayList<>();
		tableRowList.add(row1);
		tableRowList.add(row2);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0, 0, 0);
		assertEquals(guiTable.getMaxNumberOfColumns(),3);
	}

}
