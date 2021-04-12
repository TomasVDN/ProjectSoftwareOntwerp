package facades;

import java.util.ArrayList;

import GUIElements.Container;
import GUIElements.MainDialog;
import GUIElements.SaveDialog;
import htmlElement.ContentSpan;

/**
 * Controller type class. Used to connect DomainFacade (facade for the domain) and WindowManager (facade for the UI).
 *
 */
public class Browsr {
	
	private DomainFacade domainFacade;
	private WindowManager windowManager;
	
	private MainDialog mainDialog;
	private Container saveDialog;
	private Container bookmarkDialog;
	
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
		ArrayList<ContentSpan> htmlList=domainFacade.runUrl(path);
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
	
	public void savePage(String filename) {
		domainFacade.savePage(filename);
	}

	/**
	 * @return the bookmarkDialog
	 */
	public Container getBookmarkDialog() {
		return bookmarkDialog;
	}

	/**
	 * @return the saveDialog
	 */
	public Container getSaveDialog() {
		return saveDialog;
	}

	/**
	 * @return the mainDialog
	 */
	public MainDialog getMainDialog() {
		return mainDialog;
	}

	public void setDialogs(MainDialog mainDialog, SaveDialog saveDialog, Container bookmarkDialog) {
		this.mainDialog = mainDialog;
		this.saveDialog = saveDialog;
		this.bookmarkDialog = bookmarkDialog;
	}
	
	public void changeActiveDialog(Container dialog) {
		this.windowManager.setActiveDialog(dialog);
	}
}
