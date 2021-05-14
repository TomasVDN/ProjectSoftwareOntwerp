package GUIElements;

public abstract class Pane extends Container {

	public Pane(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public abstract HTMLDocument getActiveHTMLDocument();
	public abstract void changeActiveHTMLDocument(int x, int y);
	public abstract void resetActiveHTMLDocument();
	public abstract Pane splitActiveHTMLDocumentHorizontal();
	public abstract Pane splitActiveHTMLDocumentVertical();
	public abstract Pane deleteActiveHTMLDocument();
	public abstract void updateWidth(int newXPos, int newWidth);
	public abstract void updateHeight(int newYPos, int newHeight);
}