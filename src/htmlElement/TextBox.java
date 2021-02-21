package htmlElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextBox extends GUIElement{

	public TextBox(int x, int y, int w, int h) {
		super(x, y, w, h);
		Color color = Color.gray;
		this.setBox(new Box(x, y, w, h, color));
		Text text = Text.constructText("Text", x, y, metrics);
		this.setText(text);
		// TODO Auto-generated constructor stub
	}
	
	private Text text;
	private Box box;
	private boolean isActive; 
	
	public void setBox(Box newBox) {
		this.box = newBox;
	}
	
	public Box getBox() {
		return this.box;
	}
	
	public Text getText() {
		return this.text;
	}
	
	public void setText(Text newText) {
		this.text = newText;
	}
	
	public String getTextValue() {
		return this.text.getText();
	}
	
	public void setTextValue(String newText) {
		this.text.setText(newText);
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public void paint(Graphics g) {
		this.getBox().paintBox(g);
		this.getText().paintText(g);
	}
	
	public void handleMouseEvent(int id, int x, int y) {
		if (id == MouseEvent.MOUSE_CLICKED) {
			if (this.checkCoordinates(x, y)) {
				this.setActive(true);
				this.getBox().setColor(Color.blue);
			} else {
				this.setActive(false);
				this.getBox().setColor(Color.red);
				
			}
			
		}
	}
	
	public void handleKeyBoardEvent(int id, char keyChar) {
		if (this.isActive()) {
			if (id == KeyEvent.KEY_PRESSED) {
				this.setTextValue(this.getTextValue() + keyChar);
			}
		}
	}


}