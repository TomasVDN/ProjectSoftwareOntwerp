package toNew;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;
import container.Container;
import htmlElement.ContentSpan;

/**
 * This class turns the html elements to GUI elements, their positions are set to 
 * @author kobe
 *
 */
public class HTMLToGUI {
	
	/**
	 * 
	 * @param x
	 *  x = The Xposition to start drawing
	 * @param y
	 *  y= The Y position to start drawing
	 */
	public HTMLToGUI(int x , int y) {
		
	}
	
	/**
	 * Turn the given html elements to a container and returns them
	 * 
	 * @param
	 * g = graphics needed to find the heigth of a string,...
	 * width= width of the drawing window
	 * heigth = heigth of the drawing window
	 */
	public Container transformToGUI(Graphics g, int width,int heigth, ArrayList<ContentSpan> htmlElements) {
		Container cont = new Container(); // creates empty container
		int relativeY=0;
		for(int i =0 ; i<htmlElements.size();i++) {
			GUIElement gui=toGUI(htmlElements.get(i),width, heigth,relativeY,g);
			relativeY+=gui.getHeight();
			cont.addElement(gui);
		}
		return cont;
	}

	
	/**
	 * Transforms the given html to a GUI
	 * @param contentSpan
	 * @param width
	 * @param heigth
	 * @param relativeY
	 * @return
	 */
	private GUIElement toGUI(ContentSpan contentSpan, int width, int heigth, int relativeY,Graphics g) {
		
		return null;
	}
	
	
	

}