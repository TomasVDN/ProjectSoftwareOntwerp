package GUIElements;

/**
 * Extension for pane that asks of its children to implement the copy() method.
 *
 */
public abstract class LeafPane extends Pane {

	/**
	 * Constructor of the leafPane class.
	 * @param x - x coordinate of this Container
     * @param y - y coordinate of this Container
     * @param w - width of this Container
     * @param h - height of this Container
	 */
	public LeafPane(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/**
	 * Returns a copy of this leafPane
	 * @return leafPane.copy
	 */
	public abstract LeafPane copy() ;

	
}
