package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import canvaswindow.FontMetricGetter;

public class Text extends GUIElement {

	private String text;
	private FontMetrics fontMetrics;
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	
	/**
	 * Constructor of the Text class.
	 * @param x - x coordinate of the Text
	 * @param y - y coordinate of the Text
	 * @param t - content of the Text
	 */
	public Text(int x, int y, String t) {
		super(x, y, 0, 0);
		text = t;
		
		FontMetricGetter f = FontMetricGetter.getInstance();
		this.fontMetrics = f.getFontMetric(font);
		
		setHeight(fontMetrics.getHeight());
		setWidth(fontMetrics.stringWidth(text));
	}

	/**
	 * Draws this.text on coordinates (this.x + xContainer, y + yContainer)
	 * @param xContainer - x coordinate of parent container
	 * @param yContainer - y coordinate of parent container
	 * @param g - graphics object used to paint
	 */
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		g.setColor(Color.black);
		
		Shape oldClip = g.getClip();
		
		g.setClip(getX(), getY(), getWidth(), getHeight());
		super.drawCenteredText(g, this.getText());
		g.setClip(oldClip);
	}
	
	/**
	 * Draws this.text on coordinates (this.x + xContainer, y + yContainer) in given color
	 * @param xContainer - x coordinate of parent container
	 * @param yContainer - y coordinate of parent container
	 * @param g - graphics object used to paint
	 * @param color - color to draw content with
	 */
	public void paint(Graphics g, Color color) {
		g.setFont(font);
		g.setColor(color);
		
		Shape oldClip = g.getClip();
		
		g.setClip(getX(), getY(), getWidth(), getHeight());
		super.drawCenteredText(g, this.getText());
		g.setClip(oldClip);
	}
	
	/**
	 * Returns the width of the content.
	 */
	@Override
	public int getWidth() {
		return this.fontMetrics.stringWidth(this.getText());
	}
	
	/**
	 * Returns the height of the content.
	 */
	@Override
	public int getHeight() {
		return this.fontMetrics.getHeight();
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
	 * @return this.text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text - the text to set
	 */
	public void setText(String text) {
		if(text==null) {
			throw new IllegalArgumentException("Text has to have a string");
		}
		this.text = text;
	}

	@Override
	public void handleClick() {
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
	}
	
	@Override
	public void handleUnselect() {
	}
}
