package EventListeners;

/**
 * Interface that implements the elementChanged, elementChangedAndReset and the elementIncreased methods for the EventListener class.
 */
public interface AdjustmentListener extends EventListener {
	//TODO comment
	/**
	 * Notifies the listener that the element has changed.
	 * @param viewableWidth
	 * @param totalWidth
	 * @param viewableHeight
	 * @param totalHeight
	 */
	public void elementChanged(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);

	/**
	 * Notifies the listener that the element has changed. Used for new scrollable.
	 * @param viewableWidth
	 * @param totalWidth
	 * @param viewableHeight
	 * @param totalHeight
	 */
	public void elementChangedAndReset(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);
	
	/**
	 * 
	 * @param viewableWidth
	 * @param totalWidth
	 * @param viewableHeight
	 * @param totalHeight
	 */
	public void elementIncreased(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);
}
