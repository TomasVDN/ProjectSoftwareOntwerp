package facades;

import java.util.ArrayList;

import htmlElement.ContentSpan;

/**
 * Controller type class. Used to connect DomainFacade (facade for the domain) and WindowManager (facade for the UI).
 *
 */
public class Browsr {
	
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
	public void runUrl(String path) {
		ArrayList<ContentSpan> htmlList = domainFacade.runUrl(path);
		this.getWindowManager().updateURL(path);
		this.draw(htmlList);	
	}
	
	/**
	 * Asks this.windowManager to process and display the given list of HTMLElements.
	 * @param htmlElements - list to transmit.
	 */
	public void draw(ArrayList<ContentSpan> htmlElements) {
		windowManager.draw(htmlElements);
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
	public void runUrlAttribute(String URLAttribute) {
		runUrl(getBaseURLFromSearchBar() + URLAttribute);
	}
	
	/**
	 * Asks the domainFacade to save the last loaded HTML code.
	 * @param filename - file name under wich to save the HTML code.
	 */
	public void savePage(String filename) {
		domainFacade.savePage(filename);
	}
	
	/**
	 * Asks windowManager to add a bookmark with the given name and url
	 * @param bookmarkHyperlinkName - name to be displayed of the bookmark
	 * @param bookmarkHyperlinkUrl - corresponding url
	 */
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		this.windowManager.addBookmark(bookmarkHyperlinkName, bookmarkHyperlinkUrl);
	}

	/**
	 * Changes the dialog to the given dialog.
	 * @param type
	 */
	public void changeActiveDialog(String type) {
		this.windowManager.setActiveDialog(type);
	}
}
