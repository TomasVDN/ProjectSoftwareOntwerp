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
	protected Color color = Color.black;
	

	/**
	 * Constructor of the Text class.
	 * @param x - x coordinate of the Text
	 * @param y - y coordinate of the Text
	 * @param t - content of the Text
	 */
	public Text(int x, int y, String t) {
		super(x, y);
		text = t;
		
		FontMetricGetter f = FontMetricGetter.getInstance();
		this.fontMetrics = f.getFontMetric(font);
		
		setHeight(fontMetrics.getHeight());
		setWidth(fontMetrics.stringWidth(text));
	}
	
	/**
	 * Constructor of the Text class from another Text class.
	 * @param x - x coordinate of the Text
	 * @param y - y coordinate of the Text
	 * @param t - Text to copy
	 */
	public Text(int x,int y, Text t) {
		super(x,y);
		
		this.setText(t.getText());
		this.setFont(t.getFont());
		
		FontMetricGetter f = FontMetricGetter.getInstance();
		this.fontMetrics = f.getFontMetric(font);
		
		setHeight(fontMetrics.getHeight());
		setWidth(fontMetrics.stringWidth(text));
	}

	/**
	 * Draws this class.
	 * @param g - graphics object used to paint
	 */
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		g.setColor(this.getColor());
		
		Shape oldClip = g.getClip();
		
		g.setClip(getX(), getY(), getWidth(), getHeight());
		super.drawCenteredText(g, this.getText());
		g.setClip(oldClip);
		g.setColor(Color.black);
	}
	
	/**
	 * Draws this class in the given color.
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

	public Color getColor() {
		return color;
	}

	protected void setColor(Color color) {
		this.color = color;
	}

}
