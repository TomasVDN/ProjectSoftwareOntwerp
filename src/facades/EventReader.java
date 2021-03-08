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
		if(event instanceof RunUrlEvent) { //TODO instanceOf is blijkbaar bad practice
			RunUrlEvent urlEvent= (RunUrlEvent) event;
			this.getBrowsr().runUrl(urlEvent.getUrl());
		}			
	}

	private Browsr getBrowsr() {
		return browsr;
	}

	//should only be called by windowManager once!!!
	public void setBrowsr(Browsr browsr) {
		this.browsr = browsr;
	}
}
