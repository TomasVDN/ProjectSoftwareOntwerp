package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import EventListeners.FormListener;
import EventListeners.ReloadListener;
import EventListeners.ScrollBarListener;

public class HTMLDocument extends rootPane implements ScrollBarListener {
	
	private String url;
	private String HTMLCode;
	private boolean isActiveHTMLDocument;
	protected List<ReloadListener > listenerReload = new ArrayList<ReloadListener>();
	
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
	public HTMLDocument getActiveHTMLDocument() {
		return this;
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		this.setActive(true);
	}

	@Override
	public void resetActiveHTMLDocument() {
		this.setActive(false);
	}

	@Override
	public Pane splitActiveHTMLDocumentVertical() {
		return new SplitHTMLDocument(this, Direction.VERTICAL);
	}
	
	@Override
	public Pane splitActiveHTMLDocumentHorizontal() {
		return new SplitHTMLDocument(this, Direction.HORIZONTAL);
	}

	@Override
	public Pane deleteActiveHTMLDocument() {
		return null;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (isActiveHTMLDocument) {
			g.setColor(Color.ORANGE);
			g.drawRect(super.getX(),super.getY(), super.getWidth()-1, super.getHeight()-1);
		}		
	}
	
	@Override
	public void setActive(boolean active) {
		this.isActiveHTMLDocument = active;
	}
	
	@Override
	public HTMLDocument copy() {
		HTMLDocument copy = new HTMLDocument(getX(), getY(), getWidth(), getHeight(), getUrl(), getHTMLCode());
		copy.listenerReload = this.listenerReload;//copies the listener
		copy.loadPage();
		return copy;
	}
	
	@Override
	public void updateRightClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}
	
	@Override
	public void updateRightClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}

	@Override
	public void updateLeftClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}

	@Override
	public void updateLeftClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}

	@Override
	protected void updateAllBars() {

	}
	
	public void loadPage() {
		this.listenerReload.forEach(l -> l.loadHTML(this,this.getUrl(), this.getHTMLCode()));
	}
	
	public void loadHTML(ArrayList<GUIElement> guiList, String path, String code) {
		super.resetAllElements(guiList);
		this.setUrl(path);
		this.setHTMLCode(code);
}

	@Override
	public HTMLDocument setHTMLDocumentActive(int x, int y) {
		if(this.containsPoint(x, y)) {
			return this;
		}
		return null;
	}
	
	public void addReloadListener(ReloadListener listener) {
		if(listener!=null) {
			this.listenerReload.add(listener);
		}
	}

	public  void removeReloadListener(ReloadListener listener) {
		this.listenerReload.remove(listener);
	}
}
	
