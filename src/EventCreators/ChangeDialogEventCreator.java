package EventCreators;

import EventListeners.ChangeDialogListener;

/**
 * Interface that implements the addListener and removeListener for the ChangeDialogListener class.
 */
public interface ChangeDialogEventCreator extends EventCreator {
	
	public void addChangeDialogListener(ChangeDialogListener listener);

	public void removeChangeDialogListener(ChangeDialogListener listener);
}
