package htmlElement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import canvaswindow.MyCanvasWindow;

public class TextBox extends GUIElement{

	private TextCursor text;
	private Box box;
	private boolean isActive;

	
	public TextBox(int x, int y, int w, int h) {
		super(x, y, w,  h + (h/4));
		Color color = Color.white;
		this.text = new TextCursor(x, y, w, h, "", "");
		int size=this.getTextCursor().getFontSize();
		System.out.println(size);
		this.setBox(new Box(x, y, w, (int) Math.ceil(size*2), color)); //TODO mooie grootte kiezen
		//TODO de (int) Math.ceil(size*2) zorgt ervoor dat de hoogte van Box van een TextBox niet overeenkomt met de hoogte van de TextBox
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
		this.getTextCursor().setTextCursorOn(isActive);
	}
	
	/**
	 * @return the text
	 */
	public TextCursor getTextCursor() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setTextCursor(TextCursor text) {
		this.text = text;
	}
	
	@Override
	public void paint(Graphics g) {
		this.getBox().paint(g);
		this.getTextCursor().paint(g);
	}
	
	@Override
	public void update(Graphics g) {
		this.getTextCursor().update(g);
	}
	
	
	public void handleMouseEvent(int id, int x, int y) {
		if (id == MouseEvent.MOUSE_CLICKED) {
			if (this.checkCoordinates(x, y)) {
				if(this.isActive()) {
					this.getTextCursor().unselectAllText();
				}
				else {
					this.getTextCursor().selectAll();
				}
				this.setActive(true);
				this.getBox().setColor(Color.gray);
			} else {
				if (this.isActive()){
					this.handleEnter();
					this.getTextCursor().unselectAllText();
				}
				this.setActive(false);
				this.getBox().setColor(Color.white);
			}
			
		}
	}
	
	/**
	 * If the bar is focused, reroute to the functions of corresponding key code.
	 * @param id
	 * @param keyCode
	 * @param keyChar
	 */
	public void handleKeyBoardEvent(int id,int keyCode, char keyChar) {
		if (this.isActive()) {
			if (id == KeyEvent.KEY_PRESSED) {
				switch (keyCode) {
				case 8: //backspace
					//this.getSelectedTextBox().clearSelected();
					this.handleBackSpace();
					break;
				case 127: //delete
					this.handleDelete();
					break;
				case 10: //enter
					this.handleEnter();
					break;
				case 27: //escape
					this.handleEscape();
					break;
				case 37: //left
					this.handleLeft();
					break;
				case 39: //Right
					this.handleRight();
					break;
				case 36: //home
					this.handleHome();
					break;
				case 35: //end
					this.handleEnd();
					break;
				default:
					if (keyChar != KeyEvent.CHAR_UNDEFINED) {
						// dit zijn speciale gevallen en kan misschien op een betere manier opgelost worden
						// deze if statement is er voor bijvoorbeeld bij Steven zijn keyboard niet drie keer tilde te krijgen in de string
						if (keyChar != '`' &&  keyChar != '~') {
							this.handleUndefined(keyChar);
						}
					}
					break;
				}
			}
			if (id == KeyEvent.KEY_TYPED) {
				// dit zijn speciale gevallen en kan misschien op een betere manier opgelost worden
				if (keyChar == '`' ||  keyChar == '~') {
					this.handleUndefined(keyChar);
				}
			}
		}
	}
	
	/**
	 * Does the needed actions for the backSpace key.
	 */
	private void handleBackSpace() {
		this.getTextCursor().deletePrevious();
	}
	
	/**
	 * Does the needed actions for the delete key.
	 */
	private void handleDelete() {
		this.getTextCursor().deleteNext();
	}
	
	/**
	 * Does the needed actions for the Left arrow key.
	 */
	private void handleLeft() {
		this.getTextCursor().moveLeft();
	}
	
	/**
	 * Does the needed actions for the Right arrow key.
	 */
	private void handleRight() {
		this.getTextCursor().moveRight();
	}
	
	/**
	 * Does the needed actions for the End key.
	 */
	private void handleEnd() {
		this.getTextCursor().moveToEnd();
	}
	
	/**
	 * Does the needed actions for the home key.
	 */
	private void handleHome() {
		this.getTextCursor().moveToBegin();
	}
	
	/**
	 * Does the needed actions for the undefined keys.
	 */
	private void handleUndefined(char keyChar) {
		this.getTextCursor().addCharachter(keyChar);
	}
	
	/**
	 * Does the needed actions for the enter key.
	 */
	protected void handleEnter() {
		/*
		 * Misschien moet er later nog wel iets gedaan worden met andere TextBoxen als er ENTER gedrukt wordt.
		 */
	}
	
