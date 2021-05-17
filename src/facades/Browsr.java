package facades;

import java.util.ArrayList;

import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;
import EventListeners.FormListener;
import EventListeners.HyperLinkListener;
import EventListeners.ReloadListener;
import EventListeners.SavePageListener;
import EventListeners.SearchBarListener;
import GUIElements.BookmarkHyperlink;
import GUIElements.Form;
import GUIElements.GUIElement;
import GUIElements.HTMLDocument;
import GUIElements.Hyperlink;
import GUIElements.Text;
import converter.HTMLToGUI;
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
		String htmlString = new InputReader().readFile(path);		

		ArrayList<GUIElement> list = decodeToGUIElements(htmlString);
		
		this.getWindowManager().updateURL(path);
		windowManager.loadHTML(list, path, htmlString, this);	
	}
	
	@Override
	public void loadHTML(HTMLDocument HTMLDocument, String url, String htmlString) {
		ArrayList<GUIElement> list = decodeToGUIElements(htmlString);

		this.getWindowManager().loadHTMLToGivenHTMLDocument(HTMLDocument, list, url, htmlString, this);
	}
	
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
	 * @param list
	 */
	private void addListenersToGUIElements(ArrayList<GUIElement> list) {
		ArrayList<Hyperlink> hyperlinkArray = new ArrayList<>();
		ArrayList<Form> formArray = new ArrayList<>();
		for (GUIElement element: list) {
			element.getGuiClass(Hyperlink.class, hyperlinkArray);
			element.getGuiClass(Form.class, formArray);
		}
		
		for(Hyperlink hyperlink : hyperlinkArray) {
			hyperlink.addHyperLinkListener(this);
		}

		for (Form form: formArray) {
			form.addFormListener(this);
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
		new Saver().saveToFile(filename, htmlCode);
	}
	
	/**
	 * Asks windowManager to add a bookmark with the given name and url
	 * @param bookmarkHyperlinkName - name to be displayed of the bookmark
	 * @param bookmarkHyperlinkUrl - corresponding url
	 */
	@Override
	public void addBookmark(String bookmarkHyperlinkName, String bookmarkHyperlinkUrl) {
		Text bookmarkHyperlinkNameText = new Text(0, 0, bookmarkHyperlinkName);
		BookmarkHyperlink newBookmarkHyperlink = new BookmarkHyperlink(0, 0, bookmarkHyperlinkNameText, bookmarkHyperlinkUrl);
		newBookmarkHyperlink.addHyperLinkListener(this);
		this.windowManager.addBookmark(newBookmarkHyperlink);
	}

	/**
	 * Changes the dialog to the given dialog.
	 * @param type
	 */
	@Override
	public void changeDialog(String type) {
		this.windowManager.setActiveDialog(type, this);
	}
}

