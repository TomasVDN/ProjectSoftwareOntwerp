package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestHTMLDocument {

	HTMLDocument htmlTestWithButton = new HTMLDocument(0, 0, 100, 100, "button", "<input type=\"submit\">");
	
	
	
	@Test
	void testGetActiveHTMLDocument() {
		HTMLDocument htm = htmlTestWithButton.getActiveHTMLDocument();
		assertNull(htm);
		htmlTestWithButton.setActive(true);
		htm = htmlTestWithButton.getActiveHTMLDocument();
		assertEquals(htm,htmlTestWithButton);
	}

	@Test
	void testChangeActiveHTMLDocument() {
		assertFalse(htmlTestWithButton.isActive());
		HTMLDocument htm = htmlTestWithButton.changeActiveHTMLDocument(10, 10);
		assertEquals(htm,htmlTestWithButton);
		assertTrue(htmlTestWithButton.isActive());
	}

	@Test
	void testResetActiveHTMLDocument() {
		assertFalse(htmlTestWithButton.isActive());
		htmlTestWithButton.setActive(true);
		assertTrue(htmlTestWithButton.isActive());
	}

	@Test
	void testSplitActiveHTMLDocumentHorizontal() {
		Pane split = htmlTestWithButton.splitActiveHTMLDocumentHorizontal();
		assertTrue(split instanceof SplitHTMLDocument);
		SplitHTMLDocument split2 = (SplitHTMLDocument) split;
		assertEquals(split2.getActiveHTMLDocument(),htmlTestWithButton);
		assertEquals(split2.getDir(),Direction.HORIZONTAL);
	}

	@Test
	void testSplitActiveHTMLDocumentVertical() {
		Pane split = htmlTestWithButton.splitActiveHTMLDocumentHorizontal();
		assertTrue(split instanceof SplitHTMLDocument);
		SplitHTMLDocument split2 = (SplitHTMLDocument) split;
		assertEquals(split2.getActiveHTMLDocument(),htmlTestWithButton);
		assertEquals(split2.getDir(),Direction.VERTICAL);
	}

	@Test
	void testDeleteActiveHTMLDocument() {
		assertNull(htmlTestWithButton.deleteActiveHTMLDocument());
	}

	@Test
	void testUpdateRightClosestChildWidth() {
		htmlTestWithButton.updateRightClosestChildWidth(10, 50);
		assertEquals(htmlTestWithButton.getWidth(), 50);
		assertEquals(htmlTestWithButton.getX(), 10);
	}

	@Test
	void testUpdateLeftClosestChildWidth() {
		htmlTestWithButton.updateLeftClosestChildWidth(10, 50);
		assertEquals(htmlTestWithButton.getWidth(), 50);
		assertEquals(htmlTestWithButton.getX(), 10);
	}

	@Test
	void testUpdateRightClosestChildHeight() {
		htmlTestWithButton.updateRightClosestChildHeight(10, 50);
		assertEquals(htmlTestWithButton.getHeight(), 50);
		assertEquals(htmlTestWithButton.getY(), 10);
	}

	@Test
	void testUpdateLeftClosestChildHeight() {
		htmlTestWithButton.updateLeftClosestChildHeight(10, 50);
		assertEquals(htmlTestWithButton.getHeight(), 50);
		assertEquals(htmlTestWithButton.getY(), 10);
	}

	@Test
	void testHTMLDocument() {
		
	}

	@Test
	void testCopy() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadPage() {
		fail("Not yet implemented");
	}

	@Test
	void testLoadHTML() {
		fail("Not yet implemented");
	}

	@Test
	void testAddReloadListener() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveReloadListener() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUrl() {
		fail("Not yet implemented");
	}

	@Test
	void testSetUrl() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHTMLCode() {
		fail("Not yet implemented");
	}

	@Test
	void testSetHTMLCode() {
		fail("Not yet implemented");
	}

}
