package EventListeners;

/**
 * Interface that implements the elementChanged, elementChangedAndReset and the elementIncreased methods for the EventListener class.
 */
public interface AdjustmentListener extends EventListener {

	/**
	 * Notifies the listener that the element has changed.
	 * @param viewableWidth
	 * @param totalWidth
	 * @param viewableHeight
	 * @param totalHeight
	 */
	public void elementChanged(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);

	/**
	 * Notifies the listener that the element has changed. The scrollbar will also be set to the beginning.
	 * @param viewableWidth
	 * @param totalWidth
	 * @param viewableHeight
	 * @param totalHeight
	 */
	public void elementChangedKeepAtBeginning(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);
	
	/**
	 * Notifies the listener that the element has changed. The scrollbar will also be set to the end.
	 * @param viewableWidth
	 * @param totalWidth
	 * @param viewableHeight
	 * @param totalHeight
	 */
	public void elementChangedKeepAtEnd(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);
}
