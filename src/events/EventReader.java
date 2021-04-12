package events;

import EventListeners.ButtonClickedListener;
import EventListeners.EventListener;
import EventListeners.HyperLinkClickedListener;
import EventListeners.SearchBarListener;
import facades.Browsr;

/**
 * Class that can be called by given GUIElements to transmit an event to the controller (browsr).
 */
public final class EventReader  implements ButtonClickedListener,SearchBarListener, HyperLinkClickedListener {
	
	private Browsr browsr;
	
	public EventReader (Browsr browsr) {
		this.browsr = browsr;
	}
	
	/**
	 * Method used to transmit an event type to the controller.
	 * @param event
	 */
	public void readEvent(Event event){
		event.execute(this.getBrowsr());			
	}

	/**
	 * @return this.browsr
	 */
	private Browsr getBrowsr() {
		return browsr;
	}

	@Override
	public void handleHyperLinkClicked(String urlAttribute) {
		browsr.handleHyperlink(urlAttribute);
	}

	@Override
	public void searchBarLoseFocus(String url) {
		browsr.runUrl(url);
	}

	@Override
	public void handleButtonClicked() {

	}
}
