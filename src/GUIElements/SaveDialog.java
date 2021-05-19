package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import EventListeners.ChangeDialogListener;
import EventListeners.SavePageListener;

public class SaveDialog extends Dialog {

	private ScrollableTextBox textBox;
	private HTMLDocument activeHtml;


	/**
	 * Constructor of the SaveDialog class. It extends the Dialog class and implements the SavePageEventCreator and ChangeDialogEventCreator.
	  * @param x - x coordinate of this SaveDialog
     * @param y - y coordinate of this SaveDialog
     * @param w - width of this SaveDialog
     * @param h - height of this SaveDialog
	 * @param eventReader - eventReader of this SaveDialog
	 */
	public SaveDialog(int x, int y, int w, int h,HTMLDocument activeHTML) {
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
				listener.savePage(filename,this.getActiveHtml().getHTMLCode());
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

	
	
	//LISTENERS
	
	private ArrayList<ChangeDialogListener> changeDialogEventListeners = new ArrayList<ChangeDialogListener>();
	private ArrayList<SavePageListener> savePageEventListeners = new ArrayList<SavePageListener>();

	public void addChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().add(listener);
	}

	public void removeChangeDialogListener(ChangeDialogListener listener) {
		this.getChangeDialogListeners().remove(listener);
	}
	
	protected ArrayList<ChangeDialogListener> getChangeDialogListeners() {
		return changeDialogEventListeners;
	}

	public void addSavePageListener(SavePageListener listener) {
		this.getSavePageListeners().add(listener);
	}

	public void removeSavePageListener(SavePageListener listener) {
		this.getSavePageListeners().remove(listener);
	}
	
	protected ArrayList<SavePageListener> getSavePageListeners() {
		return savePageEventListeners;
	}
	
	public HTMLDocument getActiveHtml() {
		return activeHtml;
	}

	public void setActiveHtml(HTMLDocument activeHtml) {
		this.activeHtml = activeHtml;
	}

}
