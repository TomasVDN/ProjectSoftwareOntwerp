package htmlElement;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public abstract class GUIElement {
	
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	Font font = new Font(Font.DIALOG, Font.PLAIN, 30);
	FontMetrics metrics;
	
	public GUIElement(int x, int y, int w, int h){
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
	}
	
	public void setX(int x) {
		this.xPos = x;
	}
	
	public int getLeftX() {
		return this.xPos;
	}
	
	public int getRightX() {
		return this.xPos + this.width;
	}	
	
	public void setY(int y) {
		this.yPos = y;
	}

	public int getUpperY() {
		return this.yPos + this.height;
	}
	
	public int getLowerY() {
		return this.yPos;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public abstract void paint(Graphics g);
	
}
