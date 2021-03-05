package htmlElement;

import java.awt.Graphics;

import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import utils.FontMetricsHandle;

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
	public Hyperlink transformToGUI(int width, int height, int y, int x,FontMetricsHandle f) {
		return new Hyperlink(x,y,this.getText().transformToGUI(width, height, y, x,f),this.getUrl());
	}

}
