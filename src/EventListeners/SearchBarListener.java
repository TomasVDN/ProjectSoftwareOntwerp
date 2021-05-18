package EventListeners;

/**
 * Interface that implements the searchBarLoseFocus method for the EventListener class.
 */
public interface SearchBarListener extends EventListener {

	/**
	 * Notify the listener that the given URL needs to be runned
	 * @param url - the given URL
	 */
	void runUrl(String url);
}
