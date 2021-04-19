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
 * It implements the SearchBarListener, HyperLinkListener, FormListener, AddBookmarkListener, ChangeDialogListener and SavePageListener.
 */
public final class EventReader implements SearchBarListener, HyperLinkListener, FormListener, AddBookmarkListener, ChangeDialogListener, SavePageListener {
	
	private Browsr browsr;
	
	/**
	 * Sets this.browsr to the given value.
	 * @param browsr - the new value of this.browsr
	 */
	public EventReader (Browsr browsr) {
		this.browsr = browsr;
	}

	/**
	 * Runs the runUrlAttribute in this.browsr with the given UrlAttribute.
	 * @param urlAttribute - String with the urlAttribute to transmit
	 */
	@Override
	public void handleHyperLinkClicked(String urlAttribute) {
		browsr.runUrlAttribute(urlAttribute);
	}

	/**
	 * Runs the runUrl in this.browsr with the given url.
	 * @param url - String with the url to transmit
	 */
	@Override
	public void searchBarLoseFocus(String url) {
		browsr.runUrl(url);
	}

	/**
	 * Runs the runUrlAttribute in this.browsr with the given UrlAttribute.
	 * @param action - String with the urlAttribute to transmit
	 */
	@Override
	public void handleFormSubmit(String action) {
		browsr.runUrlAttribute(action);
	}
	
	/**
	 * Runs the runUrl in this.browsr with the given url.
	 * @param url - String with the url to transmit
	 */
	@Override
	public void handleBookmarkHyperLinkClicked(String url) {
		browsr.runUrl(url);
	}

	/**
	 * Runs the addBookmark in this.browsr with the given name and url.
	 * @param name - String with the name to transmit
	 * @param url - String with the url to transmit
	 */
	@Override
	public void addBookmark(String name, String url) {
		browsr.addBookmark(name, url);
	}

	/**
	 * Runs the changeActiveDialog in this.browsr with the given dialog.
	 * @param type - String with the dialog to transmit
	 */
	@Override
	public void changeDialog(String type) {
		browsr.changeActiveDialog(type);
	}

	/**
	 * Runs the savePage in this.browsr with the given filename.
	 * @param filename - String with the filename to transmit
	 */
	@Override
	public void savePage(String filename) {
		browsr.savePage(filename);
	}	
}
