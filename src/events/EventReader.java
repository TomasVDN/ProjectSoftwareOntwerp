package events;

import facades.Browsr;

/**
 * Singleton type class that can be called by all GUIElements to transmit an event to the controller (browsr).
 */
public final class EventReader {
	
	
	private static final EventReader INSTANCE = new EventReader();
	private Browsr browsr;
	
	private EventReader() {}

	/**
	 * Returns the instance of this object.
	 * @return
	 */
	public static EventReader getInstance() {
	        return INSTANCE;
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

	/**
	 * Method used to set the corresponding controller (browsr).
	 * @param browsr
	 */
    //should only be called by windowManager once!!! Due to Junit, protection has been removed
    public void setBrowsr(Browsr browsr) {
        if (browsr == null) {
            throw new IllegalArgumentException("Illegal browsr");
        }
        
        this.browsr = browsr;
    }

}
