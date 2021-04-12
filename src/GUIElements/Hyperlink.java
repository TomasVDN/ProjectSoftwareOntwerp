package GUIElements;

import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.HyperlinkListener;

import EventCreators.HyperLinkEventCreator;
import EventListeners.ActionListener;
import EventListeners.HyperLinkListener;
import events.ClickHyperlinkEvent;
import events.Event;
import events.EventReader;

public class Hyperlink extends Button implements HyperLinkEventCreator  {

	//Map for setting the underline in the font and Variable for the url
    private Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	private String url;
	private ArrayList<HyperLinkListener> eventListener = new ArrayList<HyperLinkListener>();
	
	/**
	 * Constructor for the hyperlink class.
	 * @param x - x coordinate of the hyperlink
	 * @param y - y coordinate
	 * @param text - Text element of the hyperlink
	 * @param url - URL to which the hyperlink redirects
	 */
	public Hyperlink(int x, int y, Text text, String url, EventReader eventReader) {
		super(x, y, text.getWidth(), text.getHeight(), text, false, Color.BLUE);
		this.setUrl(url);
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = text.getFont().deriveFont(fontAttributes);
		text.setFont(boldUnderline);
		this.addListener(eventReader);
		
		//creates the method when clicked on hyperlink
		this.addSingleClickListener(() ->{
			System.out.println("CLICK OP HYPERLINK");
			for(HyperLinkListener listener: this.getHyperListeners()) {
				listener.handleHyperLinkClicked(this.getUrl());
			}
		});
		//this.eventListener = eventReader;
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
		if(url==null) {
			throw new IllegalArgumentException("not a valid url given in hyperlink");
		}
		this.url = url;
	}

	@Override
	public void addListener(HyperLinkListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(HyperLinkListener listener) {
		// TODO Auto-generated method stub
		
	}
	

	private ArrayList<HyperLinkListener> getHyperListeners() {
		return eventListener;
	}
}