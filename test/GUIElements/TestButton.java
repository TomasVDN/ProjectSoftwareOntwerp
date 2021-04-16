package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class TestButton {
	
	Text textOfButton = new Text(0, 0, "click");
	Button testButton = new Button(0, 0,textOfButton,false,Color.black);
	
	@Test
	void testButtonInvalidCases() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Button button = new Button(0, 0, null,false, Color.blue);
		});
		assertTrue(exception.getMessage().contains("Given text must exist"));
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			Button button = new Button(0, 0,textOfButton,false,null);
		});
		assertTrue(exception2.getMessage().contains("not a valid color"));
		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
			Button button = new Button(-1, 0,textOfButton,false,Color.black);
		});
		assertTrue(exception3.getMessage().contains("The x position, y position, width and height of a GUIElement have to be positive."));
	}



	@Test
	void testHandlePressClick() {
		testButton.handlePressClick();
		assertEquals("PressedState",testButton.getStateName());
	}

	@Test
	void testHandleReleaseClick() {
		fail("Not yet implemented");
	}

	@Test
	void testPaint() {
		fail("Not yet implemented");
	}


	@Test
	void testAddSingleClickListener() {
		fail("Not yet implemented");
	}

	@Test
	void testAddListener() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveListener() {
		fail("Not yet implemented");
	}

	@Test
	void testGetListeners() {
		fail("Not yet implemented");
	}

}
