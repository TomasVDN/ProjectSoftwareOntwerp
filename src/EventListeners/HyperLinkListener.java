package EventListeners;

/**
 * Interface that implements the handleHyperLinkClicked and handleBookmarkHyperLinkClicked method for the EventListener class.
 */
public interface HyperLinkListener extends EventListener {

	/**
	 * Notifies the listener that he wants to change the attribute of the url
	 * @param urlAttribute -  the new url attribute
	 */
	void runUrlAttribute(String urlAttribute);
	
	/**
	 * 
	 * Notifies the listener that the child wants to run an URL
	 * @param url - the url given
	 */
	void runUrl(String url);
}
