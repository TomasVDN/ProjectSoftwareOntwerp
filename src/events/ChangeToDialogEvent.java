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
		Container dialog = null;
		
		switch (this.type) {
		case "mainDialog":
			dialog = browsr.getMainDialog();
			break;
		case "saveDialog":
			dialog = browsr.getSaveDialog();
			break;
		case "bookmarkDialog":
			dialog = browsr.getBookmarkDialog();
			break;
		default:
			break;
		}
		
		browsr.changeActiveDialog(dialog);
	}
}
