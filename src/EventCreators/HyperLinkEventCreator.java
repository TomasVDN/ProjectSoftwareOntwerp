package EventCreators;

import EventListeners.HyperLinkListener;

/**
 * Interface that implements the addListener and removeListener for the HyperLinkListener class.
 */
public interface HyperLinkEventCreator extends EventCreator{

	public void addHyperLinkListener(HyperLinkListener listener);

	public void removeHyperLinkListener(HyperLinkListener listener) ;

	
}
