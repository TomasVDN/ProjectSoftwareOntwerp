package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

import events.EventReader;

public class Text extends GUIElement {

	private String text;
	private FontMetrics fontMetrics;
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	
	public Text(int x, int y, String t) {
		super(x, y, 0, 0);
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
	
	public int getWidth() {
		FontMetrics fontMetric = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
		return fontMetric.stringWidth(this.getText());
	}

	public int getHeight() {
		FontMetrics fontMetric = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
		return fontMetric.getHeight();
	}


	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
	}
	
	@Override
	public void handleUnselect() {
	}

	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		g.setFont(font);
		g.setColor(Color.black);
		
		Shape oldClip = g.getClip();
		
		g.setClip(getX() + xContainer, getY() + yContainer, getWidth(), getHeight());
		super.drawCenteredText(g, this.getText(), xContainer, yContainer);
		g.setClip(oldClip);
	}
	
	/**
	 * paints text in given color
	 * @param g
	 * @param xContainer
	 * @param yContainer
	 * @param color
	 */
	public void paint(Graphics g, int xContainer, int yContainer,Color color) {
		g.setFont(font);
		g.setColor(color);
		
		Shape oldClip = g.getClip();
		
		g.setClip(getX() + xContainer, getY() + yContainer, getWidth(), getHeight());
		super.drawCenteredText(g, this.getText(), xContainer, yContainer);
		g.setClip(oldClip);
	}
	
	/*
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Text)) {
	        return false;
		} else {
			boolean stringEquals = this.getText().equals(((Text) other).getText());
			boolean xEquals = this.getX() == ((Text) other).getX();
			boolean yEquals = this.getY() == ((Text) other).getY();
			boolean widthEquals = this.getWidth() == ((Text) other).getWidth();
			boolean HeightEquals = this.getHeight() == ((Text) other).getHeight();
			if (stringEquals && xEquals && yEquals && widthEquals && HeightEquals) {
				return true;
			} else {
				return false;
			}
		}
	}
	**/
	

}
