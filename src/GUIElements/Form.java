package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import events.ClickHyperlinkEvent;
import events.Event;
import events.EventListener;
import events.EventReader;
import htmlElement.ContentSpan;
import canvaswindow.*;

public class Form extends GUIElement implements EventListener {

	private GUIElement gui;
	private String action;
	private List<EventListener> listeners;
	
	public Form(GUIElement gui, int x, int y,String action){
		super(x,y);
		this.setGui(gui);
		this.setAction(action);
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
	public void paint(Graphics g, int xContainer, int yContainer) {
		this.getGui().paint(g, xContainer + this.getX(), yContainer + this.getY());
		
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		return this.getGui().getGUIAtPosition(x, y);
	}


	public void sumbit() {
		String result="";
		String textResults= this.getTextResults();
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
	 * Returns the results of the textboxes with the resulting string for a form
	 */
	public String getTextResults() {
		return null;
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
	public void readEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	}
