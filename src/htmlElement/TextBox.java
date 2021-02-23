package htmlElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import canvaswindow.MyCanvasWindow;

public class TextBox extends GUIElement{

	private Text text;
	private Box box;
	private boolean isActive;
	private Box selectedText;
	
	public TextBox(int x, int y, int w, int h, MyCanvasWindow window) {
		super(x, y, w,  h + (h/4),window);
		Color color = Color.white;
		this.setBox(new Box(x, y, w, h + (h/4), color, window));
		this.setSelectedText(new Box(0, 0, 0, 0, Color.blue, window)); // maakt een selected textbox aan deze gaat initieel leeg zijn
		Text text = Text.constructText("input text: ", x, y, h, window);
		this.setText(text);
	}
	
	/**
	 * Sets the value box of this class
	 * 
	 * @param newBox - new value of this.box
	 */
	public void setBox(Box newBox) {
		this.box = newBox;
	}
	
	public Box getBox() {
		return this.box;
	}
	
	/**
	 * Sets the value text of this class
	 * 
	 * @param newText - new value of this.text
	 */
	public void setText(Text newText) {
		this.text = newText;
	}
	
	public Text getText() {
		return this.text;
	}

	public void setTextValue(String newText) {
		this.text.setText(newText);
	}
	
	public String getTextValue() {
		return this.text.getText();
	}
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public void paint(Graphics g) {
		this.getBox().paint(g);
		this.getSelectedText().paint(g);
		this.getText().paint(g);
	}
	
	@Override
	public void update(Graphics g) {
		this.getText().update(g);
	}
	
	
	public void handleMouseEvent(int id, int x, int y) {
		if (id == MouseEvent.MOUSE_CLICKED) {
			if (this.checkCoordinates(x, y)) {
				if(this.isActive()) {
					unselectAllText();
				}
				else {
					selectAllText();
				}
				this.setActive(true);
				this.getBox().setColor(Color.gray);
			} else {
				if (this.isActive()){
					this.handleEnter();
					this.unselectAllText();
				}
				this.setActive(false);
				this.getBox().setColor(Color.white);
			}
			
		}
	}
	
	/**
	 * If the bar is focused, reroute to the functions of corresponding key code.
	 * @param id - 
	 * @param keyCode
	 * @param keyChar
	 */
	public void handleKeyBoardEvent(int id,int keyCode, char keyChar) {
		if (this.isActive()) {
			if (id == KeyEvent.KEY_PRESSED) {
				
				switch (keyChar) {
				case KeyEvent.VK_BACK_SPACE:
					this.handleBackSpace();
					break;
				case KeyEvent.VK_ENTER:
					this.handleEnter();
					break;
				case KeyEvent.VK_ESCAPE:
					this.handleEscape();
					break;
				default:
					 if (keyChar != KeyEvent.CHAR_UNDEFINED) {
							this.setTextValue(this.getTextValue() + keyChar);
						}
					break;
				}
			}
		}
	}
	
	/**
	 * Does the needed actions for the backSpace key.
	 */
	private void handleBackSpace() {
		int textLength = this.getTextValue().length();
		if (textLength > 0) { // if text is not empty
			this.setTextValue(getTextValue().substring(0, textLength - 1));
		}
	}
	
	/**
	 * Does the needed actions for the enter key.
	 */
	private void handleEnter() {
		int textLength = this.getTextValue().length();
		if (textLength > 0) { // if text is not empty
			this.getWindow().readFile(getTextValue());
		}
	}
	
	/**
	 * Does the needed actions for the escape key.
	 */
	private void handleEscape() {
		this.setActive(false);
		this.getBox().setColor(Color.white);
	}

	public Box getSelectedText() {
		return selectedText;
	}

	public void setSelectedText(Box selectedText) {
		this.selectedText = selectedText;
	}
	
	/**
	 * When method is called the blue box of the selected text gets the coordinates and the size of the text
	 */
	public void selectAllText() {
		this.getSelectedText().setX(this.getText().getLeftX());
		this.getSelectedText().setY(this.getText().getUpperY());
		this.getSelectedText().setHeight(this.getText().getHeight());
		this.getSelectedText().setWidth(this.getText().getWidth());
	}
	
	/**
	 * When method is called the blue box dissapears
	 */
	public void unselectAllText() {
		this.getSelectedText().setHeight(0);
		this.getSelectedText().setWidth(0);
	}


}