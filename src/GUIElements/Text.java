package GUIElements;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

public class Text extends GUIElement {

	private String text;
	private FontMetrics fontMetrics;
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	
	public Text(int x, int y, int w, int h, String t) {
		super(x, y, w, h);
		text = t;
		this.fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
		setHeight(fontMetrics.getHeight());
		setWidth(fontMetrics.stringWidth(text));
	}
	
	/**
	 * Returns the font of this text
	 * @return font
	 */
	public Font getFont() {
		return this.font;
	}
	
	/**
	 * Sets the font of this text
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void handleClick() {
		new ArrayList<>(clickListeners).forEach(l -> l.run());
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
		if (new HashMap<Integer, ArrayList<Runnable>>(keyboardListeners).get(keyCode) == null)
			return;
		
		new HashMap<Integer, ArrayList<Runnable>>(keyboardListeners).get(keyCode).stream().forEach(l -> l.run());
	}
	
	@Override
	public void handleUnselect() {
		new ArrayList<Runnable>(unselectListener).stream().forEach(l -> l.run());
	}

	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		g.setFont(font);
		
		Shape oldClip = g.getClip();
		
		g.setClip(getX() + xContainer, getY() + yContainer, getWidth(), getHeight());
		super.drawCenteredText(g, this.getText(), xContainer, yContainer);
		g.setClip(oldClip);
	}

}
