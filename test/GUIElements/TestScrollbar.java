package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestScrollbar {
	
	ScrollBar horizontalScrollBar;
	ScrollBar verticalScrollBar;
	
	
	@BeforeEach
	public void setup() {
		horizontalScrollBar = new ScrollBar(Direction.HORIZONTAL, 30, 50, 150, 10, 50, 100);
		verticalScrollBar = new ScrollBar(Direction.VERTICAL, 110, 80, 10, 200, 50, 200);
	}

	@Test
	void testConstructor() {
		// check attributes bigBar
		assertEquals(30, horizontalScrollBar.getX());
		assertEquals(110, verticalScrollBar.getX());
		assertEquals(50, horizontalScrollBar.getY());
		assertEquals(80, verticalScrollBar.getY());
		assertEquals(150, horizontalScrollBar.getWidth());
		assertEquals(10, verticalScrollBar.getWidth());
		assertEquals(10, horizontalScrollBar.getHeight());
		assertEquals(200, verticalScrollBar.getHeight());
		
		// check attributes smallBar
		assertEquals(horizontalScrollBar.getX(), horizontalScrollBar.getSmallBarPosition());
		assertEquals(verticalScrollBar.getY(), verticalScrollBar.getSmallBarPosition());
		assertEquals(horizontalScrollBar.getWidth() * (50/ (double) 100), horizontalScrollBar.getSmallBarSize());
		assertEquals(verticalScrollBar.getHeight()* (50/ (double) 200), verticalScrollBar.getSmallBarSize()); // equals size of bigBar because viewable size is bigger than content size
		
		// check ratios
		assertEquals(0.0, horizontalScrollBar.getScrollBarRatio());
		assertEquals(0.0, verticalScrollBar.getScrollBarRatio());
	}
	
	@Test 
	void testSetSmallBarPosition() {
		// test if the small bar position is updated with correct arguments
		horizontalScrollBar.setSmallBarPosition(50);
		assertEquals(50, horizontalScrollBar.getSmallBarPosition());
		verticalScrollBar.setSmallBarPosition(200);
		assertEquals(200, verticalScrollBar.getSmallBarPosition());
	}
	
	@Test
	void testSetSmallBarPositionTooLow() {
		// test that if a too low position results in the small bar having the same position as the big bar
		horizontalScrollBar.setSmallBarPosition(20);
		assertEquals(30, horizontalScrollBar.getSmallBarPosition());
		verticalScrollBar.setSmallBarPosition(20);
		assertEquals(80, verticalScrollBar.getSmallBarPosition());
	}
	
	@Test
	void testSetSmallBarPositionTooHigh() {
		// test that if a too high position results in the small bar having the same position as the big bar end minus the small bar size
		horizontalScrollBar.setSmallBarPosition(1000);
		assertEquals(105, horizontalScrollBar.getSmallBarPosition());
		verticalScrollBar.setSmallBarPosition(1000);
		assertEquals(230, verticalScrollBar.getSmallBarPosition());
	}
	
	@Test
	void testSetSmallBarSize() {
		// test if the small bar size is updated with correct arguments
		horizontalScrollBar.setSmallBarSize(100);
		assertEquals(100, horizontalScrollBar.getSmallBarSize());
		verticalScrollBar.setSmallBarSize(150);
		assertEquals(150, verticalScrollBar.getSmallBarSize());
	}
	
	@Test
	void testSetSmallBarSizeTooHigh() {
		// test that if a too high size results in the small bar having the same size as the big bar
		horizontalScrollBar.setSmallBarSize(1000);
		assertEquals(150, horizontalScrollBar.getSmallBarSize());
		verticalScrollBar.setSmallBarSize(1500);
		assertEquals(200, verticalScrollBar.getSmallBarSize());
	}
	
	@Test
	void testSetSmallBarPositionWithRatio() {
		// test to see if the ratio is updated and the position of the small bar is updated accordingly
		horizontalScrollBar.setSmallBarPositionWithRatio(0.25);
		assertEquals(0.25, horizontalScrollBar.getScrollBarRatio());
		assertEquals(30 + 37.5, horizontalScrollBar.getSmallBarPosition());
		verticalScrollBar.setSmallBarPositionWithRatio(0.1);
		assertEquals(0.1, verticalScrollBar.getScrollBarRatio());
		assertEquals(80 + 20, verticalScrollBar.getSmallBarPosition());
	}
	
	@Test
	void testSetSmallBarPositionWithRatioTooLow() {
		// test to see when the ratio is too low that the ratio becomes zero and the small bar position becomes the position of the big bar
		horizontalScrollBar.setSmallBarPositionWithRatio(-1);
		assertEquals(0, horizontalScrollBar.getScrollBarRatio());
		assertEquals(30, horizontalScrollBar.getSmallBarPosition());
		verticalScrollBar.setSmallBarPositionWithRatio(-3);
		assertEquals(0, verticalScrollBar.getScrollBarRatio());
		assertEquals(80, verticalScrollBar.getSmallBarPosition());
	}
	
	@Test
	void testSetSmallBarPositionWithRatioTooHigh() {
		// test to see when the ratio is too high that the ratio becomes the maximum ratio available with the current set up
		// and the small bar position becomes the position of the big bar end minus the small bar size
		horizontalScrollBar.setSmallBarPositionWithRatio(20);
		assertEquals(0.5, horizontalScrollBar.getScrollBarRatio());
		assertEquals(105, horizontalScrollBar.getSmallBarPosition());
		verticalScrollBar.setSmallBarPositionWithRatio(250);
		assertEquals(0.75, verticalScrollBar.getScrollBarRatio());
		assertEquals(230, verticalScrollBar.getSmallBarPosition());
	}
	
	@Test
	void testSetBigBarSizeToZero() {
		// test to check if an IllegalArgumentException is thrown when setting the big bar size to zero
		Exception exceptionHorizontal = assertThrows(IllegalArgumentException.class, () -> {
			horizontalScrollBar.setWidth(0);
		});
		assertTrue(exceptionHorizontal.getMessage().contains("The width of a horizontal scrollbar can't be 0."));
		Exception exceptionVertical = assertThrows(IllegalArgumentException.class, () -> {
			verticalScrollBar.setHeight(0);
		});
		assertTrue(exceptionVertical.getMessage().contains("The height of a vertical scrollbar can't be 0."));
	}
	
	@Test
	void testUpdateCorrectSmallBarSizeWithZeroTotalSize() {
		// test to check if an IllegalArgumentException is thrown when the totalSize argument is zero in the updateCorrectSmallBarSize method
		Exception exceptionHorizontal = assertThrows(IllegalArgumentException.class, () -> {
			horizontalScrollBar.updateCorrectSmallBarSize(0, 20);
		});
		assertTrue(exceptionHorizontal.getMessage().contains("The totalSize in the method updateCorrectSmallBarSize can't be 0 or smaller."));
		Exception exceptionVertical = assertThrows(IllegalArgumentException.class, () -> {
			verticalScrollBar.updateCorrectSmallBarSize(0, 1);
		});
		assertTrue(exceptionVertical.getMessage().contains("The totalSize in the method updateCorrectSmallBarSize can't be 0 or smaller."));
	}
		

}
