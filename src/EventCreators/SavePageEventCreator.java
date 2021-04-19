package EventCreators;

import EventListeners.SavePageListener;

/**
 * Interface that implements the addListener and removeListener for the SavePageListener class.
 */
public interface SavePageEventCreator {

	public void addSavePageListener(SavePageListener listener);

	public void removeSavePageListener(SavePageListener listener) ;

	
}
