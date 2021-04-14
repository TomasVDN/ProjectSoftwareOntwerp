package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;

import java.util.List;

import EventCreators.ActionCreator;
import EventListeners.ActionListener;


import java.util.ArrayList;


public class Button extends GUIElement implements ActionCreator {

	private Text text;
    private Color buttonColor =  Color.BLACK;
    private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
    private Boolean mustDrawBox = false;
    private List<ActionListener> listeners = new ArrayList<ActionListener>();
    
    
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
	
	
	public Button(int x, int y, Text text, Boolean box, Color color) {
		super(x, y,text.getWidth(),text.getHeight());
		this.setButtonColor(color);
		this.setText(text);
		this.setMustDrawBox(box);
	}

	/**
	 * Paint the multiple components of a button.
	 */
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		g.setColor(Color.black);
		
		if (mustDrawBox()) this.drawBox(g);
		
		this.drawText(g);	
	}
	
	private void drawBox(Graphics g) {
		g.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());

		g.setColor(getButtonColor());
		g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		g.setColor(Color.black);
	}
	
	private void drawText(Graphics g) {
		Shape oldClip = g.getClip();
		g.setClip(getX(), getY(), getWidth(), getHeight());
		this.getText().paint(g,Color.black);
		g.setClip(oldClip);
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
	
	protected ArrayList<Runnable> clickListeners = new ArrayList<Runnable>();
	

	/**
	 * adds a listener for a singleClick action
	 * @param f: the listener to be added
	 */
	public void addSingleClickListener(Runnable f) {
		clickListeners.add(f);
	}

	@Override
	public void handleClick() {
		//new ArrayList<>(clickListeners).stream().forEach(l -> l.run()); TODO
	}
	
	@Override
	public void handleReleaseClick() {
		new ArrayList<>(clickListeners).stream().forEach(l -> l.run());
	}
	

	/**
	 * Add the given EventListener to a list of EventListeners
	 * @param listener
	 */
	@Override
	public void addListener(ActionListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * removes the given EventListener form a list of EventListeners
	 */
	@Override
	public void removeListener(ActionListener listener) {
		listeners.remove(listener);
	}
	
	public List<ActionListener> getListeners() {
		return listeners;
	}




}
