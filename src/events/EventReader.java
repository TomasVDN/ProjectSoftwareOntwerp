package events;

import facades.Browsr;

/**
 * Class that can be called by given GUIElements to transmit an event to the controller (browsr).
 */
public final class EventReader  implements EventListener {
	
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
}
