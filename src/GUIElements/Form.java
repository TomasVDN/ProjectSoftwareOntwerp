package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import events.ClickHyperlinkEvent;
import events.Event;
import events.EventListener;
import events.EventListener;
import events.SubmitEvent;
import htmlElement.ContentSpan;
import canvaswindow.*;

public class Form extends GUIElement implements EventListener {

	private GUIElement gui;
	private String action;
	private List<EventListener> listeners = new ArrayList<EventListener>();
	
	public Form(GUIElement gui, int x, int y,String action,EventListener listener){
		super(x,y);
		this.setGui(gui);
		this.setAction(action);
		this.getGui().addListener(this); // adds the form as listener to the gui
		this.addListener(listener);
	}



	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		this.getGui().handleKeyEvent(keyCode, keyChar, modifiersEx);
	}

	@Override
	protected void handleUnselect() {
		//TODO denk dat dit leeg moet blijven
		//this.getGui().handleUnselect();
	}

	@Override
	public void handleClick() {
		//TODO same
	}

	@Override
	public void paint(Graphics g) {
		g.translate(this.getX(), this.getY());
		this.getGui().paint(g);
		g.translate(-this.getX(), -this.getY());
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		return this.getGui().getGUIAtPosition(x, y);
	}


	public void submit() {
		String result="";
		result+=this.getAction() + "?";
		result+=this.getTextResults();
		Event event = new ClickHyperlinkEvent(result);
		for(EventListener reader: this.getListeners()) {
			reader.readEvent(event);
		}
	}
	
	/**
	 * 
	 * @return
	 * Returns the results of the textboxes in their string 
	 * 	 */
	public String getTextResults() {
		String result="";
		ArrayList<TextBox> textBoxesInForm = this.getUsedTextBoxes();
		for(TextBox textBox: textBoxesInForm) {
			result+=textBox.toString() +"&";
		}
		return result.substring(0, result.length() - 1); // removes the last &
	}
	
	
	
	
	public GUIElement getGui() {
		return gui;
	}



	public void setGui(GUIElement gui) {
		this.gui = gui;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	@Override
	void addListener(EventListener listener) {
		this.getListeners().add(listener);
	}



	@Override
	void removeListener(EventListener listener) {
		this.getListeners().remove(listener);
	}



	public List<EventListener> getListeners() {
		return listeners;
	}



	public void setListeners(List<EventListener> listeners) {
		this.listeners = listeners;
	}



	@Override
	public void readEvent(Event event) {//TODO bad practice mag zag geen andere oplossing
		if(event instanceof SubmitEvent) {
			SubmitEvent submit = (SubmitEvent) event;
			submit.execute(this);
		}
	}
	
	
	@Override
	public ArrayList<TextBox> getUsedTextBoxes() {
		return this.getGui().getUsedTextBoxes();
	}
	
	
	}
