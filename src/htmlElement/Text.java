package htmlElement;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

public class Text extends GUIElement{
	
	private String text;

	public Text(int x, int y, int w, int h, String text){
		super(x, y, w, h);
		this.setText(text);
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawString(getText(), getLeftX(), getLowerY());
	}

}
