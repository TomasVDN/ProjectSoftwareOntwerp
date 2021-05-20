package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import EventListeners.ActionListener;
import java.util.ArrayList;

/**
 * Class that implements a button. It is a GUIElement that can be clicked on to trigger something.
 *
 */
public class Button extends GUIElement {

	private Text text;
    private Color buttonColor =  Color.BLACK;
    private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
    private Boolean mustDrawBox = false;
    private List<ActionListener> listeners = new ArrayList<ActionListener>();
	protected ArrayList<Runnable> clickListeners = new ArrayList<Runnable>();

    private State state = new UnpressedState();

    
    /**
     * Constructor of the Button class.
     * @param x - x coordinate of this Button
     * @param y - y coordinate of this Button
     * @param w - width of this Button
     * @param h - height of this Button
     * @param text - content of this Button
     * @param mustDrawBox - boolean: if true, a surrounding box will be drawn
     * @param color - Color: color of this Button
     */
	public Button(int x, int y, int w, int h, Text text, Boolean mustDrawBox, Color color) {
		super(x, y, w, h);
		this.setButtonColor(color);
		this.setText(text);
		this.setMustDrawBox(mustDrawBox);
	}
	
    /**
     * Constructor of the Button class, but without width or height. Those will be set to the width and the height of the given text.
     * @param x - x coordinate of this Button
     * @param y - y coordinate of this Button
     * @param text - content of this Button
     * @param box - boolean: if true, a surrounding box will be drawn
     * @param color - Color: color of this Button
     */
	public Button(int x, int y, Text text, Boolean box, Color color) {
		super(x, y);
		this.setButtonColor(color);
		this.setText(text);
		this.setMustDrawBox(box);
		this.setWidth(text.getWidth());
		this.setHeight(text.getHeight());
	}
	
	/**
	 * This expresses the state in which the button is in.
	 * There are 2 states, the pressed state and the unpressedState
	 *
	 */
	private static abstract class State {
		/**
		 * Paints the button
		 * @param g - the graphics for drawing
		 */
		abstract void paint(Graphics g);
		/**
		 * Method called when button is pressed
		 */
		abstract void handlePressClick();
		/**
		 * Method called when mouse is released on button
		 * @param releasedOn - True if the mouse is released on the button
		 *  otherwise is false
		 */
		abstract void handleReleaseClick(boolean releasedOn);
		/**
		 * Returns the current state as a string
		 */
		abstract String getStateName();
		}
	/**
	 * Unpressed state of the button. Default state.
	 *
	 */
	private class UnpressedState extends State {
		
		/**
		 * Paints the button.
		 * @param g - The graphic object to paint with
		 */
		@Override
		void paint(Graphics g) {
			if (mustDrawBox()) {
				drawBox(g,getButtonColor());
			}
			Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);
			drawText(newG);		
		}
		
		/**
		 * When pressed, the button will change to the pressed state.
		 */
		@Override
		void handlePressClick() {
			state=new PressedState();
		}
		
		@Override
		void handleReleaseClick(boolean releasedOn) {
			
		}
		
		/**
		 * @return state name
		 */
		@Override
		String getStateName() {
			return "UnpressedState";
		}
	}
	
	/**
	 * Pressed state of the button
	 *
	 */
	private class PressedState extends State {
		
		/**
		 * Paints the button.
		 * @param g - The graphic object to paint with
		 */
		@Override
		void paint(Graphics g) {
			if (mustDrawBox()) {
				drawBox(g,getButtonColor().darker());
			}
			Graphics newG= g.create(getX(), getY(), getWidth()+1, getHeight()+1);
			drawText(newG);	
		}
		
		@Override
		void handlePressClick() {
			
		}
		
		/**
		 * When released, the button will change to the unpressed state.
		 */
		@Override
		void handleReleaseClick(boolean releasedOn) {
			state = new UnpressedState();
			if(releasedOn) {
				new ArrayList<>(clickListeners).stream().forEach(l -> l.run());
			}
		}
		
		/**
		 * @return state name
		 */
		@Override
		String getStateName() {
			return "PressedState";
		}
	}
	
	

	/**
	 * Paints the multiple components of the button.
	 * @param g - the graphic object to paint with
	 */
	@Override
	public void paint(Graphics g) {
		state.paint(g);
	}
	
	/**
	 * Draws a box in the given color with a black line surrounding it
	 * @param g - the graphics to draw
	 * @param buttonColor - the color of the rectangle
	 */
	private void drawBox(Graphics g,Color buttonColor) {
		g.setColor(Color.black);
		g.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		g.setColor(buttonColor);
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}
	
	/**
	 * Draws black text inside the button
	 * @param g - the graphic object to paint with
	 */
	private void drawText(Graphics g) {
		g.setColor(Color.black);
		this.getText().paint(g);
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the buttonColor
	 */
	public Color getButtonColor() {
		return buttonColor;
	}

	/**
	 * @param buttonColor the buttonColor to set
	 */
	public void setButtonColor(Color buttonColor) {
		if(buttonColor==null) {
			throw new IllegalArgumentException("not a valid color");
		}
		this.buttonColor = buttonColor;
	}

	/**
	 * @return the text
	 */
	public Text getText() {
		return text;
	}

	/**
	 * @param text - the text to set
	 */
	public void setText(Text text) {
		if(text==null) {
			throw new IllegalArgumentException("Given text must exist");
		}
		this.text = text;
	}

	/**
	 * @return the mustDrawBox
	 */
	public Boolean mustDrawBox() {
		return mustDrawBox;
	}

	/**
	 * @param mustDrawBox - the drawBox to set
	 */
	public void setMustDrawBox(Boolean mustDrawBox) {
		this.mustDrawBox = mustDrawBox;
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {		
	}

	@Override
	protected void handleUnselect() {	
	}	

	/**
	 * adds a listener for a singleClick action
	 * @param f: the listener to be added
	 */
	public void addSingleClickListener(Runnable f) {
		clickListeners.add(f);
	}

	@Override
	public void handleClick() {
	}
	
	/**
	 * Handles the mouse presses.
	 */
	@Override
	public void handlePressClick(int x,int y) {
		state.handlePressClick();
	}
	
	/**
	 * Handles the mouse releases.
	 */
	@Override
	public void handleReleaseClick(boolean releasedOn) {
		state.handleReleaseClick(releasedOn);
	}

	/**
	 * Add the given EventListener to the list of EventListeners
	 * @param listener
	 */
	public void addListener(ActionListener listener) {
		if(listener!=null) {
			listeners.add(listener);
		}
	}
	
	/**
	 * Removes the given EventListener form the list of EventListeners
	 */
	public void removeListener(ActionListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * Returns the array of actionListeners to this button
	 */
	public List<ActionListener> getListeners() {
		return listeners;
	}

	/**
	 * Returns the string representing the state of the button
	 */
	public String getStateName() {
		return state.getStateName();
	}

}
