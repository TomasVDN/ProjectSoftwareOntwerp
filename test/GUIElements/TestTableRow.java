package GUIElements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TestTableRow {

	Text textGUI = new Text(0, 0, "");
	Text mediumText = new Text(0,0,"bla");
	Text mediumText2 = new Text(0,0,"blabla");
	Text textGUIaLotOfText = new Text(0, 0, "blablablablabla");
	Text hyperText = new Text(0,0,"This is a hyperlink");
	Hyperlink hyper = new Hyperlink(0, 0, hyperText, "https://blabla.com");
	TableCellGUI cell1 = new TableCellGUI(hyper, 0, 0, 0, 0);
	TableCellGUI cell2 = new TableCellGUI(textGUIaLotOfText, 0, 0, 0, 0);
	
	@Test
	void testGetWidth() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cell1);
		tableRow.add(cell2);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0);
		ArrayList<TableRowGUI> tableRowList=new ArrayList<>();;
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0); // checks if width of array is correct after update
		assertEquals(row.getWidth(), hyper.getWidth()+textGUIaLotOfText.getWidth());
		
	}

	@Test
	void testGetHeight() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cell1);
		tableRow.add(cell2);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0);
		ArrayList<TableRowGUI> tableRowList=new ArrayList<>();;
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0); // checks if height of array is correct after update
		assertEquals(row.getHeight(), hyper.getHeight());
	}

	@Test
	void testGetGUIAtPosition() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cell1);
		tableRow.add(cell2);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0);
		ArrayList<TableRowGUI> tableRowList=new ArrayList<>();;
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 0, 0);
		assertEquals(guiTable.getGUIAtPosition(hyper.getWidth()+1, 0),textGUIaLotOfText);
	}
	

	@Test
	void testGetGUIAtPosition2() {
		ArrayList<TableCellGUI> tableRow = new ArrayList<>();
		tableRow.add(cell2);
		tableRow.add(cell1);
		TableRowGUI row = new TableRowGUI(tableRow, 0, 0);
		ArrayList<TableRowGUI> tableRowList=new ArrayList<>();;
		tableRowList.add(row);
		TableGUI guiTable = new TableGUI(tableRowList, 10, 0);
		assertEquals(guiTable.getGUIAtPosition(10+textGUIaLotOfText.getWidth()+1, 0),hyper);
	}

	
}
