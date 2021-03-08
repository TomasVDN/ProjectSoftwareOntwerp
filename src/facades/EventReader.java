package facades;

import events.*;

public final class EventReader {
	
	private static final EventReader INSTANCE = new EventReader();

	private EventReader() {}

	public static EventReader getInstance() {
	        return INSTANCE;
	    }
	   
	private Browsr browsr;
	
	public void readEvent(Event event){
		event.execute(this.getBrowsr());			
	}

	private Browsr getBrowsr() {
		return browsr;
	}

	//should only be called by windowManager once!!!
	public void setBrowsr(Browsr browsr) {
		if (this.browsr != null) {
			return;
		}
		this.browsr = browsr;
	}
}
