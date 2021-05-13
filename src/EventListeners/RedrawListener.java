package EventListeners;

import GUIElements.HTMLDocument;

/**
 * Interface that implements the redraw method for the EventListener class.
 */
public interface RedrawListener extends EventListener {

	void redraw(HTMLDocument HTMLDocument, String path, String code);
	
}
