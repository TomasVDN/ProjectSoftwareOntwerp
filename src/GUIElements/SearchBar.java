package GUIElements;

import java.util.ArrayList;
import java.util.List;

import EventCreators.SearchBarEventCreator;
import EventListeners.SearchBarListener;
import events.EventReader;


public class SearchBar extends TextBox implements SearchBarEventCreator {

	private List<SearchBarListener > listeners = new ArrayList<SearchBarListener>();
	
	/**
	 * Constructor of the SearchBar class
	 * @param x - x coordinate of the SearchBar
	 * @param y - y coordinate of the SearchBar
	 * @param w - width of the SearchBar
	 * @param h - height of the SearchBar
	 */
	public SearchBar(int x, int y, int w, int h, EventReader eventReader) {
		super(x, y, w, h);
		this.addListener(eventReader);
	}
	
	/**
	 * Overrides the handle enter method of the TextBox superclass
	 */
	@Override
	public void handleEnter() {
		runUrlEvent();
	}
	
	/**
	 * Overrides the handle unselect method of the TextBox superclass
	 */
	@Override
	public void handleUnselect() {
		runUrlEvent();
	}
	
	/**
	 * Sends a runUrlEvent to the eventReader.
	 */
	private void runUrlEvent() {
		for(SearchBarListener listener: this.getListeners()) {
			listener.searchBarLoseFocus(this.getText());
		}
	}
	
	/**
	 * Returns the base url (url with the domain only, without resource).
	 * @return base URL
	 */
	public String getBaseURL() {
		String URL = this.getText();
		int index = URL.length() - 1;
		boolean found = false;//URL.charAt(index) == '/';
		while (!found && index >= 0) {
			//index--;
			found = URL.charAt(index) == '/';
			index--;
		}
		//checks if two slashes used or no slashes are found at all, then don't remove anything
		if(index<0 || URL.charAt(index)=='/') {
			return URL + "/"; //adds slash at the end of the string
		}
		return URL.substring(0, index+2);
	}

	@Override
	public void addListener(SearchBarListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeListener(SearchBarListener listener) {
		this.listeners.remove(listener);
	}

	public List<SearchBarListener> getListeners() {
		return listeners;
	}

	
}
