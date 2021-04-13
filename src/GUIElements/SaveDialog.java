package GUIElements;

import java.awt.Color;
import java.util.ArrayList;

import EventCreators.ChangeDialogEventCreator;
import EventCreators.SavePageEventCreator;
import EventListeners.ChangeDialogListener;
import EventListeners.SavePageListener;
import events.EventReader;
import events.SavePageEvent;

public class SaveDialog extends Container implements ChangeDialogEventCreator, SavePageEventCreator{

	private TextBox textBox;
	
	private ArrayList<ChangeDialogListener> changeDialogEventListeners = new ArrayList<ChangeDialogListener>();
	private ArrayList<SavePageListener> savePageEventListeners = new ArrayList<SavePageListener>();

	//TODO rename buttons & fix layout
	public SaveDialog(int x, int y, int w, int h, EventReader eventReader) {
		super(x, y, w, h);
		
		textBox = new TextBox(10, 10, w - 20, 40);
		
		Button cancelButton = new Button(Math.floorDiv(w, 4), 100, new Text(Math.floorDiv(w, 4), 100, "Cancel"), true, Color.lightGray);
		cancelButton.addSingleClickListener(() -> {
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
		});
		
		Button submitButton = new Button(3*Math.floorDiv(w, 4), 100, new Text(3*Math.floorDiv(w, 4), 100, "Submit"), true, Color.lightGray);
		submitButton.addSingleClickListener(() ->{
			String filename = this.getTextBox().getText();
			
			for(SavePageListener listener: this.getSavePageListeners()) {
				listener.savePage(filename);
			}
			
			for(ChangeDialogListener listener: this.getChangeDialogListeners()) {
				listener.changeDialog("mainDialog");
			}
		});
		
		this.addElement(textBox);
		this.addElement(cancelButton);
		this.addElement(submitButton);
		
		this.addChangeDialogListener(eventReader);
		this.addSavePageListener(eventReader);
	}

	/**
	 * @return the textBox
	 */
	public TextBox getTextBox() {
		return textBox;
	}

	/**
	 * @param textBox the textBox to set
	 */
	public void setTextBox(TextBox textBox) {
		this.textBox = textBox;
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
		return changeDialogEventListeners;
	}

	@Override
	public void addSavePageListener(SavePageListener listener) {
		this.getSavePageListeners().add(listener);
	}

	@Override
	public void removeSavePageListener(SavePageListener listener) {
		this.getSavePageListeners().remove(listener);
	}
	
	protected ArrayList<SavePageListener> getSavePageListeners() {
		return savePageEventListeners;
	}
	

}
