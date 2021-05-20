package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import EventListeners.ActionListener;
import EventListeners.ChangeDialogListener;
import EventListeners.SavePageListener;

/**
 * Dialog used to save a page. Contains one textBoxes (to enter the name of the file) and two buttons
 * (one to cancel and return to the main dialog, and one to save).
 */
public class SaveDialog extends Dialog {

	private ScrollableTextBox textBox;
	private String activeHtml;

	/**
	 * Constructor of the SaveDialog class. It extends the Dialog class and implements the SavePageEventCreator and ChangeDialogEventCreator.
	  * @param x - x coordinate of this SaveDialog
     * @param y - y coordinate of this SaveDialog
     * @param w - width of this SaveDialog
     * @param h - height of this SaveDialog
	 * @param activeHTML - the active HTMLCode of the active HTMLDocument
	 */
	public SaveDialog(int x, int y, int w, int h,String activeHTML) {
		super(x, y, w, h);
		
		this.initTextBox(w);
		this.initCancelButton(w);
		this.initSaveButton(w);
		this.setActiveHtml(activeHTML);
	}
	
	/**
	 * Initializes the textBox used for the file name input.
	 * @param width - width of this dialog.
	 */
	private void initTextBox(int width) {
		Text text = new Text(10, 15, "File name:");
		this.addElement(text);
		textBox = new ScrollableTextBox(15 + text.getWidth(),10,new TextBox(0, 0, width - 20 - text.getWidth(), 40));
		this.addElement(textBox);
	}
	
	/**
	 * Initializes the cancel button.
	 * @param width - width of this dialog.
	 */
	private void initCancelButton(int width) {
		int xPos = Math.floorDiv(width, 4);
		Button cancelButton = new Button(xPos, 100, new Text(0, 0, "Cancel"), true, Color.lightGray);
		
		cancelButton.addSingleClickListener(() -> {
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
		});
		
		this.addElement(cancelButton);
	}
	
	/**
	 * Initializes the save button.
	 * @param width - width of this dialog.
	 */
	private void initSaveButton(int width) {
		int xPos = 3 * Math.floorDiv(width, 4);
		Button submitButton = new Button( xPos, 100, new Text(0 , 0, "Save"), true, Color.lightGray);
		
		submitButton.addSingleClickListener(() ->{
			String filename = this.getTextBox().getText();
			
			for(SavePageListener listener: this.getSavePageListeners()) {
				listener.savePage(filename,this.activeHtml);
			}
			
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
		});
		
		this.addElement(submitButton);
	}

	/**
	 * @return the textBox
	 */
	public TextBox getTextBox() {
		return textBox.getTextBox();
	}
	
	/**
	 * Returns the html code.
	 */
	public String getActiveHtml() {
		return activeHtml;
	}

	/**
	 * Sets the html code.
	 * @param activeHtml
	 */
	private void setActiveHtml(String activeHtml) {
		this.activeHtml = activeHtml;
	}

	//LISTENERS
	
	private ArrayList<ChangeDialogListener> changeDialogEventListeners = new ArrayList<ChangeDialogListener>();
	private ArrayList<SavePageListener> savePageEventListeners = new ArrayList<SavePageListener>();

	/**
	 * Add the given ChangeDialogListener to the list of ChangeDialogListeners
	 * @param listener
	 */
	public void addChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().add(listener);
	}

	/**
	 * Removes the given ChangeDialogListener from the list of ChangeDialogListeners
	 * @param listener
	 */
	public void removeChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().remove(listener);
	}
	
	/**
	 * Returns the array of ChangeDialogListener of this SaveDialog
	 */
	protected ArrayList<ChangeDialogListener> getChangeDialogListeners() {
		return changeDialogEventListeners;
	}

	/**
	 * Add the given SavePageListener to the list of SavePageListeners
	 * @param listener
	 */
	public void addSavePageListener(SavePageListener listener) {
		this.getSavePageListeners().add(listener);
	}

	/**
	 * Removes the given SavePageListener from the list of SavePageListeners
	 * @param listener
	 */
	public void removeSavePageListener(SavePageListener listener) {
		this.getSavePageListeners().remove(listener);
	}
	
	/**
	 * Returns the array of SavePageListeners of this SaveDialog
	 */
	protected ArrayList<SavePageListener> getSavePageListeners() {
		return savePageEventListeners;
	}
}