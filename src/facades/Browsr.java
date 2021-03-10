package facades;

import java.io.File;
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
		domainFacade = new DomainFacade(this);
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
	 * Used to process a File. Calls the openFile method in this.domainFacade, updates the url displayed in this.windowManager and calls the draw Method.
	 * @param path - the URL to process.
	 */
	public void openFile(File file) {
		ArrayList<ContentSpan> htmlList=domainFacade.openFile(file);
		this.getWindowManager().updateURL(file.toString());
		this.draw(htmlList);
	}

	/**
	 * Asks this.windowManager to process and display the given list of HTMLElements.
	 * @param htmlElements - list to transmit.
	 */
	public void draw(ArrayList<ContentSpan> htmlElements) {
		windowManager.draw(htmlElements);
	}
	
	public String getBaseURLFromSearchBar() {
		return this.getWindowManager().getBaseURLFromSearchBar();
	}
	
	public void handleHyperlink(String URLAttribute) {
		runUrl(getBaseURLFromSearchBar() + URLAttribute);
	}

}
