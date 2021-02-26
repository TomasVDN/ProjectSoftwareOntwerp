package htmlElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import canvaswindow.MyCanvasWindow;

public class Text extends GUIElement{
	
	private String text;
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	private String url;
	private boolean isHyperlink = false;

	public Text(int x, int y, int w, int h, String text){
		super(x, y, w, h);
		this.setText(text);
	}
	
	public Text(int x, int y, int w, int h, String text, Font font){
		super(x, y, w, h);
		this.setText(text);
		this.font = font; 
	}
	
	public Text(int x, int y, int w, int h, String text, String url){
		super(x, y, w, h);
		this.setText(text);
		this.setHyperlink(true);
	}
	
	
	
	/**
	 * automatically calculate the needed heigth and width to construct a Text object
	 * @param string
	 * @param x
	 * @param y
	 * @param metrics
	 * @return
	 */
	public static Text constructText(String string,int x, int y, FontMetrics metrics) {
	    return new Text(x, y, 0, 5, string, new Font(Font.DIALOG, Font.PLAIN, 20));
	}
	
	/**
	 * automatically calculate the needed heigth and width to construct a Text object
	 * @param string
	 * @param x
	 * @param y
	 * @param metrics
	 * @return
	 */
	public static Text constructText(String string,int x, int y, int h) {
	    //int textWidth = metrics.stringWidth(string);
	    //int textHeigth = metrics.getHeight();
	    return new Text(x, y, 0, h, string, new Font(Font.DIALOG, Font.PLAIN, h));
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
	
	public void clearText() {
		this.setText("");
	}
	
	
	@Override
	public void paint(Graphics g) {
		//TODO William, dit is kleur hyperlinks
		if (this.isHyperlink()) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.black);
		}
		g.setFont(this.getFont());
		g.drawString(getText(), getLeftX(), getLowerY());
	}
	
	@Override
	public void update(Graphics g) {
		Font textFont= this.getFont();
		FontMetrics fontMetrics = g.getFontMetrics(textFont);
		this.setHeight(fontMetrics.getHeight());
		this.setWidth(fontMetrics.stringWidth(this.getText()));
	}

	
	//TODO William, dit zijn men shit functies voor hyperlink
	/**
	 * @return the isHyperlink
	 */
	public boolean isHyperlink() {
		return isHyperlink;
	}

	/**
	 * @param isHyperlink the isHyperlink to set
	 */
	public void setHyperlink(boolean isHyperlink) {
		this.isHyperlink = isHyperlink;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void handleMouseEvent(int id, int x, int y, MyCanvasWindow window) {
		if (id == MouseEvent.MOUSE_CLICKED) {
			if (this.checkCoordinates(x, y)) {
				window.readFile(this.getUrl());
			}
							
		}
	}
}
