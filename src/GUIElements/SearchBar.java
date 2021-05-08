package GUIElements;

import java.util.ArrayList;
import java.util.List;

import EventListeners.SearchBarListener;

public class SearchBar extends TextBox {

	private List<SearchBarListener > listeners = new ArrayList<SearchBarListener>();
	
	/**
	 * Constructor of the SearchBar class
	 * @param x - x coordinate of the SearchBar
	 * @param y - y coordinate of the SearchBar
	 * @param w - width of the SearchBar
	 * @param h - height of the SearchBar
	 * @param eventReader - the eventReader used for the SearchBarListener
	 */
	public SearchBar(int x, int y, int w, int h, SearchBarListener eventReader) {
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
			listener.runUrl(this.getText());
		}
	}
	
	/**
	 * Returns the base url (url with the domain only, without resource).
	 * @return base URL
	 */
	public String getBaseURL() {	
		String URL = this.getText();
		int index = URL.lastIndexOf("/");
		//checks if two slashes used or no slashes are found at all, then don't remove anything
		if(index < 0 || URL.charAt(index-1) == '/') {
			return URL + "/"; //adds slash at the end of the string
		}
		return URL.substring(0, index+1);
	}

	public void addSearchBarListener(SearchBarListener listener) {
		if(listener!=null) {
			this.listeners.add(listener);
		}
	}

	public void removeSearchBarListener(SearchBarListener listener) {
		this.listeners.remove(listener);
	}

	public List<SearchBarListener> getListeners() {
		return listeners;
	}

	
}
