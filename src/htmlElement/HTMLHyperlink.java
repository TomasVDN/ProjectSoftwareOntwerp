package htmlElement;

import GUIElements.Hyperlink;
import events.EventReader;


public class HTMLHyperlink extends ContentSpan{

	private String url;
	private HTMLText text;
	
	/**
	 * Constructor of the HTMLHyperlink class.
	 * @param url - url of this class
	 * @param text - text of this class
	 */
	public HTMLHyperlink(String url, HTMLText text) {
		if (url == null) {
			throw new IllegalArgumentException("The URL can't be null in the constructor of HTMLHyperlink.");
		}
		if (text == null) {
			throw new IllegalArgumentException("The text can't be null in the constructor of HTMLHyperlink.");
		}
		this.url = url;
		this.text = text;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @return the text
	 */
	public HTMLText getText() {
		return text;
	}

	@Override
	public String toString() {
		return "HYPER: (" + url + "," + text.toString() + ")";
	}

	@Override
	public Hyperlink transformToGUI(int x, int y, int width, int height, EventReader eventReader) {
		return new Hyperlink(x, y, this.getText().transformToGUI(x, y, width, height, eventReader), this.getUrl(), eventReader);
	}

}
