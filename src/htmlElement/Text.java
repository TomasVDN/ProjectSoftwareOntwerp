package htmlElement;

import java.awt.Font;
import java.awt.Graphics;

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
		g.setFont(new Font(Font.DIALOG, Font.PLAIN, 40));
		g.drawString(getText(), getLeftX(), getLowerY());
	}

}
