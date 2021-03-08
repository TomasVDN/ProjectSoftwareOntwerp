package GUIElements;

import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import events.Event;
import events.EventReader;
import events.RunUrlEvent;

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
	
	
	public int getWidth() {
		return this.getText().getWidth();
	}

	public int getHeight() {
		return this.getText().getHeight();
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
	
	@Override
	public void handleClick() {
		this.runUrlEvent();
	}
	
	private void runUrlEvent() {
		//this.setActive(false);// gewone textbox gaat inactief worden bij enter
		Event event = new RunUrlEvent(this.getUrl());
		EventReader e = EventReader.getInstance();
		e.readEvent(event);
	}
	
	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		this.getText().paint(g, xContainer, yContainer,Color.BLUE);
	}
}