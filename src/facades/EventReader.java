package facades;

import events.*;

public class EventReader {
	
	private Browsr browsr;
	
	public EventReader(Browsr browsr) {
		this.setBrowsr(browsr);
	}
	
	public void readEvent(Event event){
		if(event instanceof RunUrlEvent) { //TODO instanceOf is blijkbaar bad practice
			RunUrlEvent urlEvent= (RunUrlEvent) event;
			this.getBrowsr().runUrl(urlEvent.getUrl());
		}

				
	}
	//

	public Browsr getBrowsr() {
		return browsr;
	}

	public void setBrowsr(Browsr browsr) {
		this.browsr = browsr;
	}

}
