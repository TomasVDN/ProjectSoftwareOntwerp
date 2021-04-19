package GUIElements;

import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import EventCreators.HyperLinkEventCreator;
import EventListeners.HyperLinkListener;
import events.EventReader;

public class Hyperlink extends Text implements HyperLinkEventCreator  {

	//Map for setting the underline in the font and Variable for the url
    private Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
	private String url;
	protected EventReader eventReader;

	private ArrayList<HyperLinkListener> eventListener = new ArrayList<HyperLinkListener>();

	
	/**
	 * Constructor for the hyperlink class.
	 * @param x - x coordinate of the hyperlink
	 * @param y - y coordinate
	 * @param text - Text element of the hyperlink
	 * @param url - URL to which the hyperlink redirects
	 */
	public Hyperlink(int x, int y, Text text, String url, EventReader eventReader) {
		super(x, y,text);
		this.setColor(Color.blue);
		this.setUrl(url);
		this.initFont();
		this.addHyperLinkListener(eventReader);
		initiateClickListeners();
	}
	
	protected void initiateClickListeners() {
		this.addSingleClickListener(() ->{
			System.out.println("CLICK OP HYPERLINK");
			for(HyperLinkListener listener: this.getHyperListeners()) {
				listener.handleHyperLinkClicked(this.getUrl());
			}
		});
	}
	
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

	@Override
	public void addHyperLinkListener(HyperLinkListener listener) {
		this.getHyperListeners().add(listener);
	}

	@Override
	public void removeHyperLinkListener(HyperLinkListener listener) {
		this.getHyperListeners().remove(listener);
	}
	

	protected ArrayList<HyperLinkListener> getHyperListeners() {
		return eventListener;
	}



	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void handleUnselect() {
		// TODO Auto-generated method stub
		
	}


	protected ArrayList<Runnable> clickListeners = new ArrayList<Runnable>();
	

	/**
	 * adds a listener for a singleClick action
	 * @param f: the listener to be added
	 */
	public void addSingleClickListener(Runnable f) {
		clickListeners.add(f);
	}
	
	
	@Override
	public void handleClick() {
		new ArrayList<>(clickListeners).stream().forEach(l -> l.run());
	}

}