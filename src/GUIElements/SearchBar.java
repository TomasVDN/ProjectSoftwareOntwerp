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
		this.addSearchBarListener(eventReader);
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
		int index = URL.lastIndexOf("/");
		if (URL.length() - 1 == index)
			return URL;
		else
			return URL + "/";
	}

	@Override
	public void addSearchBarListener(SearchBarListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeSearchBarListener(SearchBarListener listener) {
		this.listeners.remove(listener);
	}

	public List<SearchBarListener> getListeners() {
		return listeners;
	}

	
}
