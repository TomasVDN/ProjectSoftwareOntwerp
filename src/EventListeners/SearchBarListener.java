package EventListeners;

/**
 * Interface that implements the searchBarLoseFocus method for the EventListener class.
 */
public interface SearchBarListener extends EventListener {

	/**
	 * notify the listener that the given url needs to be runned
	 * @param url - the given url
	 */
	void runUrl(String url);
}
