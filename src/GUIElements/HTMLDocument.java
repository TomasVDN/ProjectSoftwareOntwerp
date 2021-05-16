package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import EventListeners.ChangeSearchBarURLListener;
import EventListeners.RedrawListener;

public class HTMLDocument extends Pane {
	
	private String url;
	private String HTMLCode;
	private boolean isActiveHTMLDocument;
	private List<RedrawListener > listeners = new ArrayList<RedrawListener>();
	private List<ChangeSearchBarURLListener> listenersSearchBar = new ArrayList<ChangeSearchBarURLListener>();
	
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
	
	public void setActive(boolean active) {
		this.isActiveHTMLDocument = active;
		if (active) {
			listenersSearchBar.forEach(listener -> listener.changeSearchBarURL(url));
		}
	}
	
	public HTMLDocument copy() {
		HTMLDocument copy = new HTMLDocument(getX(), getY(), getWidth(), getHeight(), getUrl(), getHTMLCode());
		listeners.forEach(listener -> copy.addRedrawListener(listener));
		listenersSearchBar.forEach(listener -> copy.addChangeSearchBarURLListener(listener));
		return copy;
	}
	
	public void redraw() {
		listeners.forEach(listener -> listener.redraw(this, getUrl(), getHTMLCode()));
	}
	
	@Override
	public void resetAllElements(ArrayList<GUIElement> guiList, String path, String code) {
		super.resetAllElements(guiList, path, code);
		this.setUrl(path);
		this.setHTMLCode(code);
	}
	
	public void addRedrawListener(RedrawListener listener) {
		if(listener!=null) {
			this.listeners.add(listener);
		}
	}

	public void removeRedrawListener(RedrawListener listener) {
		this.listeners.remove(listener);
	}

	public List<RedrawListener> getListeners() {
		return listeners;
	}
	
	public void addChangeSearchBarURLListener(ChangeSearchBarURLListener listener) {
		if(listener!=null) {
			this.listenersSearchBar.add(listener);
		}
	}

	public void removeChangeSearchBarURLListener(ChangeSearchBarURLListener listener) {
		this.listenersSearchBar.remove(listener);
	}

	public List<ChangeSearchBarURLListener> getChangeSearchBarURLListeners() {
		return listenersSearchBar;
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
	
	

	
}