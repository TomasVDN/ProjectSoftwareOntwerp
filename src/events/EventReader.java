package events;

import EventListeners.EventListener;
import EventListeners.FormListener;
import EventListeners.HyperLinkListener;
import EventListeners.SearchBarListener;
import facades.Browsr;

/**
 * Class that can be called by given GUIElements to transmit an event to the controller (browsr).
 */
public final class EventReader implements SearchBarListener, HyperLinkListener,FormListener {
	
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
		browsr.runUrlAttribute(urlAttribute);
	}

	@Override
	public void searchBarLoseFocus(String url) {
		browsr.runUrl(url);
	}


	@Override
	public void handleFormSubmit(String action) {
		browsr.runUrlAttribute(action);
	}
	
	@Override
	public void handleBookmarkHyperLinkClicked(String url) {
		browsr.runUrl(url);
	}
	
}
