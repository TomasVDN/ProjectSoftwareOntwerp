package htmlElement;

import GUIElements.GUIElement;
import events.EventReader;

public abstract class ContentSpan {

	public ContentSpan() {
	}

	@Override
	public abstract String toString();
	
	/**
	 * The method that gives back the correct GUI for given html
	 * @param eventReader 
	 * @return
	 * returns GUIElement given by the htmlElement
	 */
	public abstract GUIElement transformToGUI(int x, int y, int width, int height, EventReader eventReader);
}
