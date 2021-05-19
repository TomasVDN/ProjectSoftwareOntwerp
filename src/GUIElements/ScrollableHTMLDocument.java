package GUIElements;

public class ScrollableHTMLDocument extends LeafPane {
	
	private HTMLDocument htmlDocument;
	private ScrollBar scrollVertical;
	private ScrollBar scrollHorizontal;


	public ScrollableHTMLDocument(int x,int y,HTMLDocument html) {
		super(x,y,html.getWidth()+10,html.getHeight()+10);
		this.setHtmlDocument(html);
		ScrollBar scrollBarHorizontal = new ScrollBar(Direction.HORIZONTAL, x, html.getHeight(), html.getWidth(), 10, html.getWidth(), html.maxXCoordinateOfElements());
		html.addAdjustListener(scrollBarHorizontal);
		scrollBarHorizontal.addScrollBarListener(html);
		ScrollBar scrollBarVertical = new ScrollBar(Direction.VERTICAL, html.getWidth(), 0, 10,html.getHeight(), html.getHeight(), html.maxYCoordinatOfElements());
		html.addAdjustListener(scrollBarVertical);
		scrollBarVertical.addScrollBarListener(html);
		this.setScrollHorizontal(scrollBarHorizontal);
		this.setScrollVertical(scrollBarVertical);
		this.addElement(scrollBarVertical);
		this.addElement(scrollBarHorizontal);
		this.addElement(htmlDocument);
	}
	
	private ScrollBar getScrollHorizontal() {
		return scrollHorizontal;
	}

	public void setScrollHorizontal(ScrollBar scrollHorizontal) {
		this.scrollHorizontal = scrollHorizontal;
	}
	
	private ScrollBar getScrollVertical() {
		return scrollVertical;
	}

	public void setScrollVertical(ScrollBar scrollVertical) {
		this.scrollVertical = scrollVertical;
	}

	
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		return this.htmlDocument.getActiveHTMLDocument();
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		this.htmlDocument.setActiveUnselect(true);
	}

	@Override
	public void resetActiveHTMLDocument() {
		this.htmlDocument.setActiveUnselect(false);
	}
	
	@Override	
	public void setActiveUnselect(boolean active) {
		this.htmlDocument.setActiveUnselect(active);
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
	public HTMLDocument setHTMLDocumentActive(int x, int y) {
		return this.htmlDocument.setHTMLDocumentActive(x-this.getX(), y-this.getY());
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

	public ScrollableHTMLDocument copy() {
		HTMLDocument htmlCopy = this.getHtmlDocument().copy();
		return new ScrollableHTMLDocument(0, 0, htmlCopy);
	}

	/**
	 * Always make scrollbar visible
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
	 * Always make scrollbar visible
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