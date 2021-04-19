package EventCreators;

import EventListeners.ActionListener;

/**
 * Interface that implements the addListener and removeListener for the ActionListener class.
 */
public interface ActionCreator extends EventCreator {
	
	public void addListener(ActionListener listener);

	public  void removeListener(ActionListener listener) ;

}
