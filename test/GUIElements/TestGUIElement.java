package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestGUIElement {

	Container cont = new Container(0, 0, 10, 10);
	Text text = new Text(0, 0, "");
	Hyperlink hyper = new Hyperlink(0, 0, text,"", null);
	
	@Test
	void testContainsPoint() {
		assertTrue(cont.containsPoint(0, 0));
		assertFalse(cont.containsPoint(100, 0));
		
	}

	@Test
	void testGetGuiClass() {
		for(int i =0;i<7;i++) {
			cont.addElement(hyper);
		}
		cont.addElement(text);
		cont.addElement(text);
		ArrayList<Hyperlink> array1= new ArrayList<Hyperlink>();
		ArrayList<Text> array2= new ArrayList<Text>();
		assertEquals(7,cont.getGuiClass(Hyperlink.class, array1).size());
		assertEquals(9,cont.getGuiClass(Text.class, array2).size());
	}

}
