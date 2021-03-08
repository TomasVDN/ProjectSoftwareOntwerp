package htmlElement;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;
import events.EventReader;

public abstract class ContentSpan {

	public ContentSpan() {
		
	}

	public abstract String toString();
	
	/**
	 * The method that gives back the correct GUI for given html
	 * @return
	 * returns GUIElement given by the htmlElement
	 */
	public abstract GUIElement transformToGUI(int x, int y, int width, int height);
}
