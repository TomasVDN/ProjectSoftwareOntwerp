package GUIElements;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import EventListeners.FormListener;
import EventListeners.SeparatorBarMoveListener;
import GUIElements.Direction;

class TestSeperatorBar {
	
	Container testContainer = new Container(0, 0, 100, 100);
	int testPosition = 50;
	SeperatorBar barVertical = new SeperatorBar(testContainer,testPosition,Direction.VERTICAL);
	SeperatorBar barHorizontal = new SeperatorBar(testContainer,testPosition,Direction.HORIZONTAL);
	
	
	public static class SeperatorBarMoveClass implements SeparatorBarMoveListener {

		boolean moved = false;
		
		@Override
		public void seperatorBarMoved() {
			this.moved=true;
		}


	};
	
	
	@Before
	public void  init() {
		testContainer.addElement(barVertical);
		testContainer.addElement(barHorizontal);
	}
	
	
	@Test
	void constructorTest() {
		int position =0;
		Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
			SeperatorBar bar =new SeperatorBar(null,position, Direction.VERTICAL);
		});
		assertTrue(exception1.getMessage().contains("not a valid container"));
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			SeperatorBar bar =new SeperatorBar(testContainer,-1, Direction.VERTICAL);
		});
		assertTrue(exception2.getMessage().contains("The x position, y position, width and height of a GUIElement have to be positive."));
		Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
			SeperatorBar bar =new SeperatorBar(testContainer,position, null);
		});
		assertTrue(exception3.getMessage().contains("Not a valid direction"));
		
	}
	
	@Test
	public void testPositionBar() {
		int[] pos = barVertical.positionBar(Direction.VERTICAL, 50);
		assertEquals(50,pos[0]);
		assertEquals(0,pos[1]);
		pos = barVertical.positionBar(Direction.HORIZONTAL, 50);
		assertEquals(0,pos[0]);
		assertEquals(50,pos[1]);
		
	}
	
	@Test
	public void testPositionConstructBar() {
		assertEquals(50,barVertical.getX());
		assertEquals(0,barVertical.getY());
		assertEquals(0,barHorizontal.getX());
		assertEquals(50,barHorizontal.getY());
		
	}

	@Test
	void testHandlePressClick() {
		// Clicks on the seperatorBar
		barVertical.handlePressClick(50, 1);
		assertEquals(barVertical.getOffsetReference(),50);
	}

	@Test
	void testHandleDragMouseEasy() {
		barVertical.handlePressClick(50, 1);
		barVertical.handleDragMouse(20, 1, 0, 0);
		assertEquals(barVertical.getX(),20);
	}

	@Test
	void testPaint() {
		//fail("Not yet implemented");
	}


	@Test
	void testUpdateBar() {
		testContainer.setWidth(150); // increases the width of the container
		barHorizontal.updateBar();
		assertEquals(barHorizontal.getWidth(),testContainer.getWidth());
	}

	@Test
	void testChangeXPosition() {
		barVertical.changeXPosition(-10);
		assertEquals(barVertical.getWidth(), barVertical.getX());
		barVertical.changeXPosition(1000);
		assertEquals(100-barVertical.getWidth(),barVertical.getX());
	}

	@Test
	void testChangeYPosition() {
		barHorizontal.changeYPosition(-10);
		assertEquals(barHorizontal.getHeight(), barHorizontal.getY());
		barHorizontal.changeYPosition(1000);
		assertEquals(100-barHorizontal.getHeight(),barHorizontal.getY());
	}



	@Test
	void testAddSeparatorBarMoveListener() {
		SeperatorBarMoveClass list1 = new SeperatorBarMoveClass();
		SeperatorBarMoveClass list2 = new SeperatorBarMoveClass();
		barVertical.addSeparatorBarMoveListener(list1);
		barVertical.addSeparatorBarMoveListener(list2);
		barVertical.addSeparatorBarMoveListener(null);
		assertEquals(2,barVertical.getSeparatorBarMoveListeners().size());
	}


	@Test
	void testRemoveSeparatorBarMoveListeners() {
		SeperatorBarMoveClass list1 = new SeperatorBarMoveClass();
		SeperatorBarMoveClass list2 = new SeperatorBarMoveClass();
		barVertical.addSeparatorBarMoveListener(list1);
		barVertical.addSeparatorBarMoveListener(list2);
		barVertical.removeSeparatorBarMoveListener(list1);
		assertEquals(1,barVertical.getSeparatorBarMoveListeners().size());
	}

	@Test
	void moveSeperatorBar() {
		SeperatorBarMoveClass listener = new SeperatorBarMoveClass();
		barVertical.addSeparatorBarMoveListener(listener);
		barVertical.handlePressClick(50, 10);
		barVertical.handleDragMouse(70, -10, 0, 0);
		assertTrue(listener.moved);
		assertEquals(70,barVertical.getX());
		barVertical.handlePressClick(70, 0);
		barVertical.handleDragMouse(10, 10, 0, 0);
		assertEquals(10,barVertical.getX());
	}
}
