package htmlElement;

import java.awt.Graphics;

import GUIElements.GUIElement;

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
	public GUIElement transformToGUI(int width, int heigth, int y, int x) {
		// TODO Auto-generated method stub
		return new HyperLink(x,y,10,this.getText().transformToGUI(width, heigth, y, x),this.getUrl());
	}

}
