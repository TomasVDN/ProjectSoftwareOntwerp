package facades;

import java.util.ArrayList;

import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;
import EventListeners.FormListener;
import EventListeners.HyperLinkListener;
import EventListeners.ReloadListener;
import EventListeners.SavePageListener;
import EventListeners.SearchBarListener;
import GUIElements.HTMLDocument;
import domain.HTMLDecoder;
import domain.InputReader;
import domain.Saver;
import htmlElement.ContentSpan;

/**
 * Controller type class. Used to connect the domain and WindowManager (facade for the UI).
 *
 */
//TODO rm listener -> functional interfaces
public class Browsr implements ReloadListener, SearchBarListener, HyperLinkListener, FormListener, AddBookmarkListener, ChangeDialogListener, SavePageListener{
	
	private WindowManager windowManager;

	/**
	 * Constructor of the Browsr class.
	 * @param windowManager - the corresponding windowManager.
	 */
	public Browsr(WindowManager windowManager) {
		this.windowManager = windowManager;
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
		String code = new InputReader().readFile(path);		

		HTMLDecoder decoder = new HTMLDecoder(code);
		ArrayList<ContentSpan> htmlList = decoder.createElements();
		
		this.getWindowManager().updateURL(path);
		windowManager.draw(htmlList, path, code,this);	
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
	 * Retrieves the HTML code from the active HTMLDocument, and saves it under the given name. //TODO update
	 * @param filename - file name under wich to save the HTML code.
	 */
	@Override
	public void savePage(String filename,String htmlCode) {
		//String code = this.windowManager.getHTMLCodeFromActiveHTMLDocument();
		new Saver().saveToFile(filename, htmlCode);
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
		this.windowManager.setActiveDialog(type,this);
	}

	@Override
	public void draw(HTMLDocument HTMLDocument, String url, String htmlString) {
		HTMLDecoder decoder = new HTMLDecoder(htmlString);
		ArrayList<ContentSpan> htmlList = decoder.createElements();
		this.getWindowManager().redraw(HTMLDocument, htmlList, url, htmlString, this);
	}
}

