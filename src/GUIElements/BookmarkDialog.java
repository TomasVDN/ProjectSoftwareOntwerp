package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import EventCreators.AddBookmarkEventCreator;
import EventCreators.ChangeDialogEventCreator;
import EventListeners.AddBookmarkListener;
import EventListeners.ChangeDialogListener;
import events.EventReader;

public class BookmarkDialog extends Dialog implements AddBookmarkEventCreator, ChangeDialogEventCreator{
	
	private TextBox bookmarkHyperlinkNameTextBox;
	private TextBox bookmarkHyperlinkUrlTextBox;
	
	private ArrayList<AddBookmarkListener> eventListener = new ArrayList<AddBookmarkListener>();
	private ArrayList<ChangeDialogListener> eventListener2 = new ArrayList<ChangeDialogListener>();
	
	public BookmarkDialog(int x, int y, int w, int h, EventReader eventReader) {
		super(x, y, w, h);

		this.initHeader();
		this.initNameInput(w);
		this.initUrlInput(w);
		this.initCancelButton(w);
		this.initAddBookmarkButton(w);
		
		this.addAddBookmarkListener(eventReader);
		this.addChangeDialogListener(eventReader);
	}
	
	private void initHeader() {
		Text headerText = new Text(10, 0, "Create a new bookmark");
		this.addElement(headerText);
	}
	
	private void initNameInput(int width) {
		Text bookmarkHyperlinkNameText = new Text(10, 40, "Name:");
		this.addElement(bookmarkHyperlinkNameText);
		bookmarkHyperlinkNameTextBox = new TextBox(75, 40, width - 100, 40);
		this.setNameTextBox(bookmarkHyperlinkNameTextBox);
		this.addElement(bookmarkHyperlinkNameTextBox);
	}
	
	private void initUrlInput(int width) {
		Text bookmarkHyperlinkUrlText = new Text(10, 90, "URL:");
		this.addElement(bookmarkHyperlinkUrlText);
		bookmarkHyperlinkUrlTextBox = new TextBox(75, 90, width - 100, 40);
		this.setUrlTextBox(bookmarkHyperlinkUrlTextBox);
		this.addElement(bookmarkHyperlinkUrlTextBox);
	}
	
	private void initCancelButton(int width) {
		int xPos = Math.floorDiv(width, 4);
		Button cancelButton = new Button(xPos, 200, new Text(xPos, 200, "Cancel"), true, Color.lightGray);
		cancelButton.addSingleClickListener(() -> {
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
		});
		
		this.addElement(cancelButton);
	}
	
	private void initAddBookmarkButton(int width) {
		int xPos = 2 * Math.floorDiv(width, 4);
		
		Button addBookmarkButton = new Button(xPos, 200, new Text(xPos, 200, "Add Bookmark"), true, Color.lightGray);
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
	 * @param textBox the textBox to set
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
	 * @param textBox the textBox to set
	 */
	public void setUrlTextBox(TextBox urlTextBox) {
		this.bookmarkHyperlinkUrlTextBox = urlTextBox;
	}
	
	public void setSuggestedUrl(String suggestedUrl) {
		this.getUrlTextBox().setLeftText(suggestedUrl);
	}

	@Override
	public void addAddBookmarkListener(AddBookmarkListener listener) {
		this.getAddBookmarkListeners().add(listener);
	}

	@Override
	public void removeAddBookmarkListener(AddBookmarkListener listener) {
		this.getAddBookmarkListeners().remove(listener);
	}
	

	protected ArrayList<AddBookmarkListener> getAddBookmarkListeners() {
		return eventListener;
	}

	@Override
	public void addChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().add(listener);
	}

	@Override
	public void removeChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().remove(listener);
	}
	

	protected ArrayList<ChangeDialogListener> getChangeDialogListeners() {
		return eventListener2;
	}
}
