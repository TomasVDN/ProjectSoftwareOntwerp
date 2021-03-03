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
	public Hyperlink(int x, int y, int size, Text text, String url) {
		super(x, y, size, text, false);
		this.setUrl(url);
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = new Font("Serif",Font.BOLD, size).deriveFont(fontAttributes);
		this.setFont(boldUnderline);

	}
	
	/**
	 * HandlemouseEvent function for hyperlink - this function is called when
	 * you click the hyperlink
	 * @param id - type of event
	 * @param x - x coord of event
	 * @param y - y coord of event
	 * @param window - window connected to this event
	 */
	public void handleMouseEvent(int id, int x, int y, MyCanvasWindow window) {
		if (id == MouseEvent.MOUSE_CLICKED) {
			if (this.checkCoordinates(x, y)) {
				window.readFile(this.getUrl());
			}
							
		}
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
