package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;

public class BookmarkDialog extends Dialog {
	
	private TextBox bookmarkHyperlinkNameTextBox;
	private TextBox bookmarkHyperlinkUrlTextBox;
	
	private ArrayList<AddBookmarkListener> eventListener = new ArrayList<AddBookmarkListener>();
	private ArrayList<ChangeDialogListener> eventListener2 = new ArrayList<ChangeDialogListener>();
	
	/**
	 * Constructor of the BookmarkDialog class. It extends the Dialog class and implements the AddBookmarkEventCreator and ChangeDialogEventCreator.
	  * @param x - x coordinate of this BookmarkDialog
     * @param y - y coordinate of this BookmarkDialog
     * @param w - width of this BookmarkDialog
     * @param h - height of this BookmarkDialog
	 * @param eventReader - eventReader of this BookmarkDialog
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
		bookmarkHyperlinkNameTextBox = new TextBox(75, 40, width - 100, 40);
		this.setNameTextBox(bookmarkHyperlinkNameTextBox);
		this.addElement(bookmarkHyperlinkNameTextBox);
	}
	
	/**
	 * Initializes the textBox for the url input.
	 * @param width - width of the dialog
	 */
	private void initUrlInput(int width) {
		Text bookmarkHyperlinkUrlText = new Text(10, 90, "URL:");
		this.addElement(bookmarkHyperlinkUrlText);
		bookmarkHyperlinkUrlTextBox = new TextBox(75, 90, width - 100, 40);
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
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
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
			String bookmarkHyperlinkName = this.getNameTextBox().getText();
			String bookmarkHyperlinkUrlTextBox = this.getUrlTextBox().getText();
			
			for(AddBookmarkListener listener: this.getAddBookmarkListeners()) {
				listener.addBookmark(bookmarkHyperlinkName, bookmarkHyperlinkUrlTextBox);
			}
			
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
		});
		
		this.addElement(addBookmarkButton);
	}

	/**
	 * @return the textBox
	 */
	public TextBox getNameTextBox() {
		return bookmarkHyperlinkNameTextBox;
	}

	/**
	 * @param nameTextBox the nameTextBox to set
	 */
	public void setNameTextBox(TextBox nameTextBox) {
		this.bookmarkHyperlinkNameTextBox = nameTextBox;
	}
	
	/**
	 * @return the textBox
	 */
	public TextBox getUrlTextBox() {
		return bookmarkHyperlinkUrlTextBox;
	}

	/**
	 * @param urlTextBox the textBox to set
	 */
	public void setUrlTextBox(TextBox urlTextBox) {
		this.bookmarkHyperlinkUrlTextBox = urlTextBox;
	}
	
	/**
	 * Method used to set the suggested URL when loading the bookmark dialog.
	 * @param suggestedUrl
	 */
	public void setSuggestedUrl(String suggestedUrl) {
		this.getUrlTextBox().setLeftText(suggestedUrl);
	}

	public void addAddBookmarkListener(AddBookmarkListener listener) {
		this.getAddBookmarkListeners().add(listener);
	}

	public void removeAddBookmarkListener(AddBookmarkListener listener) {
		this.getAddBookmarkListeners().remove(listener);
	}
	

	protected ArrayList<AddBookmarkListener> getAddBookmarkListeners() {
		return eventListener;
	}

	public void addChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().add(listener);
	}

	public void removeChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().remove(listener);
	}

	protected ArrayList<ChangeDialogListener> getChangeDialogListeners() {
		return eventListener2;
	}
}
