package GUIElements;

import java.util.ArrayList;

import events.Event;
import events.RunUrlEvent;
import facades.EventReader;

public class SearchBar extends TextBox {

	public SearchBar(int x, int y, int w, int h,EventReader eventReader) {
		super(x, y, w, h,eventReader);
	}
	
	/*
	 * Overrides the handle enter method
	 */
	@Override
	void handleEnter() {
		//this.setActive(false);
		runUrlEvent();
	}
	
	private void runUrlEvent() {
		//this.setActive(false);// gewone textbox gaat inactief worden bij enter
		Event event = new RunUrlEvent(this.getText());
		this.getEventReader().readEvent(event);
	}
	
	@Override
	public void handleUnselect() {
		//unselectAllText();
		runUrlEvent();
	}

}
