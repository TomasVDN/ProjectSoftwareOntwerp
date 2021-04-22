package EventCreators;

import EventListeners.HyperLinkListener;

/**
 * Interface that implements the addListener and removeListener for the HyperLinkListener class.
 */
public interface HyperLinkEventCreator extends EventCreator{

	/**
	 * Adds the given listener to the listeners.
	 * @param listener
	 */
	public void addHyperLinkListener(HyperLinkListener listener);

	/**
	 * Removes the given listener from the listeners.
	 * @param listener
	 */
	public void removeHyperLinkListener(HyperLinkListener listener) ;
}
