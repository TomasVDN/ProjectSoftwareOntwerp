package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSplitHTMLDocument {

	HTMLDocument html1 = new HTMLDocument(0, 0, 100, 100, "url", "htmlCode");
	HTMLDocument html2 = new HTMLDocument(0, 0, 100, 100, "url", "htmlCode");
	HTMLDocument html3 = new HTMLDocument(0, 0, 100, 100, "url", "htmlCode");
	SplitHTMLDocument splitHtml1 = new SplitHTMLDocument(html1, Direction.VERTICAL);
	SplitHTMLDocument splitHtml2 = new SplitHTMLDocument(html2, Direction.HORIZONTAL);
	SplitHTMLDocument bigSplitV2;
	SplitHTMLDocument bigSplitH;
	
	@BeforeEach
	void init() {
		html1.setActive(true);
		html2.setActive(true);
		html3.setActive(true);
		SplitHTMLDocument splitHtml3 = new SplitHTMLDocument(html3, Direction.VERTICAL);
		SplitHTMLDocument splitHtml4 = new SplitHTMLDocument(html3.copy(), Direction.VERTICAL);
		bigSplitV2 = (SplitHTMLDocument) splitHtml3.splitActiveHTMLDocumentVertical();
		bigSplitH = (SplitHTMLDocument) splitHtml4.splitActiveHTMLDocumentHorizontal();
	}
	
	@Test
	void testGetActiveHTMLDocument() {
		assertEquals(html1,splitHtml1.getActiveHTMLDocument());
		splitHtml1.changeActiveHTMLDocument(75, 10);
		assertEquals(splitHtml1.getRightPanel(),splitHtml1.getActiveHTMLDocument());
	}


	@Test
	void testResetActiveHTMLDocument() {
		splitHtml1.resetActiveHTMLDocument();
		assertFalse(splitHtml1.getLeftPanel().isActive());
		assertFalse(splitHtml1.getRightPanel().isActive());
	}

	@Test
	void testSplitActiveHTMLDocumentHorizontal() {
		Pane splitted =  splitHtml1.splitActiveHTMLDocumentHorizontal();
		assertTrue(splitted instanceof SplitHTMLDocument);
		SplitHTMLDocument splitted1 = (SplitHTMLDocument) splitted;
		assertTrue(splitted1.getLeftPanel() instanceof SplitHTMLDocument);
		assertTrue(splitted1.getRightPanel() instanceof HTMLDocument);
		SplitHTMLDocument splitted2 = (SplitHTMLDocument) splitted1.getLeftPanel();
		assertEquals(html1,splitted2.getLeftPanel());
	}

	@Test
	void testSplitActiveHTMLDocumentVertical() {
		Pane splitted =  splitHtml1.splitActiveHTMLDocumentVertical();
		assertTrue(splitted instanceof SplitHTMLDocument);
		SplitHTMLDocument splitted1 = (SplitHTMLDocument) splitted;
		assertTrue(splitted1.getLeftPanel() instanceof SplitHTMLDocument);
		assertTrue(splitted1.getRightPanel() instanceof HTMLDocument);
		SplitHTMLDocument splitted2 = (SplitHTMLDocument) splitted1.getLeftPanel();
		assertEquals(html1,splitted2.getLeftPanel());
	}

	@Test
	void testDeleteActiveHTMLDocument() {
		Pane right = splitHtml2.getRightPanel();
		assertEquals(right, splitHtml2.deleteActiveHTMLDocument());
	}

	@Test
	void testUpdateLeftClosestChildWidth() {
		 bigSplitV2.updateLeftClosestChildWidth(10, 80);
		 assertEquals(bigSplitV2.getX(), 10);
		 assertEquals(bigSplitV2.getWidth(), 80);
		 assertEquals(bigSplitV2.getLeftPanel().getWidth(), 50);
		 assertEquals(bigSplitV2.getRightPanel().getWidth(), 30);
		 
	}

	@Test
	void testUpdateRightClosestChildWidth() {
		 bigSplitV2.updateRightClosestChildWidth(10, 80);
		 assertEquals(bigSplitV2.getX(), 10);
		 assertEquals(bigSplitV2.getWidth(), 80);
		 assertEquals(bigSplitV2.getLeftPanel().getWidth(), 30);
		 assertEquals(bigSplitV2.getRightPanel().getWidth(), 50);
		 assertTrue(bigSplitV2.getLeftPanel() instanceof SplitHTMLDocument);
		 SplitHTMLDocument child = (SplitHTMLDocument) bigSplitV2.getLeftPanel();
		 assertEquals(child.getLeftPanel().getWidth(), 5);
		 assertEquals(child.getRightPanel().getWidth(), 25);
		 
	}

	@Test
	void testUpdateRightClosestChildHeight() {
		 bigSplitH.updateRightClosestChildHeight(10, 80);
		 assertEquals(bigSplitH.getY(), 10);
		 assertEquals(bigSplitH.getHeight(), 80);
		 assertEquals(bigSplitH.getLeftPanel().getHeight(), 80);
		 assertEquals(bigSplitH.getRightPanel().getHeight(), 80);
		 assertTrue(bigSplitH.getLeftPanel() instanceof SplitHTMLDocument);
		 SplitHTMLDocument child = (SplitHTMLDocument) bigSplitH.getLeftPanel();
		 assertEquals(child.getLeftPanel().getHeight(), 30);
		 assertEquals(child.getRightPanel().getHeight(), 50);
	}

	@Test
	void testUpdateLeftClosestChildHeight() {
		 bigSplitH.updateLeftClosestChildHeight(10, 80);
		 assertEquals(bigSplitH.getY(), 10);
		 assertEquals(bigSplitH.getHeight(), 80);
		 assertEquals(bigSplitH.getLeftPanel().getHeight(), 80);
		 assertEquals(bigSplitH.getRightPanel().getHeight(), 80);
		 assertTrue(bigSplitH.getLeftPanel() instanceof SplitHTMLDocument);
		 SplitHTMLDocument child = (SplitHTMLDocument) bigSplitH.getLeftPanel();
		 assertEquals(child.getLeftPanel().getHeight(), 50);
		 assertEquals(child.getRightPanel().getHeight(), 30);
	}


}
