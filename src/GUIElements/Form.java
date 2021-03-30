package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

import htmlElement.ContentSpan;

public class Form extends GUIElement {

	private GUIElement gui;
	private String action;
	
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
	}}
