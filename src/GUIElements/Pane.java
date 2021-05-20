package GUIElements;


/**
 * Superclass for HTMLDocument and SplitHTMLDocument. It defines the needed methods for the tree hierarchy necessary for the splitting
 * and deleting of panes. Our implementation works on the basis of a tree structure.
 *
 */
public abstract class Pane extends Container {

	/**
	 * Constructor for the Pane class.
	 * @param x - x coordinate of this Container
     * @param y - y coordinate of this Container
     * @param w - width of this Container
     * @param h - height of this Container
	 */
	public Pane(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/**
	 * Return the active HTMLDocument
	 * @return active HTMLDocument
	 */
	public abstract HTMLDocument getActiveHTMLDocument();
	
	/**
	 * Changes the activeHTLDocument to the HTMLDocument with (x,y) in it's bounds.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @return the new activeHTMLDocument
	 */
	public abstract HTMLDocument changeActiveHTMLDocument(int x, int y);
	
	/**
	 * Reset the activeHTMLDocument: all splitDocument point to the left.
	 */
	public abstract void resetActiveHTMLDocument();
	
	/**
	 * Split the activeHTMLDocument horizontally.
	 * @return SplitHTMLDocument with update
	 */
	public abstract Pane splitActiveHTMLDocumentHorizontal();
	
	/**
	 * Split the activeHTMLDocument vertically.
	 * @return SplitHTMLDocument with update
	 */
	public abstract Pane splitActiveHTMLDocumentVertical();
	
	/**
	 * Delete the activeHTMLDocument.
	 * @return a Pane if not a HTMLDocument, null otherwise.
	 */
	public abstract Pane deleteActiveHTMLDocument();
	
	/**
	 * Updates the most right child.
	 * @param newXPos - the new x position
	 * @param newWidth - the new width
	 */
	public abstract void updateRightClosestChildWidth(int newXPos, int newWidth);
	
	/**
	 * Updates the most left child.
	 * @param newXPos - the new x position
	 * @param newWidth - the new width
	 */
	public abstract void updateLeftClosestChildWidth(int newXPos, int newWidth);
	
	/**
	 * Updates the most right child.
	 * @param newYPos - the new y position
	 * @param newHeight - the new height
	 */
	public abstract void updateRightClosestChildHeight(int newYPos, int newHeight);
	
	/**
	 * Updates the most left child.
	 * @param newYPos - the new y position
	 * @param newHeight - the new height
	 */
	public abstract void updateLeftClosestChildHeight(int newYPos, int newHeight);
	
	/**
	 * Returns the width of the left most pane.
	 * @return width
	 */
	public abstract int getLeftPanelWidth();
	
	/**
	 * Returns the height of the left most pane.
	 * @return height
	 */
	public abstract int getLeftPanelHeight();
	
	/**
	 * Returns the width of the right most pane.
	 * @return width
	 */
	public abstract int getRightPanelWidth();
	
	/**
	 * Returns the height of the right most pane.
	 * @return height
	 */
	public abstract int getRightPanelHeight();
	
	/**
	 * Updates all separator bars contained within panes.
	 */
	protected abstract void updateAllBars();
}