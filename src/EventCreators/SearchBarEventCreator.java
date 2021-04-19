package EventCreators;

import EventListeners.SearchBarListener;

/**
 * Interface that implements the addListener and removeListener for the SearchBarListener class.
 */
public interface SearchBarEventCreator extends EventCreator {

	public void addSearchBarListener(SearchBarListener listener);

	public  void removeSearchBarListener(SearchBarListener listener);
}
