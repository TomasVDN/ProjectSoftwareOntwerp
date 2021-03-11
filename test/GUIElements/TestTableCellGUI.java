package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestTableCellGUI {
	Text textGUI = new Text(0, 0, "");

	@Test
	void testSetGui() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			TableCellGUI guiCell = new TableCellGUI(null, 0, 0, 0, 0);
		});
		assertTrue(exception.getMessage().contains("not a valid gui in table cell"));
	}
	
	void invalidConstructors() {
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			TableCellGUI guiCell = new TableCellGUI(textGUI, -1, 0, 0, 0);
		});
		assertTrue(exception1.getMessage().contains("invalid position tableCell"));
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			TableCellGUI guiCell = new TableCellGUI(textGUI, 0, -1, 0, 0);
		});
		assertTrue(exception2.getMessage().contains("invalid position tableCell"));
		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
			TableCellGUI guiCell = new TableCellGUI(textGUI, 0, 0, -1, 0);
		});
		assertTrue(exception3.getMessage().contains("invalid size tableCell"));
		Exception exception4 = assertThrows(IllegalArgumentException.class, () -> {
			TableCellGUI guiCell = new TableCellGUI(textGUI, 0, 0, 0, -1);
		});
		assertTrue(exception4.getMessage().contains("invalid size tableCell"));
	}

}
