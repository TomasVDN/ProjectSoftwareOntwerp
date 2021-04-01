package GUIElements;

import events.Event;
import events.EventReader;
import events.RunUrlEvent;

public class SearchBar extends TextBox {

	private EventReader eventReader;
	
	/**
	 * Constructor of the SearchBar class
	 * @param x - x coordinate of the SearchBar
	 * @param y - y coordinate of the SearchBar
	 * @param w - width of the SearchBar
	 * @param h - height of the SearchBar
	 */
	public SearchBar(int x, int y, int w, int h, EventReader eventReader) {
		super(x, y, w, h);
		this.eventReader = eventReader;
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
		Event event = new RunUrlEvent(this.getText());
		this.eventReader.readEvent(event);
	}
	
	/**
	 * Returns the base url (url with the domain only, without resource).
	 * @return base URL
	 */
	public String getBaseURL() {
		String URL = this.getText();
		int index = URL.length() - 1;
		boolean found = URL.charAt(index) == '/';
		while (!found && index >= 0) {
			index--;
			found = URL.charAt(index) == '/';
		}
		return URL.substring(0, index+1);
	}
	

}
