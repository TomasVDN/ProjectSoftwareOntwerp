package htmlElement;

import java.awt.Color;  //geen idee of deze import mag 
import java.awt.Font;
import java.awt.FontMetrics; //geen idee of deze import mag 
import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;

public class Button extends GUIElement{
	
	//text en button kleur variabelen
	private String text;
    private Color buttonColor =  Color.BLUE;
	
    /**
     * Constructor van de button
     * 
     * @param x - x coordinaat
     * @param y - y coordinaat
     * @param size - groote van de font
     * @param text - text van de button
     */
	public Button(int x, int y, int size, String text){
		super(x, y, size, size);
		setWidth((3*text.length()/4) * 40);
		setHeight(size);	
		setText(text);
	}
	
	public Button(int x, int y, int width,int heigth, String text){
		super(x, y, width, heigth);	
		setText(text);
	}
	
	/**
	 * Set de text van de button
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Geeft de text van de button
	 * @return text
	 */
	public String getText() {
		return this.text;
	}


	/**
	 * Deze functie tekent de button
	 * 
	 * g - Graphics
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		Font font = new Font(Font.DIALOG, Font.PLAIN, 40);
		//kleur kiezen
		g.setColor(buttonColor);
		//zet de font
		g.setFont(font);
		//zorgt voor centreren van de tekst in de button
	    FontMetrics metrics = g.getFontMetrics(font);
	    int textX = getLeftX() + (getWidth() - metrics.stringWidth(getText())) / 2;
	    int textY = getLowerY() + ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
	    //tekent de button en de tekst en zet dan de kleur terug naar de standaar kleur
	    g.drawString(getText(), textX, textY);
		g.drawRoundRect(getLeftX(), getLowerY(), getWidth(), getHeight(), 5, 5);
		g.setColor(Color.BLACK);
	}

}
