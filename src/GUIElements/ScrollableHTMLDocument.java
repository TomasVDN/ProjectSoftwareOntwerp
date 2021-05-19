package GUIElements;

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
	 * @param x
	 * @param y
	 * @param html
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
	 * Initializes the vertival Scrollbar.
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

	
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		return this.htmlDocument.getActiveHTMLDocument();
	}

	@Override
	public HTMLDocument changeActiveHTMLDocument(int x, int y) {
		return this.htmlDocument.changeActiveHTMLDocument(x-this.getX(), y-this.getY());
	}

	@Override
	public void resetActiveHTMLDocument() {
		this.htmlDocument.setActive(false);
	}
	
	@Override	
	public void setActive(boolean active) {
		this.htmlDocument.setActive(active);
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
	public void updateRightClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}

	@Override
	public void updateLeftClosestChildWidth(int newXPos, int newWidth) {
		setX(newXPos);
		setWidth(newWidth);
	}

	@Override
	public void updateRightClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}

	@Override
	public void updateLeftClosestChildHeight(int newYPos, int newHeight) {
		setY(newYPos);
		setHeight(newHeight);
	}
	
	public HTMLDocument getHtmlDocument() {
		return htmlDocument;
	}

	public void setHtmlDocument(HTMLDocument htmlDocument) {
		this.htmlDocument = htmlDocument;
	}

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
	 * Update the width of the scrollbar to keep it visible.
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
	 * Update the height of the scrollbar to keep it visible.
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

}