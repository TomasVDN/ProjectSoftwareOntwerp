package htmlElement;

import java.awt.Color;
import java.awt.Graphics;

public class TextCursor extends GUIElement {
	
	/*
	 * TextCursor has 3 text classes, they change throughout the program
	 * however their order always stay the same starting with leftText, selectedText in the middle
	 * rigthText at the end. The strings in those classes can be empty however.
	 */
	private Text leftText;
	private Text selectedText;
	private Text rightText;
	private SurroundingTextBox selectionBox;
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
		this.setLeftText(new Text(x, y, w, h, leftString));
		this.setSelectedText(new Text(x, y, w, h, ""));
		this.setSelectionBox(new SurroundingTextBox(x, y, 0, 0, Color.blue, this.getSelectedText()));
		this.setRightText(new Text(x, y, w, h, rightString));
		this.setBox(new Box(x, y, (int) Math.ceil(this.getFontSize() / 10), this.getFontSize(), Color.black));
	}

	/**
	 * This method paints the two Text's. If, additionally, this.isTextCursor is true, it will also paint the cursor in itself.
	 */
	@Override
	public void paint(Graphics g) {
		this.getLeftText().paint(g);
		this.getSelectionBox().paint(g);
		this.getSelectedText().paint(g);
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
		
		this.getSelectedText().setX(rightXLeftSide);
		this.getSelectedText().setY(upperYLeftSide);
		
		this.getSelectedText().update(g);
		
		int rightXSelectedSide = this.getSelectedText().getRightX();
		int upperYSelectedSide = this.getSelectedText().getUpperY();
		
		this.getRightText().setX(rightXSelectedSide);
		this.getRightText().setY(upperYSelectedSide);
		
		this.getRightText().update(g);
		this.getSelectionBox().update(g);
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
		this.unselectAllText();
		if (this.getStringRightText().length() != 0) {
			this.setLeftTextString(this.getStringLeftText() + this.getStringRightText().charAt(0));
			this.setRightTextString(this.getStringRightText().substring(1));
		}		
	}
	
	/**
	 * This method handles every action that needs to be done when moving one character to the left.
	 */
	public void moveLeft() {
		this.unselectAllText();
		if (this.getStringLeftText().length() != 0) {
			this.setRightTextString(this.getStringLeftText().charAt(this.getStringLeftText().length() - 1) + this.getStringRightText());
			this.setLeftTextString(this.getStringLeftText().substring(0, this.getStringLeftText().length() - 1));
		}
	}
	
	/**
	 * This method handles every action that needs to be done when moving to the end of the text.
	 */
	public void moveToEnd() {
		this.unselectAllText();
		this.setLeftTextString(this.getStringLeftText() + this.getStringRightText());
		this.setRightTextString("");
	}
	
	/**
	 * This method handles every action that needs to be done when moving to the begin of the text.
	 */
	public void moveToBegin() {
		this.unselectAllText();
		this.setRightTextString(this.getStringLeftText() + this.getStringRightText());
		this.setLeftTextString("");
	}
	
	/**
	 * This method handles every action that needs to be done when removing a character on the left of the cursor.
	 */
	public void deletePrevious() {
		this.clearSelected();
		if (this.getStringLeftText().length() > 0) { // check if left string is not empty
			this.setLeftTextString(this.getStringLeftText().substring(0, this.getStringLeftText().length() - 1));
		}
	}
	
	/**
	 * This method handles every action that needs to be done when removing a character on the right of the cursor.
	 */
	public void deleteNext() {
		this.clearSelected();
		if (this.getStringRightText().length() > 0) { // check if right string is not empty
			this.setRightTextString(this.getStringRightText().substring(1));
		}
	}
	
	/**
	 * This method handles every action that needs to be done when adding a character. This always happens on the left of the cursor.
	 */
	public void addCharachter(char keyChar) {
		this.clearSelected();
		this.setLeftTextString(this.getLeftText().getText() + keyChar);
	}
	
	//##############################Selection#######################################
	
	public void selectAll() {
		this.setSelectedTextString(this.getStringLeftText() + this.getStringSelectedText() + this.getStringRightText());
		this.getLeftText().clearText();
		this.getRightText().clearText();
	}
	
	/**
	 * When method is called the blue box dissapears
	 */
	public void unselectAllText() {
		this.setRightTextString(this.getStringSelectedText() +this.getStringRightText()); // Voeg string aan rechts toe
		this.setSelectedTextString("");
	}
	
	/**
	 * Clears all selected Text
	 */
	public void clearSelected() {
		this.setSelectedTextString("");
	}
	
	
	//#############################end Selection##################################################
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
	public void setLeftTextString(String leftText) {
		this.leftText.setText(leftText);;
	}

	/**
	 * This method returns the text stored in the left Text element.
	 * @return this.leftText.getText
	 */
	public String getStringSelectedText() {
		return this.getSelectedText().getText();
	}

	/**
	 * This method sets the text value of the left Text element.
	 * @param leftText - the new value of text of the left Text element.
	 */
	public void setSelectedTextString(String newText) {
		this.getSelectedText().setText(newText);
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
	public void setRightTextString(String rightText) {
		this.rightText.setText(rightText);
	}

	/**
	 * This method returns a new Text element containing all the text contained in both Text elements
	 * @return new Text(this.leftText.getLeftX(), this.leftText.getUpperY(), this.leftText.getWidth() + this.rightText.getWidth(), this.leftText.getHeight(), this.getStringLeftText() + this.getStringRightText())
	 */
	public Text getTextFromTextCursor() {
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

	public Text getSelectedText() {
		return selectedText;
	}

	public void setSelectedText(Text selectedText) {
		this.selectedText = selectedText;
	}

	public SurroundingTextBox getSelectionBox() {
		return selectionBox;
	}

	public void setSelectionBox(SurroundingTextBox selectionBox) {
		this.selectionBox = selectionBox;
	}
	


}
