package GUIElements;

import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import EventListeners.*;


public class Form extends GUIElement implements ActionListener {

	private GUIElement rootGui;
	private String action;
	private ArrayList<FormListener> listeners = new ArrayList<FormListener>();
	
	/**
	 * Creates a form class
	 * @param gui
	 *  the gui element of the form
	 * @param x
	 * 	the x position 
	 * @param y
	 * 	the y position
	 * @param action
	 * 	the action string of the form
	 * @param listener
	 * 	the initialized listener, if no listener in the beginning is applied to it use null
	 */
	public Form(GUIElement gui, int x, int y,String action){
		super(x,y);
		this.setGui(gui);
		this.setAction(action);
		this.addSelfToRootGui(); // adds the form as listener to the gui
	}
	
	/**
	 * Add itself as listener to all action creators
	 */
	private void addSelfToRootGui() {
		ArrayList<Button> array = new ArrayList<>();
		this.getGuiClass( Button.class, array);
		for(Button ac : array) {
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
	}

	@Override
	public void handleClick() {
	}

	@Override
	public void paint(Graphics g) {
		if(this.getRootGui()!=null) {
			Graphics newG= g.create(getX(), getY(), this.getRootGui().getWidth()+1, this.getRootGui().getHeight()+1);
			this.getRootGui().paint(newG);
		}
	}
	
	/**
	 * Returns the GUI if the given position is between its bounds
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		if(this.getRootGui()==null) {
			return null;
		}
		return this.getRootGui().getGUIAtPosition(x-this.getX(), y-this.getY());
	}


	/**
	 * submit the elements inside the form, first searches for all textboxes that
	 * are inside the root of the form then adds them in the order found with
	 * the correct symbols between them
	 */
	private void submit() {
		String result="";
		result+=this.getAction() + "?";
		result+=this.getTextResults();
		for(FormListener reader: this.getListeners()) {
			reader.runUrlAttribute(result);
		}
	}
	


	/**
	 * 
	 * @return
	 * Returns the results of the textboxes in their string 
	 * 	 */
	private String getTextResults() {
		String result="";
		ArrayList<TextBox> textBoxesInForm =new ArrayList<TextBox>();
		textBoxesInForm = this.getGuiClass(TextBox.class, textBoxesInForm);
		try {
		for(int i =0;i<textBoxesInForm.size();i++) {
			TextBox textBox = textBoxesInForm.get(i);
			if(i!=0) {
				result+="&";
			}
			result+=URLEncoder.encode(textBox.getName(),StandardCharsets.UTF_8.name());
			result+="=";
			result+= URLEncoder.encode(textBox.getText(),StandardCharsets.UTF_8.name());
		}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return result; // removes the last &
	}
	

	
	/**
	 * returns the root of the form
	 * @return
	 */
	public GUIElement getRootGui() {
		return rootGui;
	}


	/**
	 * Sets the root of the form
	 */
	private void setGui(GUIElement gui) {
		this.rootGui = gui;
	}


	/**
	 * returns the action of the form
	 */
	public String getAction() {
		return action;
	}


	/**
	 *  sets the action of the form to the given string
	 */
	private void setAction(String action) {
		if(action==null) {
			throw new IllegalArgumentException("not a valid action");
		}
		this.action = action;
	}


	@Override
	public <T>  ArrayList<T> getGuiClass(Class<T> cls,ArrayList<T> array){
		if(cls.isInstance(this)) {
			array.add( (T) this); //TODO dit is niet veilig blijkbaar
		}
		if(this.getRootGui()==null) {
			return array;
		}
		return this.getRootGui().getGuiClass(cls, array);
	}

	@Override
	public void clickButton() {
		this.submit();
	}

	public void addFormListener(FormListener listener) {
		if(listener!=null) {
			this.listeners.add(listener);
		}
	}

	public  void removeFormListener(FormListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Returns a list of all the listeners of given form
	 */
	public ArrayList<FormListener> getListeners() {
		return this.listeners; //TODO clone this
	}
	
	}
