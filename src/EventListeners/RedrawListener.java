package EventListeners;

import GUIElements.HTMLDocument;

/**
 * Interface that implements the redraw method for the EventListener class.
 */
public interface RedrawListener extends EventListener {

	/**
	 * Redraws the HTMLDocument
	 * @param HTMLDocument - the HTMLDocument to change
	 * @param url - the url to achieve the given htmlString
	 * @param htmlString - the html elements given in a string
	 */
	void redraw(HTMLDocument HTMLDocument, String url, String htmlString);
	
}
