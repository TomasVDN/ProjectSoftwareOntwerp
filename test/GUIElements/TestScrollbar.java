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
		verticalScrollBar = new ScrollBar(Direction.VERTICAL, 110, 80, 10, 200, 200, 50);
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
		assertEquals(verticalScrollBar.getHeight(), verticalScrollBar.getSmallBarSize()); // equals size of bigBar because viewable size is bigger than content size
		
		// check ratios
		assertEquals(0.0, horizontalScrollBar.getScrollBarRatio());
		assertEquals(0.0, verticalScrollBar.getScrollBarRatio());
	}

}
