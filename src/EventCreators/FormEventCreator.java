package EventCreators;

import java.util.ArrayList;

import EventListeners.FormListener;

public interface FormEventCreator extends EventCreator{

	public void addListener(FormListener listener);

	public  void removeListener(FormListener listener) ;

	
}
