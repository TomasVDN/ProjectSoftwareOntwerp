package EventCreators;

import EventListeners.SavePageListener;

public interface SavePageEventCreator {

	public void addSavePageListener(SavePageListener listener);

	public  void removeSavePageListener(SavePageListener listener) ;

	
}
