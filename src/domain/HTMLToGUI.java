package domain;

import java.util.ArrayList;

import GUIElements.GUIElement;
import htmlElement.ContentSpan;

/**
 * This class turns the HTML elements to GUI elements.
 *
 */
public class HTMLToGUI {
	
	/**
	 * The distance between the guiElements from the start x position
	 */
	private static int XSPACE= 10;
	
	/**
	 * @return this.XSPACE
	 */
	public static int getXSPACE() {
		return XSPACE;
	}


	/**
	 * The distance between the guiElements in the y direction
	 */
	private static int YSPACE= 3;
	
	/**
	 * @return this.YSPACE
	 */
	public static int getYSPACE() {
		return YSPACE;
	}
	
	/**
	 * Transforms the given htmlElements to GUIElements
	 * @param htmlElements - htmlElements to transform
	 * @param width - width of the new container
	 * @param height - height of the new container
	 * @param relativeX - x coordinate of the new container
	 * @param relativeY - y coordinate of the new container
	 * @param eventReader - eventReader of the new container
	 * @return container with the GUIElements corresponding to the given htmlElements
	 */
	public ArrayList<GUIElement> transformToGUI(int x, int y, int width, int height, ArrayList<ContentSpan> htmlElements) {
		ArrayList<GUIElement> content = new ArrayList<GUIElement>();
		int relativeY = y;
		int relativeX = x + XSPACE;
		
		for(int i = 0 ; i < htmlElements.size(); i++) {
			GUIElement gui = toGUI(htmlElements.get(i), relativeX, relativeY, width, height);
			relativeY+=gui.getHeight() +YSPACE;
			content.add(gui);
		}
		return content;
	}

	/**
	 * Transforms the given HTML to a GUI
	 * @param contentSpan - contentSpan to transform
	 * @param width - width of the GUIElement
	 * @param height - height of the GUIElement
	 * @param relativeX - x coordinate of the GUIElement
	 * @param relativeY - y coordinate of the GUIElement
	 * @param eventReader - eventReader of the GUIElement
	 * @return GUIElement corresponding to the given contentSpan
	 */
	public GUIElement toGUI(ContentSpan contentSpan, int relativeX, int relativeY, int width, int height) {
		return contentSpan.transformToGUI(relativeX, relativeY, width, height);
	}
}
