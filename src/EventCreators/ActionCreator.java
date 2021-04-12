package EventCreators;

import EventListeners.ActionListener;

public interface ActionCreator extends EventCreator{
	
	public void addListener(ActionListener listener);

	public  void removeListener(ActionListener listener) ;


}
