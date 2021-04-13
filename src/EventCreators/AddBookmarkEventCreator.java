package EventCreators;

import EventListeners.AddBookmarkListener;

public interface AddBookmarkEventCreator extends EventCreator {

	public void addAddBookmarkListener(AddBookmarkListener listener);

	public  void removeAddBookmarkListener(AddBookmarkListener listener) ;
}
