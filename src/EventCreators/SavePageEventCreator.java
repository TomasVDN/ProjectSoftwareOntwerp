package EventCreators;

import EventListeners.SavePageListener;

/**
 * Interface that implements the addListener and removeListener for the SavePageListener class.
 */
public interface SavePageEventCreator {

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addSavePageListener(SavePageListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public void removeSavePageListener(SavePageListener listener) ;
}
