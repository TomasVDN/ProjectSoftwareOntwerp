package EventListeners;

import GUIElements.GUIElement;

public interface AdjustmentListener {
	
	public void elementChanged(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);

	public void elementChangedAndReset(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);
	
	public void elementIncreased(int viewableWidth,int totalWidth,int viewableHeight,int totalHeight);
}
