package EventCreators;

import EventListeners.FormListener;

/**
 * Interface that implements the addListener and removeListener for the FormListener class.
 */
public interface FormEventCreator extends EventCreator{

	public void addFormListener(FormListener listener);

	public void removeFormListener(FormListener listener) ;

	
}
