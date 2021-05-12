package GUIElements;

import java.awt.Color;
import java.awt.Graphics;

public class HTMLDocument extends Pane {
	
	private String url;
	private String HTMLCode;
	private boolean isActiveHTMLDocument;

	public HTMLDocument(int x, int y, int w, int h, String url, String HTMLCode) {
		super(x, y, w, h);
		this.setUrl(url);
		this.setHTMLCode(HTMLCode);
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the hTMLCode
	 */
	public String getHTMLCode() {
		return HTMLCode;
	}

	/**
	 * @param hTMLCode the hTMLCode to set
	 */
	public void setHTMLCode(String hTMLCode) {
		HTMLCode = hTMLCode;
	}

	@Override
	public Pane getActiveHTMLDocument() {
		return this;
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		this.isActiveHTMLDocument = true;
	}

	@Override
	public void resetActiveHTMLDocument() {
		this.isActiveHTMLDocument = false;
	}

	@Override
	public Pane splitActiveHTMLDocument() {
		return new SplitHTMLDocument(this);
	}

	@Override
	public Pane deleteActiveHTMLDocument() {
		return null;
	}

	@Override
	public void updateWidth(int width) {
		this.setWidth(width);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (isActiveHTMLDocument) {
			g.setColor(Color.ORANGE);
			g.drawRect(super.getX(),super.getY(), super.getWidth(), super.getHeight());
		}
		
	}
	
	public void setActive(boolean active) {
		this.isActiveHTMLDocument = active;
	}
	
}