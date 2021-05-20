package GUIElements;

import java.util.List;

/**
 * Class that implements the scrolling feature for the HTMLDocument. It contains a HTMLDocument and two scrollbars.
 * All uncommented methods are redirections to the HTMLDocument methods.
 */
public class ScrollableHTMLDocument extends LeafPane {
	
	private HTMLDocument htmlDocument;
	private ScrollBar scrollVertical;
	private ScrollBar scrollHorizontal;

	/**
	 * Constructor of the ScrollableHTMLDocument. It creates the two Scrollbars.
	 * @param x - the x position of the scrollableHTMLDocument
	 * @param y - the y position of the scrollableHTMLDocument
	 * @param html - the htmlDocument that needs the scrollbars
	 */
	public ScrollableHTMLDocument(int x, int y, HTMLDocument html) {
		super(x, y, html.getWidth() + 10, html.getHeight() + 10);
		this.setHtmlDocument(html);
		
		initializeHorizontalScrollbar(x, html);
		initializeVerticalScrollbar(html);
		
		this.addElement(htmlDocument);
	}

	/**
	 * Initializes the Horizontal Scrollbar.
	 * @param x
	 * @param html
	 */
	private void initializeHorizontalScrollbar(int x, HTMLDocument html) {
		ScrollBar scrollBarHorizontal = new ScrollBar(Direction.HORIZONTAL, x, html.getHeight(), html.getWidth(), 10, html.getWidth(), html.maxXCoordinateOfElements());
		html.addAdjustListener(scrollBarHorizontal);
		scrollBarHorizontal.addScrollBarListener(html);
		this.setScrollHorizontal(scrollBarHorizontal);
		this.addElement(scrollBarHorizontal);
	}

	/**
	 * Initializes the vertical Scrollbar.
	 * @param html
	 */
	private void initializeVerticalScrollbar(HTMLDocument html) {
		ScrollBar scrollBarVertical = new ScrollBar(Direction.VERTICAL, html.getWidth(), 0, 10,html.getHeight(), html.getHeight(), html.maxYCoordinatOfElements());
		
		html.addAdjustListener(scrollBarVertical);
		scrollBarVertical.addScrollBarListener(html);
		
		this.setScrollVertical(scrollBarVertical);
		this.addElement(scrollBarVertical);
	}
	
	/**
	 * Return the horizontal scrollbar of this Scrollable.
	 * @return this.scrollHorizontal
	 */
	public ScrollBar getScrollHorizontal() {
		return scrollHorizontal;
	}

	/**
	 * Sets the horizontal scrollbar to the given scrollbar.
	 * @param scrollHorizontal
	 */
	public void setScrollHorizontal(ScrollBar scrollHorizontal) {
		this.scrollHorizontal = scrollHorizontal;
	}
	
	/**
	 * Return the vertical scrollbar of this Scrollable.
	 * @return this.scrollHorizontal
	 */
	public ScrollBar getScrollVertical() {
		return scrollVertical;
	}

	/**
	 * Sets the horizontal vertical to the given scrollbar.
	 * @param scrollVertical
	 */
	public void setScrollVertical(ScrollBar scrollVertical) {
		this.scrollVertical = scrollVertical;
	}

