package htmlElement;

import java.awt.Color;
import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;

/**
 * Test klasse voor GUI uit te testen
 * @author kobe
 *
 */
public class Box extends GUIElement {

	public Box(int x, int y, int w, int h, Color color, MyCanvasWindow window) {
		super(x, y, w, h, window);
		this.setColor(color);
	}
	
	private Color boxColor;
	
	
	
	public Color getColor() {
		return this.boxColor;
	}
	
	public void setColor(Color newColor) {
		this.boxColor=newColor;
	}

	@Override
	public void paint(Graphics g) {
		paintBox(g);
	}
	

	public void paintBox(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(this.getLeftX(), this.getUpperY(), this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(this.getLeftX(), this.getUpperY(), this.getWidth(), this.getHeight());
	}

}
