package EventListeners;

import GUIElements.Direction;

/**
 * Interface that implements the scrollbarMoved method for the EventListener class.
 */
public interface ScrollBarListener {
	
	/**
	 * Notifies the listener that the scrollbar has been moved.
	 * @param ratio - amount of displacement
	 * @param direction - direction of displacement
	 */
	public void scrollBarMoved(double ratio,Direction direction);

}
