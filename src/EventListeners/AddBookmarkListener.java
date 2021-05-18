package EventListeners;

/**
 * Interface that implements the addBookmark method for the EventListener class.
 */
public interface AddBookmarkListener extends EventListener {
	
	/**
	 * Function that notifies the listener that the user want to create an bookmark
	 * @param name - The name of the bookmark
	 * @param url - The URL where the bookmark refers to
	 */
	void addBookmark(String name, String url);
}
