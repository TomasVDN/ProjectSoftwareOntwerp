package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

import EventCreators.ActionCreator;
import EventCreators.FormEventCreator;
import EventListeners.*;
import events.EventReader;


public class Form extends GUIElement implements ActionListener,FormEventCreator {

	private GUIElement rootGui;
	private String action;
	private ArrayList<FormListener> listeners = new ArrayList<FormListener>();
	
	public Form(GUIElement gui, int x, int y,String action,EventReader listener){
		super(x,y);
		this.setGui(gui);
		this.setAction(action);
		this.addSelfToRootGui(); // adds the form as listener to the gui
		this.addFormListener(listener);
	}
	
	private void addSelfToRootGui() {
		ArrayList<ActionCreator> array = new ArrayList<>();
		this.getRootGui().getGuiClass( ActionCreator.class, array);
		for(ActionCreator ac : array) {
			ac.addListener(this);
		}
	}



	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		if(this.getRootGui()!=null) {
			this.getRootGui().handleKeyEvent(keyCode, keyChar, modifiersEx);
		}
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
		if(this.getRootGui()!=null) {
			this.getRootGui().paint(g);
		}
		g.translate(-this.getX(), -this.getY());
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		if(this.getRootGui()==null) {
			return null;
		}
		return this.getRootGui().getGUIAtPosition(x, y);
	}


	public void submit() {
		String result="";
		result+=this.getAction() + "?";
		result+=this.getTextResults();
		for(FormListener reader: this.getListeners()) {
			reader.handleFormSubmit(result);
		}
	}
	




	/**
	 * 
	 * @return
	 * Returns the results of the textboxes in their string 
	 * 	 */
	public String getTextResults() {
		String result="";
		ArrayList<TextBox> textBoxesInForm =new ArrayList<TextBox>();
		textBoxesInForm = this.getGuiClass(TextBox.class, textBoxesInForm);
		for(TextBox textBox: textBoxesInForm) {
			result+=textBox.toString() +"&";
		}
		return result.substring(0, result.length() - 1); // removes the last &
	}
	
	
	
	
	public GUIElement getRootGui() {
		return rootGui;
	}



	public void setGui(GUIElement gui) {
		this.rootGui = gui;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		if(action==null) {
			throw new IllegalArgumentException("not a valid action");
		}
		this.action = action;
	}


	
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array){
		if(this.getRootGui()==null) {
			return array;
		}
		return this.getRootGui().getGuiClass(cls, array);
	}



	@Override
	public void clickButton() {
		this.submit();
	}




	@Override
	public void addFormListener(FormListener listener) {
		this.listeners.add(listener);
	}



	@Override
	public  void removeFormListener(FormListener listener) {
		this.listeners.remove(listener);
	}

	private ArrayList<FormListener> getListeners() {
		return this.listeners; //TODO clone this
	}





	
	
	}
