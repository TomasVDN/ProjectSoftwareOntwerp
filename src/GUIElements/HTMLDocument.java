package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import EventListeners.ReloadListener;
/**
 * Container type class used as a pane. It keeps track of the loaded URL and the loaded HTML code. 
 * It also implements everything needed for splitting.
 */
public class HTMLDocument extends LeafPane { 
	
	private String url;
	private String HTMLCode;
	private List<ReloadListener > reloadListeners = new ArrayList<ReloadListener>();
	
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
	 * Return whether the active HTMLDocument.
	 */
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		if(this.isActive()) {
			return this;
		}
		return null;
	}

	/**
	 * Sets this HTMLDocument to active.
	 */
	@Override
	public HTMLDocument changeActiveHTMLDocument(int x, int y) {
		if(this.containsPoint(x, y)) {
			this.setActive(true);
			return this;
		}
		return null;
	}

	/**
	 * Sets this HTMLDocument to unactive.
	 */
	@Override
	public void resetActiveHTMLDocument() {
		this.setActive(false);
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
		if (isActive()) {
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
		copy.reloadListeners = this.reloadListeners;//copies the listener
		copy.loadPage();
		return copy;
	}
	
	/**
	 * Update the x and width. This is used to update when moving/splitting HTMLDocuments.
	 * @param newXPos
	 * @param newWidth
	 */
	@Override
	public void updateRightClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}
	
	/**
	 * Update the y and height. This is used to update when moving/splitting HTMLDocuments.
	 * @param newYPos
	 * @param newHeight
	 *//**
	 * Update the y and height. This is used to update when moving/splitting HTMLDocuments.
	 * @param newYPos
	 * @param newHeight
	 */
	@Override
	public void updateRightClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}

	/**
	 * Update the x and width. This is used to update when moving/splitting HTMLDocuments.
	 * @param newXPos
	 * @param newWidth
	 */
	@Override
	public void updateLeftClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}

	/**
	 * Update the y and height. This is used to update when moving/splitting HTMLDocuments.
	 * @param newYPos
	 * @param newHeight
	 */
	@Override
	public void updateLeftClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}

	@Override
	protected void updateAllBars() {
	}
	
	/**
	 * This will remove the old GUIElements, and add new ones made from this.HTMLCode
	 */
	public void loadPage() {
		this.reloadListeners.forEach(l -> l.loadHTML(this,this.getUrl(), this.getHTMLCode()));
	}
	
	/**
	 * Adds the GUIElements from guiList to this HTMLDocument.
	 * @param guiList - list with the GUIElements to add
	 * @param path - URL from the page the GUIElements come from
	 * @param code - HTML code from the page the GUIElements come from
	 */
	public void loadHTML(ArrayList<GUIElement> guiList, String path, String code) {
		super.resetAllElements(guiList);
		this.setUrl(path);
		this.setHTMLCode(code);
	}

	/**
	 * Add the given ReloadListener to the list of ReloadListeners
	 * @param listener
	 */
	public void addReloadListener(ReloadListener listener) {
		if(listener!=null) {
			this.reloadListeners.add(listener);
		}
	}

	/**
	 * Removes the given ReloadListener form the list of ReloadListeners
	 */
	public  void removeReloadListener(ReloadListener listener) {
		this.reloadListeners.remove(listener);
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
		if(url==null) {
			throw new IllegalArgumentException("Url can't be null");
		}
		this.url = url;
	}

	/**
	 * @return the hTMLCode
	 */
	public String getHTMLCode() {
		return HTMLCode;
	}

	/**
	 * @param HTMLCode the HTMLCode to set
	 */
	public void setHTMLCode(String HTMLCode) {
		if(HTMLCode==null) {
			throw new IllegalArgumentException("Not a valid HTMLCode");
		}
		this.HTMLCode = HTMLCode;
	}

	@Override
	public int getLeftPanelWidth() {
		return getWidth();
	}

	@Override
	public int getLeftPanelHeight() {
		return getHeight();
	}

	@Override
	public int getRightPanelWidth() {
		return getWidth();
	}

	@Override
	public int getRightPanelHeight() {
		return getHeight();
	}
	
}
	