	/**
	 * Does the needed actions for the escape key.
	 */
	private void handleEscape() {
		this.setActive(false);
		this.getBox().setColor(Color.white);
	}



}
//
//package htmlElement;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//
//import canvaswindow.MyCanvasWindow;
//
//public class TextBox extends GUIElement{
//
//	private Text text;
//	private Box box;
//	private boolean isActive;
//	private SurroundingTextBox selectedTextBox;
//	private MyCanvasWindow window;
//	
//	
//	public TextBox(int x, int y, int w, int h, MyCanvasWindow window) {
//		super(x, y, w,  h + (h/4));
//		Color color = Color.white;
//		this.setBox(new Box(x, y, w, h + (h/4), color));
//		Text text = Text.constructText("input text: ", x, y, h);
//		this.setText(text);
//		this.setSelectedTextBox(new SurroundingTextBox(0, 0, 0, 0, Color.blue, text)); // maakt een selected textbox aan deze gaat initieel leeg zijn
//		this.window = window;
//	}
//	
//	/**
//	 * Sets the value box of this class
//	 * 
//	 * @param newBox - new value of this.box
//	 */
//	public void setBox(Box newBox) {
//		this.box = newBox;
//	}
//	
//	public Box getBox() {
//		return this.box;
//	}
//	
//	/**
//	 * Sets the value text of this class
//	 * 
//	 * @param newText - new value of this.text
//	 */
//	public void setText(Text newText) {
//		this.text = newText;
//	}
//	
//	public Text getText() {
//		return this.text;
//	}
//
//	public void setTextValue(String newText) {
//		this.text.setText(newText);
//	}
//	
//	public String getTextValue() {
//		return this.text.getText();
//	}
//	
//	
//	public boolean isActive() {
//		return isActive;
//	}
//
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}
//	
//	@Override
//	public void paint(Graphics g) {
//		this.getBox().paint(g);
//		this.getSelectedTextBox().paint(g);
//		this.getText().paint(g);
//	}
//	
//	@Override
//	public void update(Graphics g) {
//		this.getText().update(g);
//	}
//	
//	
//	public void handleMouseEvent(int id, int x, int y) {
//		if (id == MouseEvent.MOUSE_CLICKED) {
//			if (this.checkCoordinates(x, y)) {
//				if(this.isActive()) {
//					this.getSelectedTextBox().unselectAllText();
//				}
//				else {
//					this.getSelectedTextBox().selectGivenText(this.getText());
//				}
//				this.setActive(true);
//				this.getBox().setColor(Color.gray);
//			} else {
//				if (this.isActive()){
//					this.handleEnter();
//					this.getSelectedTextBox().unselectAllText();
//				}
//				this.setActive(false);
//				this.getBox().setColor(Color.white);
//			}
//			
//		}
//	}
//	
//	/**
//	 * If the bar is focused, reroute to the functions of corresponding key code.
//	 * @param id - 
//	 * @param keyCode
//	 * @param keyChar
//	 */
//	public void handleKeyBoardEvent(int id,int keyCode, char keyChar) {
//		if (this.isActive()) {
//			if (id == KeyEvent.KEY_PRESSED) {
//				
//				switch (keyChar) {
//				case KeyEvent.VK_BACK_SPACE:
//					this.getSelectedTextBox().clearSelected();
//					this.handleBackSpace();
//					break;
//				case KeyEvent.VK_ENTER:
//					this.handleEnter();
//					break;
//				case KeyEvent.VK_ESCAPE:
//					this.handleEscape();
//					break;
//				default:
//					 if (keyChar != KeyEvent.CHAR_UNDEFINED) {
//						 	this.getSelectedTextBox().clearSelected();
//							this.setTextValue(this.getTextValue() + keyChar);
//						}
//					break;
//				}
//			}
//		}
//	}
//	
//	/**
//	 * Does the needed actions for the backSpace key.
//	 */
//	private void handleBackSpace() {
//		int textLength = this.getTextValue().length();
//		if (textLength > 0) { // if text is not empty
//			this.setTextValue(getTextValue().substring(0, textLength - 1));
//		}
//	}
//	
//	/**
//	 * Does the needed actions for the enter key.
//	 */
//	private void handleEnter() {
//		int textLength = this.getTextValue().length();
//		if (textLength > 0) { // if text is not empty
//			this.getWindow().readFile(getTextValue());
//		}
//	}
//	
//	/**
//	 * Does the needed actions for the escape key.
//	 */
//	private void handleEscape() {
//		this.setActive(false);
//		this.getBox().setColor(Color.white);
//	}
//
//	public SurroundingTextBox getSelectedTextBox() {
//		return selectedTextBox;
//	}
//
//	public void setSelectedTextBox(SurroundingTextBox selectedText) {
//		this.selectedTextBox = selectedText;
//	}
//	
//	/**
//	 * @return the window
//	 */
//	public MyCanvasWindow getWindow() {
//		return window;
//	}
//
//	/**
//	 * @param window - the window to set
//	 */
//	public void setWindow(MyCanvasWindow window) {
//		this.window = window;
//	}
//
//
//
//}

