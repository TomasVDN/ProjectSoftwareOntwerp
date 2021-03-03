package GUIElements;

import java.awt.Color;
import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;

/**
 * Class to make a Box.
 * @author kobe
 *
 */
public class Box extends GUIElement {

	private Color boxColor;
	
	public Box(int x, int y, int w, int h, Color color) {
		super(x, y, w, h);
		this.setColor(color);
	}
		
	/**
	 * Returns the value bowColor of this class
	 * 
	 * @return this.boxColor
	 */
	public Color getColor() {
		return this.boxColor;
	}
	
	/**
	 * Sets the value boxColor to the given new value.
	 * 
	 * @param newColor - new value of this.boxColor
	 */
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
