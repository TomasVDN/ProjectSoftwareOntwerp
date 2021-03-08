package GUIElements;

import events.Event;
import events.RunUrlEvent;
import facades.EventReader;

public class SearchBar extends TextBox {

	public SearchBar(int x, int y, int w, int h) {
		super(x, y, w, h);
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
		EventReader e = EventReader.getInstance();
		e.readEvent(event);
	}
	
	@Override
	public void handleUnselect() {
		//unselectAllText();
		runUrlEvent();
	}

}
