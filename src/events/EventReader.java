package events;

import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;
import EventListeners.FormListener;
import EventListeners.HyperLinkListener;
import EventListeners.SavePageListener;
import EventListeners.SearchBarListener;
import facades.Browsr;

/**
 * Class that can be called by given GUIElements to transmit an event to the controller (browsr).
 */
public final class EventReader implements SearchBarListener, HyperLinkListener,FormListener, AddBookmarkListener, ChangeDialogListener, SavePageListener {
	
	private Browsr browsr;
	
	public EventReader (Browsr browsr) {
		this.browsr = browsr;
	}

	@Override
	public void handleHyperLinkClicked(String urlAttribute) {
		browsr.runUrlAttribute(urlAttribute);
	}

	@Override
	public void searchBarLoseFocus(String url) {
		browsr.runUrl(url);
	}


	@Override
	public void handleFormSubmit(String action) {
		browsr.runUrlAttribute(action);
	}
	
	@Override
	public void handleBookmarkHyperLinkClicked(String url) {
		browsr.runUrl(url);
	}

	@Override
	public void addBookmark(String name, String url) {
		browsr.addBookmark(name, url);
	}

	@Override
	public void changeDialog(String type) {
		browsr.changeActiveDialog(type);
	}

	@Override
	public void savePage(String filename) {
		browsr.savePage(filename);
	}	
}
