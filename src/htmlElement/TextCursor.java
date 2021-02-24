package htmlElement;

import java.awt.Color;
import java.awt.Graphics;

public class TextCursor extends GUIElement {
	
	private Text leftText;
	private Text rightText;
	private Box box;
	private boolean textCursorOn;


	/**
	 * Constructor of the TextCursor class. TextCursor is constructed in the following way: Text + Box + Text.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param color
	 * @param leftString
	 * @param rightString
	 */
	public TextCursor(int x, int y, int w, int h, String leftString,String rightString) {
		super(x, y, w, h);
		
		leftText = new Text(x, y, w, h, leftString);
		rightText = new Text(x, y, w, h, rightString);
		
		box = new Box(x, y, (int) Math.ceil(this.getFontSize() / 10), this.getFontSize(), Color.black);
	}

	/**
	 * This method paints the two Text's. If, additionally, this.isTextCursor is true, it will also paint the cursor in itself.
	 */
	@Override
	public void paint(Graphics g) {
		this.getLeftText().paint(g);
		this.getRightText().paint(g);
		
		if(this.isTextCursorOn()) {
			this.getBox().paint(g);
		}
	}
	
	/**
	 * This method updates the position of the right Text and of the box depending on the size of the left Text.
	 */
	public void update(Graphics g) {
		this.getLeftText().update(g);
		
		int rightXLeftSide = this.getLeftText().getRightX();
		int upperYLeftSide = this.getLeftText().getUpperY();
		
		this.getBox().setX(rightXLeftSide);
		this.getBox().setY(this.getLeftText().getLowerY() - this.getBox().getHeight());
		
		this.getRightText().setX(rightXLeftSide);
		this.getRightText().setY(upperYLeftSide);
		
		this.getRightText().update(g);
	}
	
	/**
	 * this method gives back the fontSize
	 */
	public int getFontSize() {
		return this.getLeftText().getFont().getSize();
	}
	
	/**
	 * This method handles every action that needs to be done when moving one character to the right.
	 */
	public void moveRight() {
		if (this.getStringRightText().length() != 0) {
			this.setLeftText(this.getStringLeftText() + this.getStringRightText().charAt(0));
			this.setrightText(this.getStringRightText().substring(1));
		}		
	}
	
	/**
	 * This method handles every action that needs to be done when moving one character to the left.
	 */
	public void moveLeft() {
		if (this.getStringLeftText().length() != 0) {
			this.setrightText(this.getStringLeftText().charAt(this.getStringLeftText().length() - 1) + this.getStringRightText());
			this.setLeftText(this.getStringLeftText().substring(0, this.getStringLeftText().length() - 1));
		}
	}
	
	/**
	 * This method handles every action that needs to be done when moving to the end of the text.
	 */
	public void moveToEnd() {
		this.setLeftText(this.getStringLeftText() + this.getStringRightText());
		this.setrightText("");
	}
	
	/**
	 * This method handles every action that needs to be done when moving to the begin of the text.
	 */
	public void moveToBegin() {
		this.setrightText(this.getStringLeftText() + this.getStringRightText());
		this.setLeftText("");
	}
	
	/**
	 * This method handles every action that needs to be done when removing a character on the left of the cursor.
	 */
	public void deletePrevious() {
		this.setLeftText(this.getStringLeftText().substring(0, this.getStringLeftText().length() - 1));
	}
	
	/**
	 * This method handles every action that needs to be done when removing a character on the right of the cursor.
	 */
	public void deleteNext() {
		this.setrightText(this.getStringRightText().substring(1));
	}
	
	/**
	 * This method handles every action that needs to be done when adding a character. This always happens on the left of the cursor.
	 */
	public void addCharachter(char keyChar) {
		this.setLeftText(this.getLeftText().getText() + keyChar);
	}
	
	/**
	 * This method returns the text stored in the left Text element.
	 * @return this.leftText.getText
	 */
	public String getStringLeftText() {
		return this.leftText.getText();
	}

	/**
	 * This method sets the text value of the left Text element.
	 * @param leftText - the new value of text of the left Text element.
	 */
	public void setLeftText(String leftText) {
		this.leftText.setText(leftText);;
	}

	/**
	 * This method returns the text stored in the right Text element.
	 * @return this.leftText.getText
	 */
	public String getStringRightText() {
		return rightText.getText();
	}

	/**
	 * This method sets the text value of the right Text element.
	 * @param rightText - the new value of text of the right Text element.
	 */
	public void setrightText(String rightText) {
		this.rightText.setText(rightText);
	}

	/**
	 * This method returns a new Text element containing all the text contained in both Text elements
	 * @return new Text(this.leftText.getLeftX(), this.leftText.getUpperY(), this.leftText.getWidth() + this.rightText.getWidth(), this.leftText.getHeight(), this.getStringLeftText() + this.getStringRightText())
	 */
	public Text getText() {
		return new Text(this.leftText.getLeftX(), this.leftText.getUpperY(), this.leftText.getWidth() + this.rightText.getWidth(),
				this.leftText.getHeight(), this.getStringLeftText() + this.getStringRightText());
	}
	
	/**
	 * This method returns if the cursor is to be displayed
	 * @return this.textCursorOn
	 */
	public boolean isTextCursorOn() {
		return this.textCursorOn;
	}

	/**
	 * This method sets the value of textCursorOn. If True, the cursor will be displayed. Otherwise, it will not.
	 * @param textCursorOn
	 */
	public void setTextCursorOn(boolean textCursorOn) {
		this.textCursorOn = textCursorOn;
	}
	
	/**
	 * This methods returns the left Text element.
	 * @return this.leftText 
	 */
	public Text getLeftText() {
		return this.leftText;
	}
	
	/**
	 * This method sets the left Text element to the given Text element.
	 * @param leftText
	 */
	public void setLeftText(Text leftText) {
		this.leftText = leftText;
	}
	
	/**
	 * This methods returns the right Text element.
	 * @return this.rightText 
	 */
	public Text getRightText() {
		return this.rightText;
	}
	
	/**
	 * This method sets the right Text element to the given Text element.
	 * @param rightText
	 */
	public void setRightText(Text rightText) {
		this.rightText = rightText;
	}
	
	/**
	 * This methods sets the box element to the given box element.
	 * @param box 
	 */
	public void setBox(Box box) {
		this.box = box;
	}
	
	/**
	 * This methods returns the box element.
	 * @return this.box 
	 */
	public Box getBox() {
		return this.box;
	}
	


}
