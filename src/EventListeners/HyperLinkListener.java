package EventListeners;

/**
 * Interface that implements the run URL methods for the EventListener class.
 */
public interface HyperLinkListener extends EventListener {

	/**
	 * Notifies the listener to change the attribute of the URL
	 * @param urlAttribute -  the new URL attribute
	 */
	void runUrlAttribute(String urlAttribute);
	
	/**
	 * 
	 * Notifies the listener to run an URL
	 * @param url - the given URL
	 */
	void runUrl(String url);
}
