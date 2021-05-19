package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;

/**
 * Dialog used to make a new bookmark. Contains two textBoxes (one to enter the bookmark display name and
 * one to enter the corresponding URL) and two buttons (one to cancel and return to the main dialog, and
 * one to finalize the creation).
 */
public class BookmarkDialog extends Dialog {
	
	private ScrollableTextBox bookmarkHyperlinkNameTextBox;
	private ScrollableTextBox bookmarkHyperlinkUrlTextBox;
	
	private ArrayList<AddBookmarkListener> addBookmarkListenerList = new ArrayList<AddBookmarkListener>();
	private ArrayList<ChangeDialogListener> changeDialogListenerList = new ArrayList<ChangeDialogListener>();
	
	/**
	 * Constructor of the BookmarkDialog class.
	 * @param x - x coordinate of this BookmarkDialog
     * @param y - y coordinate of this BookmarkDialog
     * @param w - width of this BookmarkDialog
     * @param h - height of this BookmarkDialog
	 * @param suggestedUrl - the URL to prefill the URL textBox with
	 */
	public BookmarkDialog(int x, int y, int w, int h,String suggestedUrl) {
		super(x, y, w, h);

		this.initHeader();
		this.initNameInput(w);
		this.initUrlInput(w);
		this.initCancelButton(w);
		this.initAddBookmarkButton(w);
		this.setSuggestedUrl(suggestedUrl);
	}
	
	/**
	 * Initialize the header.
	 */
	private void initHeader() {
		Text headerText = new Text(10, 0, "Create a new bookmark");
		this.addElement(headerText);
	}
	
	/**
	 * Initializes the textBox for the name input.
	 * @param width - width of the dialog
	 */
	private void initNameInput(int width) {
		Text bookmarkHyperlinkNameText = new Text(10, 40, "Name:");
		this.addElement(bookmarkHyperlinkNameText);
		bookmarkHyperlinkNameTextBox =new ScrollableTextBox(75, 40,new TextBox(0,0, width - 100, 40));
		this.setNameTextBox(bookmarkHyperlinkNameTextBox);
		this.addElement(bookmarkHyperlinkNameTextBox);
	}
	
	/**
	 * Initializes the textBox for the URL input.
	 * @param width - width of the dialog
	 */
	private void initUrlInput(int width) {
		Text bookmarkHyperlinkUrlText = new Text(10, 90, "URL:");
		this.addElement(bookmarkHyperlinkUrlText);
		bookmarkHyperlinkUrlTextBox = new ScrollableTextBox(75, 90,new TextBox(0,0, width - 100, 40));
		this.setUrlTextBox(bookmarkHyperlinkUrlTextBox);
		this.addElement(bookmarkHyperlinkUrlTextBox);
	}
	
	/**
	 * Initializes the cancel button.
	 * @param width - width of the dialog
	 */
	private void initCancelButton(int width) {
		int xPos = Math.floorDiv(width, 4);
		
		Button cancelButton = new Button(xPos, 200, new Text(0, 0, "Cancel"), true, Color.lightGray);
		
		cancelButton.addSingleClickListener(() -> {
			changeDialogListenerList.forEach(listener -> listener.changeDialog("mainDialog"));
		});
		
		this.addElement(cancelButton);
	}
	
	/**
	 * Initializes the addBookmark button.
	 * @param width - width of the dialog
	 */
	private void initAddBookmarkButton(int width) {
		int xPos = 2 * Math.floorDiv(width, 4);
		
		Button addBookmarkButton = new Button(xPos, 200, new Text(0, 0, "Add Bookmark"), true, Color.lightGray);
		addBookmarkButton.addSingleClickListener(() ->{
			String name = this.getNameTextBox().getText();
			String url = this.getUrlTextBox().getText();
			
			addBookmarkListenerList.forEach(listener -> listener.addBookmark(name, url));
			changeDialogListenerList.forEach(listener -> listener.changeDialog("mainDialog"));
		});
		
		this.addElement(addBookmarkButton);
	}

	/**
	 * @return the textBox
	 */
	public TextBox getNameTextBox() {
		return bookmarkHyperlinkNameTextBox.getTextBox();
	}

	/**
	 * @param nameTextBox the nameTextBox to set
	 */
	public void setNameTextBox(ScrollableTextBox nameTextBox) {
		this.bookmarkHyperlinkNameTextBox = nameTextBox;
	}
	
	/**
	 * @return the textBox
	 */
	public TextBox getUrlTextBox() {
		return bookmarkHyperlinkUrlTextBox.getTextBox();
	}

	/**
	 * @param urlTextBox the textBox to set
	 */
	public void setUrlTextBox(ScrollableTextBox urlTextBox) {
		this.bookmarkHyperlinkUrlTextBox = urlTextBox;
	}
	
	/**
	 * Method used to set the suggested URL when loading the bookmark dialog.
	 * @param suggestedUrl
	 */
	public void setSuggestedUrl(String suggestedUrl) {
		this.getUrlTextBox().setLeftText(suggestedUrl);
	}

	/**
	 * Add a bookmarkListener to the list of bookmarkListeners
	 * @param listener - listener to add
	 */
	public void addAddBookmarkListener(AddBookmarkListener listener) {
		addBookmarkListenerList.add(listener);
	}

	/**
	 * Remove the given bookmarkListener from the list of bookmarkListeners
	 * @param listener - listener to remove
	 */
	public void removeAddBookmarkListener(AddBookmarkListener listener) {
		addBookmarkListenerList.remove(listener);
	}
	
	/**
	 * Add a ChangeDialogListener to the list of ChangeDialogListeners
	 * @param listener - listener to add
	 */
	public void addChangeDialogListener(ChangeDialogListener listener) {
		changeDialogListenerList.add(listener);
	}
	
	/**
	 * Remove the given ChangeDialogListener from the list of ChangeDialogListeners
	 * @param listener - listener to remove
	 */
	public void removeChangeDialogListener(ChangeDialogListener listener) {
		changeDialogListenerList.remove(listener);
	}
}
