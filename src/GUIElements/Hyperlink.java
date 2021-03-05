package GUIElements;

import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.Font; 
import java.util.HashMap;
import java.util.Map;

import canvaswindow.MyCanvasWindow;

public class Hyperlink extends Button {

	//Map for setting the underline in the font and Variable for the url
    private Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	private String url;
	
	/**
	 * Contructor for the hyperlink
	 * @param x
	 * @param y
	 * @param size
	 * @param text
	 * @param url
	 */
	public Hyperlink(int x, int y, int w, int h, int size, Text text, String url) {
		super(x, y, w, h, text, false);
		this.setUrl(url);
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = new Font("Serif",Font.BOLD, size).deriveFont(fontAttributes);
		this.setFont(boldUnderline);

	}
	
	/**
	 * Contructor for the hyperlink
	 * @param x
	 * @param y
	 * @param size
	 * @param text
	 * @param url
	 */
	public Hyperlink(int x, int y, Text text, String url) {
		super(x, y, text.getWidth(), text.getHeight(), text, false);
		this.setUrl(url);
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = text.getFont().deriveFont(fontAttributes);
		text.setFont(boldUnderline);
	}
	
	
	/**
	 * getter for the hyperlink url
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * setter for the hyperlink url
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}