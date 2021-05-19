package GUIElements;

/**
 * Extension for pane that asks of its children to implement the copy() method.
 *
 */
public abstract class LeafPane extends Pane {

	public LeafPane(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public abstract LeafPane copy() ;

	
}
