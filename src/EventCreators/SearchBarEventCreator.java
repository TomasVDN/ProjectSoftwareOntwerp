package EventCreators;

import EventListeners.HyperLinkListener;
import EventListeners.SearchBarListener;

public interface SearchBarEventCreator extends EventCreator {

	public void addListener(SearchBarListener listener);

	public  void removeListener(SearchBarListener listener) ;

	
}
