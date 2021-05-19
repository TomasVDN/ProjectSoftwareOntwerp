package facades;

import java.util.ArrayList;

import EventListeners.AddBookmarkListener;
import EventListeners.FormListener;
import EventListeners.HyperLinkListener;
import EventListeners.ReloadListener;
import EventListeners.SavePageListener;
import EventListeners.SearchBarListener;
import GUIElements.Form;
import GUIElements.GUIElement;
import GUIElements.HTMLDocument;
import GUIElements.Hyperlink;
import domain.HTMLDecoder;
import domain.HTMLToGUI;
import domain.InputReader;
import domain.Saver;
import htmlElement.ContentSpan;

/**
 * Controller type class. Used to receive Events and handle them.
 */
//TODO rm listener -> functional interfaces
public class BrowsrController implements ReloadListener, SearchBarListener, HyperLinkListener, FormListener, AddBookmarkListener, SavePageListener{
	
	private WindowManager windowManager;

	/**
	 * Constructor of the BrowsrController class.
	 * @param windowManager - the corresponding windowManager.
	 */
	public BrowsrController(WindowManager windowManager) {
		this.windowManager = windowManager;
	}

	/**
	 * @return the windowManager
	 */
	public WindowManager getWindowManager() {
		return windowManager;
	}

	/**
	 * Used to process an URL. Reads in the HTML code from the given URL, converts it to GUIElements and updates
	 * the displayed URL in the searchbar.
	 * It then transmits the GUIElements to the windowManager to load it.
	 * @param path - the URL to process.
	 */
	@Override
	public void runUrl(String path) {
		String htmlString = new InputReader().readFile(path);		

		ArrayList<GUIElement> list = decodeToGUIElements(htmlString);
		
		this.getWindowManager().updateURL(path);
		windowManager.loadHTML(list, path, htmlString);	
	}
	
	/**
	 * Converts the given HTML code to GUIElements, and then loads them to the given HTMLDocument.
	 * @param HTMLDocument - the HTMLDocument in which the GUIElements have to be loaded.
	 * @param url - the URL to transmit to the given HTMLDocument.
	 * @param htmlString - HTML code to transform to GUIElements.
	 */
	@Override
	public void loadHTML(HTMLDocument HTMLDocument, String url, String htmlString) {
		ArrayList<GUIElement> list = decodeToGUIElements(htmlString);

		HTMLDocument.loadHTML(list, url, htmlString);
	}
	
	/**
	 * Transforms the given HTML code to GUIElements.
	 * @param code - the HTML code to transmit.
	 * @return  ArrayList<GUIElement> - list with the GUIElements.
	 */
	private ArrayList<GUIElement> decodeToGUIElements(String code) {
		HTMLDecoder decoder = new HTMLDecoder(code);
		ArrayList<ContentSpan> htmlElements = decoder.createElements();
		
		HTMLToGUI converter = new HTMLToGUI();
		ArrayList<GUIElement> list = converter.transformToGUI(0, 0, this.windowManager.getWidth(), this.windowManager.getHeight(), htmlElements);
		
		addListenersToGUIElements(list);
		
		return list;
	}
	
	/**
	 * Adds a listener of a given class to all hyperlinks and forms
	 * @param list - list of GUIElements on which the listeners must be appended
	 */
	private void addListenersToGUIElements(ArrayList<GUIElement> list) {
		ArrayList<Hyperlink> hyperlinkArray = new ArrayList<>();
		ArrayList<Form> formArray = new ArrayList<>();
		for (GUIElement element: list) {
			element.getGuiClass(Hyperlink.class, hyperlinkArray);
			element.getGuiClass(Form.class, formArray);
		}
		
		for(Hyperlink hyperlink : hyperlinkArray) {
			hyperlink.addHyperLinkListener(this); //TODO
		}

		for (Form form: formArray) {
			form.addFormListener(this); //TODO
		}
	}
	
	/**
	 * Asks the windowManager to return the baseUrl (domain without resource).
	 * @return this.windowManager.getBaseURLFromSearchBar
	 */
	public String getBaseURLFromSearchBar() {
		return this.getWindowManager().getBaseURLFromSearchBar();
	}
	
	/**
	 * Handler for the hyperlink.
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
		new Saver().saveToFile(filename, htmlCode);
	}
	
	/**
	 * Asks windowManager to add a bookmark with the given name and URL
	 * @param bookmarkHyperlinkName - name to be displayed of the bookmark
	 * @param bookmarkHyperlinkUrl - corresponding URL
	 */
	@Override
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		this.windowManager.addBookmark(bookmarkHyperlinkName, bookmarkHyperlinkUrl);
	}
}

