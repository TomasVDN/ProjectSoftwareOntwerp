package EventListeners;

/**
 * Interface that implements the handleHyperLinkClicked and handleBookmarkHyperLinkClicked method for the EventListener class.
 */
public interface HyperLinkListener extends EventListener {

	void handleHyperLinkClicked(String urlAttribute);
	
	void handleBookmarkHyperLinkClicked(String url);
}
