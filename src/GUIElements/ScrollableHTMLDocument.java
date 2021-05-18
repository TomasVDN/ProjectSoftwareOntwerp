package GUIElements;

public class ScrollableHTMLDocument extends Pane {
	
	private HTMLDocument htmlDocument;


	public ScrollableHTMLDocument(int x,int y,HTMLDocument html) {
		super(x,y,html.getWidth()+10,html.getHeight()+10);
		this.setHtmlDocument(html);
		ScrollBar scrollBarHorizontal = new ScrollBar(Direction.HORIZONTAL, x, html.getHeight(), html.getWidth(), 10, html.getWidth(), html.maxCoordinateOfElements());
		html.addAdjustListener(scrollBarHorizontal);
		scrollBarHorizontal.addScrollBarListener(html);
		ScrollBar scrollBarVertical = new ScrollBar(Direction.VERTICAL, html.getWidth(), 0, 10,html.getHeight(), html.getHeight(), html.maxYCoordinatOfElements());
		html.addAdjustListener(scrollBarVertical);
		scrollBarVertical.addScrollBarListener(html);
		this.addElement(htmlDocument);
		this.addElement(scrollBarVertical);
		this.addElement(scrollBarHorizontal);
	}
	
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		return this.htmlDocument.getActiveHTMLDocument();
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		this.htmlDocument.setActive(true);
	}

	@Override
	public void resetActiveHTMLDocument() {
		this.htmlDocument.setActive(false);
	}
	
	@Override	public void setActive(boolean active) {
		this.htmlDocument.setActive(active);
	}
	

	@Override
	public Pane splitActiveHTMLDocumentVertical() {
		return new SplitHTMLDocument(this.htmlDocument, Direction.VERTICAL);
	}
	
	@Override
	public Pane splitActiveHTMLDocumentHorizontal() {
		return new SplitHTMLDocument(this.htmlDocument, Direction.HORIZONTAL);
	}

	@Override
	public Pane deleteActiveHTMLDocument() {
		return null;
	}

	@Override
	public HTMLDocument setHTMLDocumentActive(int x, int y) {
		return this.htmlDocument.setHTMLDocumentActive(x, y);
	}

	@Override
	public void updateRightClosestChildWidth(int newXPos, int newWidth) {
		htmlDocument.updateRightClosestChildWidth(newXPos, newWidth);
	}

	@Override
	public void updateLeftClosestChildWidth(int newXPos, int newWidth) {
		htmlDocument.updateLeftClosestChildWidth(newXPos, newWidth);
	}

	@Override
	public void updateRightClosestChildHeight(int newYPos, int newHeight) {
		htmlDocument.updateRightClosestChildHeight(newYPos, newHeight);
	}

	@Override
	public void updateLeftClosestChildHeight(int newYPos, int newHeight) {
		htmlDocument.updateLeftClosestChildHeight(newYPos, newHeight);
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


	

}