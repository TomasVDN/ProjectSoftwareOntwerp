package EventListeners;

/**
 * Interface to warn listener that the sub class wants to change the searchbar
 */
public interface ChangeSearchBarURLListener extends EventListener {

	/**
	 * Notifies the listener that the searchbar needs to be changed to the given url
	 * @param url - the url where the searchbar needs to change to.
	 */
	void changeSearchBarURL(String url);
	
}
