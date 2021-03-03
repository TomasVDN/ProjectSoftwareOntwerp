package GUIElements;

import java.awt.Color; //geen idee of deze import mag 
import java.awt.Font; 
import java.awt.FontMetrics; //geen idee of deze import mag 
import java.awt.font.TextAttribute;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import canvaswindow.MyCanvasWindow;

public class Button extends GUIElement{
	
	//text en button kleur variabelen
	private Text text;
    private Color buttonColor =  Color.BLUE;
    private Font font = null;
    private Boolean box = false;
    private int size = 0;
    
    /**
     * Constructor van de button
     * 
     * @param x - x coordinaat
     * @param y - y coordinaat
     * @param size - groote van de font
     * @param text - text van de button
     */
	public Button(int x, int y, int size, Text text, Boolean box){
		super(x, y, size, size);
		setWidth((3*text.getText().length()/4) * size);
		setHeight(size);	
		setText(text);
		if (box) {
			this.box = box;
		}
		this.setSize(size);
		this.font = new Font(Font.DIALOG, Font.PLAIN, size);
		
	}
	
	/**
	 * set the button size
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * get the button size
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	

	
	/**
	 * Set de text van de button
	 * @param text
	 */
	public void setText(Text text) {
		this.text = text;
	}
	
	/**
	 * Set the font of this button
	 * @param font
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	/**
	 * Geeft de text van de button
	 * @return text
	 */
	public Text getTextGUI() {
		return this.text;
	}
	
	/**
	 * HandlemouseEvent function for button - call this function in window when you want
	 * a mouse event
	 * @param id - type of event
	 * @param x - x coord of event
	 * @param y - y coord of event
	 * @param window - window connected to this event
	 */
	public void handleMouseEvent(int id, int x, int y, MyCanvasWindow window) {
		//TODO: geen idee of dit hier moet staan aangezien GUIElement dit al heeft
	}

	/**
	 * Deze functie tekent de button
	 * 
	 * g - Graphics
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		//Font font = new Font(Font.DIALOG, Font.PLAIN, 40);
		//kleur kiezen
		g.setColor(buttonColor);
		//zet de font
		g.setFont(this.font);
		//zorgt voor centreren van de tekst in de button
	    FontMetrics metrics = g.getFontMetrics(this.font);
	    int textX = getLeftX() + (getWidth() - metrics.stringWidth(getTextGUI().getText())) / 2;
	    int textY = getUpperY() + ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
	    //tekent de button en de tekst en zet dan de kleur terug naar de standaar kleur
	    g.drawString(getTextGUI().getText(), textX, textY);
	    if (this.box) {
	    	g.drawRoundRect(getLeftX(), getUpperY(), getWidth(), getHeight(), 5, 5);
	    }
		g.setColor(Color.BLACK);
	}
		
	@Override
	public void update(Graphics g) {
		setWidth((3*text.getText().length()/4) * size);
		setHeight(size);
	}

}
