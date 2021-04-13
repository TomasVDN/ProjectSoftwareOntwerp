package EventCreators;

import EventListeners.ChangeDialogListener;

public interface ChangeDialogEventCreator extends EventCreator {
	
	public void addChangeDialogListener(ChangeDialogListener listener);

	public  void removeChangeDialogListener(ChangeDialogListener listener);
}
