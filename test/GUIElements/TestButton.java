package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import EventListeners.ActionListener;

class TestButton {
	
	
	public static class ActionListenerClass implements ActionListener {


		public boolean clicked = false;

		@Override
		public void clickButton() {
			clicked = true;
		}


	};
	
	ActionListenerClass actionListener = new ActionListenerClass();
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
		assertEquals("UnpressedState",testButton.getStateName());
		testButton.handlePressClick(0,0);
		assertEquals("PressedState",testButton.getStateName());
	}

	@Test
	void testHandleReleaseClick() {
		assertEquals("UnpressedState",testButton.getStateName());
		testButton.handlePressClick(0,0);
		assertEquals("PressedState",testButton.getStateName());
		testButton.handleReleaseClick(false);
		assertEquals("UnpressedState",testButton.getStateName());
	}
	
	@Test
	void testHandleDoublePress() {
		assertEquals("UnpressedState",testButton.getStateName());
		testButton.handleReleaseClick(true);
		assertEquals("UnpressedState",testButton.getStateName());
		testButton.handlePressClick(0,0);
		assertEquals("PressedState",testButton.getStateName());
		testButton.handlePressClick(0,0);
		assertEquals("PressedState",testButton.getStateName());
	}

	@Test
	void testPaint() { //TODO william
		//fail("Not yet implemented"); 
	}


	@Test
	void testAddSingleClickListener() {
		testButton.addSingleClickListener(() ->{
			for(ActionListener listener: testButton.getListeners()) {
				listener.clickButton();
			}
		});
		testButton.addListener(actionListener);
		testButton.handlePressClick(0,0);
		testButton.handleReleaseClick(true);// release is on the button
		assertTrue(actionListener.clicked);
	}
	
	@Test
	void testAddListenerEmpty() {
		testButton.addListener(null);
		assertEquals(0,testButton.getListeners().size());
	}
	

	@Test
	void testAddListener() {
		testButton.addListener(actionListener);
		assertEquals(1,testButton.getListeners().size());
	}

	@Test
	void testRemoveListener() {
		testButton.addListener(actionListener);
		assertEquals(1,testButton.getListeners().size());
		ActionListenerClass actionListener2 = new ActionListenerClass();
		testButton.addListener(actionListener2);
		assertEquals(2,testButton.getListeners().size());
		testButton.removeListener(actionListener);
		assertEquals(1,testButton.getListeners().size());
	}



}
