package converter;

import java.util.ArrayList;

import GUIElements.GUIElement;
import events.EventReader;
import htmlElement.ContentSpan;

/**
 * This class turns the html elements to GUI elements, their positions are set to 
 * @author kobe
 *
 */
public class HTMLToGUI {
	
	/**
	 * The distance between the guiElements from the start x position
	 */
	private static int XSPACE= 3;
	
	/**
	 * The distance between the guiElements in the y direction
	 */
	private static int YSPACE= 3;
	
	/**
	 * 
	 * @param x
	 *  x = The X position to start drawing
	 * @param y
	 *  y= The Y position to start drawing
	 */
	public HTMLToGUI() {
		
	}
	
	/**
	 * Turn the given html elements to a container and returns them
	 * 
	 * @param
	 * g = graphics needed to find the height of a string,...
	 * width= width of the drawing window
	 * height = height of the drawing window
	 */
	public ArrayList<GUIElement> transformToGUI(int x, int y, int width, int height, ArrayList<ContentSpan> htmlElements) {
		ArrayList<GUIElement> cont = new ArrayList<GUIElement>(); // creates empty container
		int relativeY=y;
		int relativeX=x + XSPACE;
		for(int i =0 ; i<htmlElements.size();i++) {
			GUIElement gui = toGUI(htmlElements.get(i), relativeX, relativeY, width, height);
			relativeY+=gui.getHeight() +YSPACE;
			cont.add(gui);
		}
		return cont;
	}

	
	/**
	 * Transforms the given html to a GUI
	 * @param contentSpan
	 * @param width
	 * @param height
	 * @param relativeY
	 * @return
	 */
	private GUIElement toGUI(ContentSpan contentSpan, int relativeX, int relativeY, int width, int height) {
		return contentSpan.transformToGUI(relativeX, relativeY, width, height);
	}
	
	
	

}
