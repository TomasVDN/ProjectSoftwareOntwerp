package GUIElements;

import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import EventListeners.HyperLinkListener;

/**
 * Class that implements a Hyperlink (clickable text).
 *
 */
public class Hyperlink extends Text {

	//Map for setting the underline in the font and Variable for the url
    private Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	private String url;

	private ArrayList<HyperLinkListener> hyperlinkListeners = new ArrayList<HyperLinkListener>();

	
	/**
	 * Constructor for the hyperlink class.
	 * @param x - x coordinate of the hyperlink
	 * @param y - y coordinate
	 * @param text - Text element of the hyperlink
	 * @param url - URL to which the hyperlink redirects
	 */
	public Hyperlink(int x, int y, Text text, String url) {
		super(x, y,text);
		this.setColor(Color.blue);
		this.setUrl(url);
		this.initFont();
	}
	
	/**
	 * Initializes the font to use for the Hyperlink
	 */
	private void initFont() {
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = this.getFont().deriveFont(fontAttributes);
		this.setFont(boldUnderline);
	}

	
	/**
	 * @return this.url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url - the new value of this.url
	 */
	public void setUrl(String url) {
		if(url==null) {
			throw new IllegalArgumentException("not a valid url given in hyperlink");
		}
		this.url = url;
	}

	/**
	 * Add the given HyperLinkListener to the list of HyperLinkListeners
	 * @param listener
	 */
	public void addHyperLinkListener(HyperLinkListener listener) {
		this.hyperlinkListeners.add(listener);
	}

	/**
	 * Removes the given HyperLinkListener to the list of HyperLinkListeners
	 * @param listener
	 */
	public void removeHyperLinkListener(HyperLinkListener listener) {
		this.hyperlinkListeners.remove(listener);
	}
	
	/**
	 * @return this.hyperlinklisteners
	 */
	protected ArrayList<HyperLinkListener> getHyperListeners() {
		return hyperlinkListeners;
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {}

	@Override
	public void handleUnselect() {}
	
	/**
	 * Handles left click on hyperlink.
	 */
	@Override
	public void handleClick() {
		hyperlinkListeners.forEach(listener -> listener.runUrlAttribute(getUrl()));
	}

}