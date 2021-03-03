package facades;

import java.awt.Graphics;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.Text;
import GUIElements.TextBox;
import container.Container;

public class WindowManager {


	private ArrayList<Container> listOfContainers = new ArrayList<Container>();
	
	public WindowManager () {

	}



	public void paint(Graphics g) {
		listOfContainers.stream().forEach(container -> container.paint(g));
	}
	
	public Container containerAt(int x, int y) {
		for(Container c: listOfContainers) {
			if (c.containsPoint(x, y)) {
				return c;
			}
		}
		return null;
	}


	public void handleMouseEvent(int id,int x, int y) {
		
	
	}
	


}