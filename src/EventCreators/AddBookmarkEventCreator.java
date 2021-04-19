package EventCreators;

import EventListeners.AddBookmarkListener;

/**
 * Interface that implements the addListener and removeListener for the AddBookmarkListener class.
 */
public interface AddBookmarkEventCreator extends EventCreator {

	public void addAddBookmarkListener(AddBookmarkListener listener);

	public void removeAddBookmarkListener(AddBookmarkListener listener) ;
}
