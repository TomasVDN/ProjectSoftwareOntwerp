package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;

import facades.EventReader;

public class Button extends GUIElement {

	private Text text;
    private Color buttonColor =  Color.BLACK;
    private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
    private Boolean drawBox = false;
    
	public Button(int x, int y, int w, int h, Text text, Boolean box) {
		super(x, y, w, h);
		this.text = text;
		this.drawBox = box;
	}

	@Override
	public void handleClick() {
		new ArrayList<Runnable>(clickListeners).stream().forEach(l -> l.run());
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		new HashMap<Integer, ArrayList<Runnable>>(keyboardListeners).get(keyCode).stream().forEach(l -> l.run());
	}

	@Override
	protected void handleUnselect() {
		new ArrayList<Runnable>(unselectListener).stream().forEach(l -> l.run());
	}

	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		g.setFont(font);
		
		//content
		g.setColor(getButtonColor());
		
		//Border
		if (getDrawBox()) {
			g.drawRect(super.getX() + xContainer,super.getY() + yContainer, super.getWidth(), super.getHeight());
		}
	
		//Text
		Shape oldClip = g.getClip();
		g.setClip(getX() + xContainer, getY() + yContainer, getWidth(), getHeight());
		this.getText().paint(g, xContainer, yContainer);
		
		
		g.setColor(Color.black);
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
	 * @param text the text to set
	 */
	public void setText(Text text) {
		this.text = text;
	}

	/**
	 * @return the drawBox
	 */
	public Boolean getDrawBox() {
		return drawBox;
	}

	/**
	 * @param drawBox the drawBox to set
	 */
	public void setDrawBox(Boolean drawBox) {
		this.drawBox = drawBox;
	}

}
