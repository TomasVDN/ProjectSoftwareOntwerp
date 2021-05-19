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
		fail("Not yet implemented");
	}

	@Test
	void testSplitActiveHTMLDocumentVertical() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteActiveHTMLDocument() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateRightClosestChildWidth() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateLeftClosestChildWidth() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateRightClosestChildHeight() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateLeftClosestChildHeight() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateAllBars() {
		fail("Not yet implemented");
	}

	@Test
	void testHTMLDocument() {
		fail("Not yet implemented");
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
