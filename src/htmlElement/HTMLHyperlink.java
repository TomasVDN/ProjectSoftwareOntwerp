package htmlElement;

import java.awt.Graphics;

import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import facades.EventReader;


public class HTMLHyperlink extends ContentSpan{

	private String url;
	private HTMLText text;
	public HTMLHyperlink(String url, HTMLText text) {
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
	public Hyperlink transformToGUI(int x, int y, int width, int height, EventReader e) {
		return new Hyperlink(x, y, e, this.getText().transformToGUI(x, y, width, height, e), this.getUrl());
	}

}
