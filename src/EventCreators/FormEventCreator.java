package EventCreators;

import EventListeners.FormListener;

/**
 * Interface that implements the addListener and removeListener for the FormListener class.
 */
public interface FormEventCreator extends EventCreator{

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addFormListener(FormListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public void removeFormListener(FormListener listener) ;
}
