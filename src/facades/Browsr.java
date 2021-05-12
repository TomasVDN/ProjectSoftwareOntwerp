package facades;

import java.util.ArrayList;

import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;
import EventListeners.FormListener;
import EventListeners.HyperLinkListener;
import EventListeners.SavePageListener;
import EventListeners.SearchBarListener;
import htmlElement.ContentSpan;

/**
 * Controller type class. Used to connect DomainFacade (facade for the domain) and WindowManager (facade for the UI).
 *
 */
public class Browsr implements SearchBarListener, HyperLinkListener, FormListener, AddBookmarkListener, ChangeDialogListener, SavePageListener{
	
	private DomainFacade domainFacade;
	private WindowManager windowManager;

	/**
	 * Constructor of the Browsr class. Makes a new DomainFacade.
	 * @param windowManager - the corresponding windowManager.
	 */
	public Browsr(WindowManager windowManager) {
		domainFacade = new DomainFacade();
		this.windowManager = windowManager;
	}

	/**
	 * @return the domainFacade
	 */
	public DomainFacade getDomainFacade() {
		return domainFacade;
	}

	/**
	 * @return the windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}

	/**
	 * Used to process a URL. Calls the runUrl method in this.domainFacade, updates the url displayed in this.windowManager and calls the draw Method.
	 * @param path - the URL to process.
	 */
	@Override
	public void runUrl(String path) {
		ArrayList<ContentSpan> htmlList = domainFacade.runUrl(path);
		this.getWindowManager().updateURL(path);
		windowManager.draw(htmlList);	
	}
	
	/**
	 * Asks the windowManager to return the baseUrl (domain without resource).
	 * @return this.windowManager.getBaseURLFromSearchBar
	 */
	public String getBaseURLFromSearchBar() {
		return this.getWindowManager().getBaseURLFromSearchBar();
	}
	
	/**
	 * Handler for the hyperlinks.
	 * @param URLAttribute
	 */
	@Override
	public void runUrlAttribute(String URLAttribute) {
		runUrl(getBaseURLFromSearchBar() + URLAttribute);
	}
	
	/**
	 * Asks the domainFacade to save the last loaded HTML code.
	 * @param filename - file name under wich to save the HTML code.
	 */
	@Override
	public void savePage(String filename) {
		domainFacade.savePage(filename);
	}
	
	/**
	 * Asks windowManager to add a bookmark with the given name and url
	 * @param bookmarkHyperlinkName - name to be displayed of the bookmark
	 * @param bookmarkHyperlinkUrl - corresponding url
	 */
	@Override
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		this.windowManager.addBookmark(bookmarkHyperlinkName, bookmarkHyperlinkUrl);
	}

	/**
	 * Changes the dialog to the given dialog.
	 * @param type
	 */
	@Override
	public void changeDialog(String type) {
		this.windowManager.setActiveDialog(type);
	}
}

