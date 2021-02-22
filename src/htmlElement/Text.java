package htmlElement;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;

public class Text extends GUIElement{
	
	private String text;
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);

	public Text(int x, int y, int w, int h, String text, MyCanvasWindow window){
		super(x, y, w, h, window);
		this.setText(text);
	}
	
	public Text(int x, int y, int w, int h, String text, Font font, MyCanvasWindow window){
		super(x, y, w, h, window);
		this.setText(text);
		this.font = font; 
	}
	
	/**
	 * automatically calculate the needed heigth and width to construct a Text object
	 * @param string
	 * @param x
	 * @param y
	 * @param metrics
	 * @return
	 */
	public static Text constructText(String string,int x, int y, FontMetrics metrics, MyCanvasWindow window) {
	    return new Text(x, y, 0, 5, string, new Font(Font.DIALOG, Font.PLAIN, 20), window);
	}
	
	/**
	 * automatically calculate the needed heigth and width to construct a Text object
	 * @param string
	 * @param x
	 * @param y
	 * @param metrics
	 * @return
	 */
	public static Text constructText(String string,int x, int y, int h, MyCanvasWindow window) {
	    //int textWidth = metrics.stringWidth(string);
	    //int textHeigth = metrics.getHeight();
	    return new Text(x, y, 0, h, string, new Font(Font.DIALOG, Font.PLAIN, h), window);
	}
	
	
	/**
	 * Sets the value text of this class
	 * 
	 * @param text - new value of this.text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public Font getFont() {
		return this.font;
	}
	
	/**
	 * Sets the value font of this class
	 * 
	 * @param newFont - new value of this.font
	 */
	public void setFont(Font newFont) {
		this.font = newFont;
	}
	
	
	@Override
	public void paint(Graphics g) {
		this.paintText(g);
	}
	
	public void paintText(Graphics g) {
		g.setFont(this.getFont());
		g.drawString(getText(), getLeftX(), getLowerY());
	}

}
