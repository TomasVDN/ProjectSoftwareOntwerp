package htmlElement;

import GUIElements.GUIElement;

public abstract class ContentSpan {

	public ContentSpan() {
	}

	/**
	 * Returns the HTML/contentSpan to its string form
	 */
	@Override
	public abstract String toString();
	
	/**
	 * The method that gives back the correct GUI for given html
	 * @return
	 * returns GUIElement given by the htmlElement
	 */
	public abstract GUIElement transformToGUI(int x, int y, int width, int height);
}
