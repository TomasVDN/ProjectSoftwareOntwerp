package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import EventListeners.ReloadListener;
import domain.HTMLDecoder;
import htmlElement.ContentSpan;
import htmlElement.HTMLButton;

class TestHTMLDocument {

	HTMLDocument htmlTestWithButton = new HTMLDocument(0, 0, 100, 100, "button", "<input type=\"submit\">");
	
	
	public static class LoadPageListenerClass implements ReloadListener {


		public String htmlCode;
		
		public ArrayList<ContentSpan> htmlElements;

		@Override
		public void loadHTML(HTMLDocument HTMLDocument, String url, String htmlString) {
			this.htmlCode=htmlString;
			HTMLDecoder decoder = new HTMLDecoder(htmlString);
			htmlElements = decoder.createElements();
		}

	};
	
	
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
		assertEquals(split2.getDirection(),Direction.HORIZONTAL);
	}

	@Test
	void testSplitActiveHTMLDocumentVertical() {
		Pane split = htmlTestWithButton.splitActiveHTMLDocumentVertical();
		assertTrue(split instanceof SplitHTMLDocument);
		SplitHTMLDocument split2 = (SplitHTMLDocument) split;
		assertEquals(split2.getActiveHTMLDocument(),htmlTestWithButton);
		assertEquals(split2.getDirection(),Direction.VERTICAL);
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
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			HTMLDocument html = new HTMLDocument(0, 0, 0, 0, null, "");
		});
		assertTrue(exception.getMessage().contains("Url can't be null"));
		
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			HTMLDocument html = new HTMLDocument(0, 0, 0, 0, "", null);
		});
		assertTrue(exception2.getMessage().contains("Not a valid HTMLCode"));
	}

	@Test
	void testCopy() {
		HTMLDocument htmlCopy = htmlTestWithButton.copy();
		assertEquals(htmlCopy.getWidth(),htmlTestWithButton.getWidth());
		assertEquals(htmlCopy.getY(),htmlTestWithButton.getY());
		assertEquals(htmlCopy.getUrl(),htmlTestWithButton.getUrl());
		assertEquals(htmlCopy.getHTMLCode(),htmlTestWithButton.getHTMLCode());
		htmlTestWithButton.setY(20);
		assertEquals(0,htmlCopy.getY());
	}

	@Test
	void testLoadPage() {
		LoadPageListenerClass loadPage = new LoadPageListenerClass();
		htmlTestWithButton.addReloadListener(loadPage);
		htmlTestWithButton.loadPage();
		assertEquals(loadPage.htmlCode, htmlTestWithButton.getHTMLCode());
		assertTrue(loadPage.htmlElements.get(0) instanceof HTMLButton);
	}

	@Test
	void testLoadHTML() {
		Text text = new Text(0, 0, "String");
		ArrayList<GUIElement> list = new ArrayList<GUIElement>();
		list.add(text);
		htmlTestWithButton.loadHTML(list, "", "String");
		assertEquals(htmlTestWithButton.getElements().get(0),text);
	}


}
