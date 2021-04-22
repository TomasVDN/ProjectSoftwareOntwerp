package EventCreators;

import EventListeners.ActionListener;

/**
 * Interface that implements the addListener and removeListener for the ActionListener class.
 */
public interface ActionCreator extends EventCreator {

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addListener(ActionListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public void removeListener(ActionListener listener) ;

}
