package EventListeners;

/**
 * Interface that implements the addBookmark method for the EventListener class.
 */
public interface AddBookmarkListener extends EventListener {
	
	void addBookmark(String name, String url);
}
