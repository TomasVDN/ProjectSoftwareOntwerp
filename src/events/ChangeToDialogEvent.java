package events;

import GUIElements.Container;
import facades.Browsr;

public class ChangeToDialogEvent implements Event {

	String type;
	
	//TODO: keep this, or make 3 classes?
	public ChangeToDialogEvent(String type) {
		this.type = type;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.changeActiveDialog(this.type);
	}
}
