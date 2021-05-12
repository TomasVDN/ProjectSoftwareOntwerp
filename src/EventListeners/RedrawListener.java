package EventListeners;

import GUIElements.Container;

/**
 * Interface that implements the changeDialog method for the EventListener class.
 */
public interface RedrawListener extends EventListener {
	
	void runUrlWithContainer(Container container, String url);
	
}