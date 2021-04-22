package EventCreators;

import EventListeners.ChangeDialogListener;

/**
 * Interface that implements the addListener and removeListener for the ChangeDialogListener class.
 */
public interface ChangeDialogEventCreator extends EventCreator {

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addChangeDialogListener(ChangeDialogListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public void removeChangeDialogListener(ChangeDialogListener listener);
}