	/**
	 * Calls the getActiveHtmlDocument of its child
	 */
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		return this.htmlDocument.getActiveHTMLDocument();
	}

	/**
	 * Calls the changeActiveHtmlDocument of its child, this function sets the pane active
	 * if the position is within its boundaries
	 */
	@Override
	public HTMLDocument changeActiveHTMLDocument(int x, int y) {
		this.htmlDocument.setActive(true);
		return this.htmlDocument;
	}

	/**
	 * Sets the child htmlDocument to non-active
	 */
	@Override
	public void resetActiveHTMLDocument() {
		this.htmlDocument.setActive(false);
	}
	
	/**
	 * Sets the child htmlDocument to active
	 */
	@Override	
	public void setActive(boolean active) {
		this.htmlDocument.setActive(active);
	}
	
	/**
	 * Splits this ScrollableHTMLDocument in half with a vertical seperatorBar between them.
	 * This Pane becomes the left side of the splitHtmlDocument, the right side becomes a copy
	 * wich is afterwards reloaded.
	 */
	@Override
	public Pane splitActiveHTMLDocumentVertical() {
		return new SplitHTMLDocument(this, Direction.VERTICAL);
	}
	
	/**
	 * Splits this ScrollableHTMLDocument in half with a horizontal seperatorBar between them.
	 * This Pane becomes the left side of the splitHtmlDocument, the right side becomes a copy
	 * wich is afterwards reloaded.
	 */
	@Override
	public Pane splitActiveHTMLDocumentHorizontal() {
		return new SplitHTMLDocument(this, Direction.HORIZONTAL);
	}

	/**
	 * Deletes the current HTMLDocument
	 */
	@Override
	public Pane deleteActiveHTMLDocument() {
		return null;
	}

	/**
	 * This node is the rigthClosest child, sets its new X position to the given position
	 * and the newWidth to the given width
	 */
	@Override
	public void updateRightClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}

	/**
	 * This node is the leftClosest child, sets its new X position to the given position
	 * and the newWidth to the given width
	 */
	@Override
	public void updateLeftClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}

	/**
	 * This node is the rigthClosest child, sets its new y position to the given position
	 * and the newWidth to the given height.
	 */
	@Override
	public void updateRightClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}

	/**
	 * This node is the leftClosest child, sets its new y position to the given position
	 * and the newWidth to the given height.
	 */
	@Override
	public void updateLeftClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}
	
	/**
	 * Returns the htmlDocument
	 */
	public HTMLDocument getHtmlDocument() {
		return htmlDocument;
	}
	
	/**
	 * Sets the htmlDocument to the given html
	 */
	private void setHtmlDocument(HTMLDocument htmlDocument) {
		this.htmlDocument = htmlDocument;
	}

	/**
	 * Calls the update all bars of its childClass, bec only splitHTMLDocuments have child
	 * classes this would probably do nothing.
	 */
	@Override
	protected void updateAllBars() {
		this.htmlDocument.updateAllBars();
	}

	/**
	 * Creates a copy of this scrollable HTMLDocument. For this, create a copy of the contained HTMLDocument, and make a new 
	 * ScrollableHTMLDocument with it.
	 */
	public ScrollableHTMLDocument copy() {
		HTMLDocument htmlCopy = this.getHtmlDocument().copy();
		return new ScrollableHTMLDocument(0, 0, htmlCopy);
	}

	/**
	 * Update the width. The size of the scrollBar is always 10 unless the width of the container
	 * is less then 10. 
	 */
	@Override
	public void setWidth(int width) {
		if(width>10) {
			this.getHtmlDocument().setWidth(width-10);
			if(this.getScrollHorizontal()!=null) {
				this.getScrollHorizontal().setWidth(this.getHtmlDocument().getWidth());
			}
			if(this.getScrollVertical()!=null) {
				this.getScrollVertical().setX(this.getHtmlDocument().getEndX());
			}
		}
		super.setWidth(width);
	}
	
	/**
	 * Update the height. The size of the scrollBar is always 10 unless the width of the container
	 * is less then 10. 
	 */
	@Override
	public void setHeight(int height) {
		if(height>10) {
			this.getHtmlDocument().setHeight(height-10);
			if(this.getScrollVertical()!=null) {
				this.getScrollVertical().setHeight(this.getHtmlDocument().getHeight());
			}
			if(this.getScrollHorizontal()!=null) {
				this.getScrollHorizontal().setY(this.getHtmlDocument().getEndY());
			}
		}
		super.setHeight(height);
	}

	@Override
	public int getLeftPanelWidth() {
		return this.htmlDocument.getLeftPanelWidth();
	}

	@Override
	public int getLeftPanelHeight() {
		return this.htmlDocument.getLeftPanelHeight();
	}

	@Override
	public int getRightPanelWidth() {
		return this.htmlDocument.getRightPanelWidth();
	}

	@Override
	public int getRightPanelHeight() {
		return this.htmlDocument.getRightPanelHeight();
	}

}