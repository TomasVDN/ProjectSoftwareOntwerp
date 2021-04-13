package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.text.TableView.TableRow;

import events.ChangeToDialogEvent;
import events.EventReader;
import events.AddBookmarkEvent;

public class BookmarkDialog extends Container{
	

	private TextBox bookmarkHyperlinkNameTextBox;
	private TextBox bookmarkHyperlinkUrlTextBox;
	private EventReader eventReader;
	
	
	public BookmarkDialog(int x, int y, int w, int h, EventReader eventReader) {
		super(x, y, w, h);
		
		/*
		 * TODO moete deze dimensies aangepast worde ofzo?
		 */
		
		// set up header
		Text headerText = new Text(10, 0, "Create a new bookmark");
		
		// set up bookmark hyperlink name input
		Text bookmarkHyperlinkNameText = new Text(0, 10, "Name:");
		bookmarkHyperlinkNameTextBox = new TextBox(10, 10, w - 100, 40);
		TableCellGUI bookmarkHyperlinkNameTextCell = new TableCellGUI(bookmarkHyperlinkNameText, 0, 0, 10, 10);
		TableCellGUI bookmarkHyperlinkNameTextBoxCell = new TableCellGUI(bookmarkHyperlinkNameTextBox, 0, 0, 10, 10);
		ArrayList<TableCellGUI> nameInputcells = new ArrayList<TableCellGUI>();
		nameInputcells.add(bookmarkHyperlinkNameTextCell);
		nameInputcells.add(bookmarkHyperlinkNameTextBoxCell);
		TableRowGUI nameInputRow = new TableRowGUI(nameInputcells, 0, 0);
		
		// set up bookmark hyperlink url input
		Text bookmarkHyperlinkUrlText = new Text(0, 20, "URL:");
		bookmarkHyperlinkUrlTextBox = new TextBox(10, 20, w - 100, 40);
		TableCellGUI bookmarkHyperlinkUrlTextCell = new TableCellGUI(bookmarkHyperlinkUrlText, 0, 0, 10, 10);
		TableCellGUI bookmarkHyperlinkUrlTextBoxCell = new TableCellGUI(bookmarkHyperlinkUrlTextBox, 0, 0, 10, 10);
		ArrayList<TableCellGUI> urlInputcells = new ArrayList<TableCellGUI>();
		urlInputcells.add(bookmarkHyperlinkUrlTextCell);
		urlInputcells.add(bookmarkHyperlinkUrlTextBoxCell);
		TableRowGUI urlInputRow = new TableRowGUI(urlInputcells, 0, 0);
		
		// set up input table
		ArrayList<TableRowGUI> tableRows = new ArrayList<TableRowGUI>();
		tableRows.add(nameInputRow);
		tableRows.add(urlInputRow);
		TableGUI inputTable = new TableGUI(tableRows, 10, 40);
		
		// set up cancel button
		Button cancelButton = new Button(Math.floorDiv(w, 4), 200, new Text(Math.floorDiv(w, 4), 200, "Cancel"), true, Color.black);
		cancelButton.addSingleClickListener(() -> {
			ChangeToDialogEvent event = new ChangeToDialogEvent("mainDialog");
			eventReader.readEvent(event);
		});
		
		// set up submit button
		Button submitButton = new Button(2*Math.floorDiv(w, 4), 200, new Text(2*Math.floorDiv(w, 4), 200, "Add Bookmark	"), true, Color.black);
		submitButton.addSingleClickListener(() ->{
			String bookmarkHyperlinkName = this.getNameTextBox().getText();
			String bookmarkHyperlinkUrlTextBox = this.geturlTextBox().getText();
			
			AddBookmarkEvent addBookmarkEvent = new AddBookmarkEvent(bookmarkHyperlinkName, bookmarkHyperlinkUrlTextBox);
			this.getEventReader().readEvent(addBookmarkEvent);
			
			ChangeToDialogEvent changeToMainDialogEvent = new ChangeToDialogEvent("mainDialog");
			this.getEventReader().readEvent(changeToMainDialogEvent);
		});
		
		// add elements to BookmarkDialog container
		this.addElement(headerText);
		this.addElement(inputTable);
		this.addElement(cancelButton);
		this.addElement(submitButton);
		
		this.eventReader = eventReader;
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
	public TextBox geturlTextBox() {
		return bookmarkHyperlinkUrlTextBox;
	}

	/**
	 * @param textBox the textBox to set
	 */
	public void seturlTextBox(TextBox urlTextBox) {
		this.bookmarkHyperlinkUrlTextBox = urlTextBox;
	}

	/**
	 * @return the eventReader
	 */
	public EventReader getEventReader() {
		return eventReader;
	}

	/**
	 * @param eventReader the eventReader to set
	 */
	public void setEventReader(EventReader eventReader) {
		this.eventReader = eventReader;
	}
	
	public void setInitialInputs(String suggestedURL) {
		this.getNameTextBox().setLeftText("");
		this.geturlTextBox().setLeftText(suggestedURL);
	}
}
