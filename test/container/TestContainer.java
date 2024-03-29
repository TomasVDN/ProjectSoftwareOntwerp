package container;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GUIElements.*;
import GUIElements.Text;

class TestContainer {

	
	Container container = new Container(100, 100, 500, 500);
	Text text1;
	Text text2;
	Button button1;
	Button button2;
	ArrayList<TableRowGUI> rows = new ArrayList<TableRowGUI>();
	TableGUI table = new TableGUI(new ArrayList<TableRowGUI>(),0,100);
			
	@BeforeEach
	void setUp() throws InvocationTargetException, InterruptedException {
		text1 = new Text(0, 0, "hallo");
		button1 = new Button(0, 0, 10, 10, text1, true, Color.BLACK);
		text2 = new Text(0, 0, "wereld");
		button2 = new Button(10, 0, 10, 10, text2, true, Color.BLACK);

	}
	
	@Test
	void testContainerEmpty() {
		assertEquals(container.getElements().size(), 0);
	}
	
	@Test
	void testReset() {
		container.addElement(button1);
		ArrayList<GUIElement> list = new ArrayList<GUIElement>();
		list.add(button2);
		container.resetAllElements(list);
		assertEquals(container.getElements().size(), 1);
	}
	
	@Test
	void testContainsPoint() {
		assertTrue(container.containsPoint(100, 101));
	}
	
	@Test
	void testNotContainsPoint() {
		assertFalse(container.containsPoint(100, 99));
	}
	
	@Test
	void testGetGUI() {
		ArrayList<GUIElement> list = new ArrayList<GUIElement>();
		list.add(button1);
		list.add(button2);
		container.addMultipleElements(list);
		assertEquals(button1, container.getGUIAtPosition(101, 101));
	}
	
	@Test 
	void clickNoElements() {
		ArrayList<GUIElement> list = new ArrayList<GUIElement>();
		list.add(button1);
		list.add(button2);
		container.addMultipleElements(list);
		assertEquals(null, container.getGUIAtPosition(101, 111));
	}
	
	
	@Test
	void addMultipleElements() {
		container.addElement(button1);
		container.addElement(button2);
		container.addElement(table);
		assertEquals(container.getElements().size(), 3);
	}
	
	@Test
	void addNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			container.addElement(null);
		});
		assertTrue(exception.getMessage().contains("Can't add null to a container"));
	}
	
	@Test
	void addAllNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			container.addMultipleElements(null);
		});
		assertTrue(exception.getMessage().contains("Can't add null to a container"));
	}
	
	

}
