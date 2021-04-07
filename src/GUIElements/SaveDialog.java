package GUIElements;

import java.awt.Color;

import events.ChangeToDialogEvent;
import events.EventReader;
import events.SavePageEvent;

public class SaveDialog extends Container{

	private TextBox textBox;
	private EventReader eventReader;
	
	public SaveDialog(int x, int y, int w, int h, EventReader eventReader) {
		super(x, y, w, h);
		
		textBox = new TextBox(10, 10, w - 20, 40);
		
		Button cancelButton = new Button(Math.floorDiv(w, 4), 100, new Text(Math.floorDiv(w, 4), 100, "Cancel"), true, Color.black);
		cancelButton.addSingleClickListener(() -> {
			ChangeToDialogEvent event = new ChangeToDialogEvent("mainDialog");
			eventReader.readEvent(event);
		});
		
		Button submitButton = new Button(3*Math.floorDiv(w, 4), 100, new Text(3*Math.floorDiv(w, 4), 100, "Submit"), true, Color.black);
		submitButton.addSingleClickListener(() ->{
			String filename = this.getTextBox().getText();
			
			SavePageEvent event = new SavePageEvent(filename);
			this.getEventReader().readEvent(event);
			
			ChangeToDialogEvent eventBis = new ChangeToDialogEvent("mainDialog");
			this.getEventReader().readEvent(eventBis);
		});
		
		this.addElement(textBox);
		this.addElement(cancelButton);
		this.addElement(submitButton);
		
		this.eventReader = eventReader;
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

	

}
