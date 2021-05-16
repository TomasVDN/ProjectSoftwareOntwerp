package EventListeners;

/**
 * Interface that implements the changeDialog method for the EventListener class.
 */
public interface ChangeDialogListener extends EventListener {
	
	/**
	 * Notifies the listener that the user want to change its dialog
	 * @param type - the type where the user want to switch to
	 */
	void changeDialog(String type);
	
}
