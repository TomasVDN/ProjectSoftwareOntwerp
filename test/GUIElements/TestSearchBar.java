package GUIElements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import EventListeners.SearchBarListener;

class TestSearchBar {

	public static class SearchBarListenerClass implements SearchBarListener {

		public String urlRecieved;

		@Override
		public void runUrl(String url) {
			this.urlRecieved =url;
		}

	};
	SearchBarListenerClass searchBarListener = new SearchBarListenerClass();
	SearchBar testBar = new SearchBar(0, 0, 0, 0,searchBarListener);
	
	@Test
	void testHandleUnselect() {
		testBar.replaceBox("This is a test");
		testBar.handleUnselect();
		assertEquals("This is a test", searchBarListener.urlRecieved);
		assertFalse(testBar.isActive());
	}

	@Test
	void testHandleEnter() {
		testBar.replaceBox("This is a test");
		testBar.handleEnter();
		assertEquals("This is a test", searchBarListener.urlRecieved);
		assertFalse(testBar.isActive());
	}

	@Test
	void testGetBaseURLNormal() {
		testBar.replaceBox("This is a test");
		assertEquals("This is a test/", testBar.getBaseURL());
	}
	
	@Test
	void testGetBaseURLWithSlash() {
		testBar.replaceBox("https://konikoko/");
		assertEquals("https://konikoko/", testBar.getBaseURL());
	}
	
	@Test
	void testGetBaseURLWithoutSlash() {
		testBar.replaceBox("https://konikoko");
		assertEquals("https://konikoko/", testBar.getBaseURL());
	}

	@Test
	void testAddSearchBarListenerEmpty() {
		testBar.addSearchBarListener(null);
		assertEquals(1,testBar.getListeners().size());
	}
	
	@Test
	void testAddSearchBarListener() {
		testBar.addSearchBarListener(searchBarListener);
		assertEquals(2,testBar.getListeners().size());
	}

	@Test
	void testRemoveSearchBarListener() {
		SearchBarListenerClass searchBarListener2 = new SearchBarListenerClass();
		testBar.addSearchBarListener(searchBarListener2);
		assertEquals(2,testBar.getListeners().size());
		testBar.removeSearchBarListener(searchBarListener);
		assertEquals(1,testBar.getListeners().size());
	}



}
