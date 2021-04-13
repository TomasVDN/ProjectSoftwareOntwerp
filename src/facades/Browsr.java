package facades;

import java.util.ArrayList;

import GUIElements.BookmarkDialog;
import GUIElements.BookmarkHyperlink;
import GUIElements.Container;
import GUIElements.MainDialog;
import GUIElements.SaveDialog;
import GUIElements.Text;
import htmlElement.ContentSpan;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
		// transform the searchBar to percentage encoding
		try {
			String percentageEncoded = URLEncoder.encode(path,StandardCharsets.UTF_8.name());
			percentageEncoded = this.replaceUncorrectPercentages(percentageEncoded);;
			// run the url
			ArrayList<ContentSpan> htmlList=domainFacade.runUrl(percentageEncoded);
			this.getWindowManager().updateURL(percentageEncoded);
			this.draw(htmlList);	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Replaces all percentages of common characters that shouldn't be replaced with their counterpart
	 * form URLencoder
	 */
	private String replaceUncorrectPercentages(String percentageEncoded) {
		return percentageEncoded.replace("%2F", "/").replace("%3A",":" ).replace("%7E", "~").replace("%3F","?")
				.replace("%3D","=").replace("%26","&");
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
	 * Handler for the hyperlinks.
	 * @param URLAttribute
	 */
	public void handleBookmarkHyperlink(String URLAttribute) {
		runUrl(URLAttribute);
	}
	
	public void savePage(String filename) {
		domainFacade.savePage(filename);
	}
	
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		/*
		 * TODO moet die BookmarkHyperlink hier wel aangemaakt worden? Maar anders weet ik niet van waar ge die eventReader gaat halen.
		 */
		Text bookmarkHyperlinkNameText = new Text(0, 0, bookmarkHyperlinkName);
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, bookmarkHyperlinkNameText, bookmarkHyperlinkUrl, this.getWindowManager().getEventReader());
		this.getWindowManager().getMainDialog().addBookmark(newBookmarkHyperlink);
	}

	public void changeActiveDialog(String type) {
		this.windowManager.setActiveDialog(type);
	}
}
