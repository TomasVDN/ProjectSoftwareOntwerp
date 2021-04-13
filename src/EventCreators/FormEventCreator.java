package EventCreators;

import EventListeners.FormListener;

public interface FormEventCreator extends EventCreator{

	public void addFormListener(FormListener listener);

	public void removeFormListener(FormListener listener) ;

	
}
