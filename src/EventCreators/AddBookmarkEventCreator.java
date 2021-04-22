package EventCreators;

import EventListeners.AddBookmarkListener;

/**
 * Interface that implements the addListener and removeListener for the AddBookmarkListener class.
 */
public interface AddBookmarkEventCreator extends EventCreator {

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addAddBookmarkListener(AddBookmarkListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public void removeAddBookmarkListener(AddBookmarkListener listener) ;
}
