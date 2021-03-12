package GUIElements;

import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import events.ClickHyperlinkEvent;
import events.Event;
import events.EventReader;

public class Hyperlink extends Button {

	//Map for setting the underline in the font and Variable for the url
    private Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	private String url;
	
	/**
	 * Constructor for the hyperlink class.
	 * @param x - x coordinate of the hyperlink
	 * @param y - y coordinate
	 * @param text - Text element of the hyperlink
	 * @param url - URL to which the hyperlink redirects
	 */
	public Hyperlink(int x, int y, Text text, String url) {
		super(x, y, text.getWidth(), text.getHeight(), text, false, Color.BLUE);
		this.setUrl(url);
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = text.getFont().deriveFont(fontAttributes);
		text.setFont(boldUnderline);
	}
	
	/**
	 * If clicked, send a runUrlEvent
	 */
	@Override
	public void handleClick() {
		Event event = new ClickHyperlinkEvent(this.getUrl());
		EventReader e = EventReader.getInstance();
		e.readEvent(event);
	}

	/**
	 * @return the width (dependent on width of this.Text)
	 */
	@Override
	public int getWidth() {
		return this.getText().getWidth();
	}

	/**
	 * @return the height (dependent on height of this.Text)
	 */
	@Override
	public int getHeight() {
		return this.getText().getHeight();
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
		this.url = url;
	}
}