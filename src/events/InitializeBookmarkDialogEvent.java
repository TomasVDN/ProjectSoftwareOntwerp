package events;

import facades.Browsr;

public class InitializeBookmarkDialogEvent implements Event{
	private String suggestedUrl;
	
	public InitializeBookmarkDialogEvent(String suggestedUrl) {
		this.suggestedUrl = suggestedUrl;
	}

	@Override
	public void execute(Browsr browsr) {
		browsr.initializeBookmarkDialog(suggestedUrl);
	}
}
