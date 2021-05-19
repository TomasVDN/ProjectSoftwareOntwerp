package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import EventListeners.ReloadListener;
import EventListeners.ScrollBarListener;

/**
 * Container type class used as a pane. It keeps track of the loaded URL and the loaded HTML code. 
 * It also implements everything needed for splitting.
 */
public class HTMLDocument extends LeafPane implements ScrollBarListener {
	
	private String url;
	private String HTMLCode;
	private boolean isActiveHTMLDocument;
	protected List<ReloadListener > listenerReload = new ArrayList<ReloadListener>();
	
	/**
	 * Constructor of the HTMLDocument class. It extends the Pane class.
	 * @param x - x coordinate of this HTMLDocument
     * @param y - y coordinate of this HTMLDocument
     * @param w - width of this HTMLDocument
     * @param h - height of this HTMLDocument
     * @param url - URL of the loaded page
     * @param HTMLCode - string which contains the HTML code of the loaded page
	 */
	public HTMLDocument(int x, int y, int w, int h, String url, String HTMLCode) {
		super(x, y, w, h);
		this.setUrl(url);
		this.setHTMLCode(HTMLCode);
	}

	/**
	 * Return whether this HTMLDocument is active.
	 */
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		return this;
	}

	/**
	 * Sets this HTMLDocument to active.
	 */
	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		this.setActiveUnselect(true);
	}

	/**
	 * Sets this HTMLDocument to unactive.
	 */
	@Override
	public void resetActiveHTMLDocument() {
		this.setActiveUnselect(false);
	}

	/**
	 * Split this HTMLDocument vertically by creating a new SplitHTMLDocument.
	 * @return new SplitHTMLDocument(Direction.vertical)
	 */
	@Override
	public Pane splitActiveHTMLDocumentVertical() {
		return new SplitHTMLDocument(this, Direction.VERTICAL);
	}
	
	/**
	 * Split this HTMLDocument horizontally by creating a new SplitHTMLDocument.
	 * @return new SplitHTMLDocument(Direction.horizontal)
	 */
	@Override
	public Pane splitActiveHTMLDocumentHorizontal() {
		return new SplitHTMLDocument(this, Direction.HORIZONTAL);
	}

	/**
	 * Deletes this HTMLDocument in the tree structure of Panes.
	 */
	@Override
	public Pane deleteActiveHTMLDocument() {
		return null;
	}
	
	/**
	 * Paints this HTMLDocument. Uses the Paint of the Super class, except that it also paints an orange border if it is active.
	 * @param g -graphics to paint with
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (isActiveHTMLDocument) {
			g.setColor(Color.ORANGE);
			g.drawRect(super.getX(),super.getY(), super.getWidth()-1, super.getHeight()-1);
		}		
	}
	
	/**
	 * Makes a copy of the HTMLDocument
	 * @return new HTMLDocument identical to this one
	 */
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
	 * @param hTMLCode the HTMLCode to set
	 */
	public void setHTMLCode(String hTMLCode) {
		HTMLCode = hTMLCode;
	}
	
	/**
	 * Updates the isActive boolean to the given value.
	 * @param active - the new value of this.isActive
	 */
	@Override
	public void setActiveUnselect(boolean active) {
		this.isActiveHTMLDocument = active;
	}
}
	
