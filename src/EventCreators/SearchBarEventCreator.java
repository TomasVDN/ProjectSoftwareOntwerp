package EventCreators;

import EventListeners.SearchBarListener;

/**
 * Interface that implements the addListener and removeListener for the SearchBarListener class.
 */
public interface SearchBarEventCreator extends EventCreator {

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addSearchBarListener(SearchBarListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public  void removeSearchBarListener(SearchBarListener listener);
}
