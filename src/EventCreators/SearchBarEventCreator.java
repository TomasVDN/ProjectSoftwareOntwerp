package EventCreators;

import EventListeners.SearchBarListener;

public interface SearchBarEventCreator extends EventCreator {

	public void addSearchBarListener(SearchBarListener listener);

	public  void removeSearchBarListener(SearchBarListener listener);
}
