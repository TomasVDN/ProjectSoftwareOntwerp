package htmlElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextBox extends GUIElement{

	public TextBox(int x, int y, int w, int h) {
		super(x, y, w,  h + (h/4));
		Color color = Color.white;
		this.setBox(new Box(x, y, w, h + (h/4), color));
		Text text = Text.constructText("input text: ", x, y, h);
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
				if(this.isActive()) {
					
				}
				this.setActive(true);
				this.getBox().setColor(Color.gray);
			} else {
				this.setActive(false);
				this.getBox().setColor(Color.white);
				
			}
			
		}
	}
	
	public void handleKeyBoardEvent(int id,int keyCode, char keyChar) {
		if (this.isActive()) {
			if (id == KeyEvent.KEY_PRESSED) {
				if (keyChar == KeyEvent.VK_BACK_SPACE) {
					int textLength = this.getTextValue().length();
					if (textLength > 0) { // if text is not empty
						this.setTextValue(getTextValue().substring(0, textLength - 1));
					}
				} else if (keyChar != KeyEvent.CHAR_UNDEFINED) {
					this.setTextValue(this.getTextValue() + keyChar);
				}
			}
		}
	}
	
	private void backSpace() {
		int textLength = this.getTextValue().length();
		if (textLength > 0) {
			this.setTextValue(getTextValue().substring(0, textLength - 1));
		}

	}


}
