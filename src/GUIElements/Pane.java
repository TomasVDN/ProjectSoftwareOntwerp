package GUIElements;

/**
 * Superclass for HTMLDocument and SplitHTMLDocument. It defines the needed methods for the tree hierarchy necessary for the splitting
 * and deleting of panes. Our implementation works on the basis of a tree structure.
 *
 */
public abstract class Pane extends Container {

	public Pane(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public abstract HTMLDocument getActiveHTMLDocument();
	public abstract HTMLDocument changeActiveHTMLDocument(int x, int y);
	public abstract void resetActiveHTMLDocument();
	public abstract Pane splitActiveHTMLDocumentHorizontal();
	public abstract Pane splitActiveHTMLDocumentVertical();
	public abstract Pane deleteActiveHTMLDocument();
	
	public abstract void updateRightClosestChildWidth(int newXPos, int newWidth);
	public abstract void updateLeftClosestChildWidth(int newXPos, int newWidth);
	public abstract void updateRightClosestChildHeight(int newYPos, int newHeight);
	public abstract void updateLeftClosestChildHeight(int newYPos, int newHeight);
	protected abstract void updateAllBars();
}