package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;

public class Button extends GUIElement {

	private Text text;
    private Color buttonColor =  Color.BLACK;
    private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
    private Boolean drawBox = false;
    
    /**
     * Constructor of the Button class.
     * @param x - x coordinate of this Button
     * @param y - y coordinate of this Button
     * @param w - width of this Button
     * @param h - height of this Button
     * @param text - content of this Button
     * @param box - boolean: if true, a surrounding box will be drawn
     * @param color - Color: color of this Button
     */
	public Button(int x, int y, int w, int h, Text text, Boolean box, Color color) {
		super(x, y, w, h);
		this.setButtonColor(color);
		this.setText(text);
		this.setDrawBox(box);
	}
	
	
	public Button(int x, int y, Text text, Boolean box, Color color) {
		super(x, y,text.getWidth(),text.getHeight());
		this.setButtonColor(color);
		this.setText(text);
		this.setDrawBox(box);
	}

	/**
	 * Paint the multiple components of a button.
	 */
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		
		//content
		g.setColor(getButtonColor());
		
		//Border: draw if boolean this.drawBox is true
		if (drawBox()) {
			g.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		}
	
		//Text
		Shape oldClip = g.getClip();
		g.setClip(getX(), getY(), getWidth(), getHeight());
		this.getText().paint(g,this.getButtonColor());
		g.setClip(oldClip);
		
		g.setColor(Color.black);	
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
	 * @return the drawBox
	 */
	public Boolean drawBox() {
		return drawBox;
	}

	/**
	 * @param drawBox - the drawBox to set
	 */
	public void setDrawBox(Boolean drawBox) {
		this.drawBox = drawBox;
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {		
	}

	@Override
	protected void handleUnselect() {		
	}

	@Override
	public void handleClick() {
	}

}
