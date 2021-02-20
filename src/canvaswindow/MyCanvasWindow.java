package canvaswindow;

import java.awt.Graphics;
import java.util.ArrayList;

import htmlElement.GUIElement;
import htmlElement.Text;


public class MyCanvasWindow extends CanvasWindow {
	
	Text test;
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	
	//TODO test must be given from input file
	public MyCanvasWindow(String title) {
		super(title);
		test = new Text(25, 25, 10, 10, "Test");
		elements.add(test);
	}
	
	@Override
	protected void handleShown() {
		repaint();
	}
	
	@Override
	protected void paint(Graphics g) {
		elements.forEach((n) -> n.paint(g));
	}
	
	@Override
	protected void handleResize() {
		repaint();
	}
	
	@Override
	public void handleKeyEvent(int id, int keyCode, char keyChar){
		
	}
	
	@Override
	public void handleMouseEvent(int id, int x, int y, int clickCount){
		
	}

}
