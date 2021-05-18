package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import EventListeners.ScrollBarListener;

public class TextBox extends GUIElement implements ScrollBarListener {

	private String leftText = "", rightText = "";
	private String previousText = "";
	private String selectedText = "";
	private Boolean cursorOnTheRightOfSelectedText = false;
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	private String name;
	private int xOffset;
	private int yOffset;
	private int CONSTANTXOFFSET=10;
	private int CONSTANTYOFFSET = 2;
	

	/**
	 * Constructor of the TextBox class
	 * @param x - x coordinate of the TextBox
	 * @param y - y coordinate of the TextBox
	 * @param w - width of the TextBox
	 * @param h - height of the TextBox
	 */
	public TextBox(int x, int y, int w, int h) {
		super(x, y, w, h);
		leftText = "";
		rightText = "";
		selectedText = "";
	}
	
	/**
	 * Constructor of the TextBox class
	 * @param x - x coordinate of the TextBox
	 * @param y - y coordinate of the TextBox
	 * @param w - width of the TextBox
	 * @param h - height of the TextBox
	 * @param name - name of this textBox instance
	 */
	public TextBox(int x, int y, int w, int h,String name) {
		super(x, y, w, h);
		this.setName(name);
		leftText = "";
		rightText = "";
		selectedText = "";
	}
	
	/**
	 * @return the leftText
	 */
	public String getLeftText() {
		return leftText;
	}

	/**
	 * @param leftText - the new value of this.leftText
	 */
	public void setLeftText(String leftText) throws IllegalArgumentException {
		if (leftText == null) {
			throw new IllegalArgumentException("Null given to TextBox");
		}
		this.leftText = leftText;
	}

	/**
	 * @return the rightText
	 */
	public String getRightText() {
		return rightText;
	}

	/**
	 * @param rightText - the new value of this.rightText
	 */
	public void setRigthText(String rightText) {
		if (rightText == null) {
			throw new IllegalArgumentException("Null given to TextBox");
		}
		this.rightText = rightText;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return leftText + selectedText + rightText;
	}
	
	/**
	 * Returns the width of the text
	 */
	public int getWidthText() {
		String text = this.getText();
		Text textWithWidth= new Text(0, 0, text); // position doesn't matter
		return Math.max(this.getWidth(), textWithWidth.getWidth()+CONSTANTXOFFSET);
	}

	/**
	 * @return the previousText
	 */
	public String getPreviousText() {
		return previousText;
	}

	/**
	 * @param previousText - the new value of this.previousText to set
	 */
	public void setPreviousText(String previousText) {
		if (previousText == null) {
			throw new IllegalArgumentException("Null given to TextBox");
		}
		this.previousText = previousText;
	}

	/**
	 * Handler for left mouse click. If textBox was not active, keep track of the previous content and select all text. Otherwise, unselect selected text.
	 */
	@Override
	public void handleClick() {
		if (!isActive()) {
			this.previousText = getText();
			this.selectAll();
			setActive(true);
		} else {
			this.unselectAllTextToLeft();
		}
	}

	/**
	 * Handler for the keyboardEvents.
	 */
	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
		if (!this.isActive()) {
			return;
		}
		
		switch (keyCode) {
		case 8: //backspace
			this.handleBackSpace();
			break;
		case 127: //delete
			this.handleDelete();
			break;
		case 27: //escape
			this.handleEscape();
			break;
		case 37: //left
			this.handleLeft(modifier);
			break;
		case 39: //Right
			this.handleRight(modifier);
			break;
		case 36: //home
			this.handleHome(modifier);
			break;
		case 35: //end
			this.handleEnd(modifier);
			break;
		case 10://enter
			this.handleEnter();
			break;
		default:
			if (keyChar != KeyEvent.CHAR_UNDEFINED) {
				this.handleUndefined(keyChar);
			}
			break;
		}
		this.notifyAdjustmentListener(this.getWidth(),this.getWidthText(),this.getHeight(),this.getHeight());
		
	}
	
	/**
	 * Paints all the components of this textBox.
	 */
	@Override
	public void paint(Graphics g) {
		g.setFont(font);
		FontMetrics metrics =  g.getFontMetrics(g.getFont());
		
		//content
		this.drawContent(g);
					
		//Border
		g.setColor(Color.black);
		g.drawRect(super.getX(),super.getY(), super.getWidth(), super.getHeight());
		
		//Text
		Graphics newG= g.create(getX(), getY(), getWidth(), getHeight());
		this.drawText(newG, metrics);
		//cursor
		if (isActive()) this.drawCursor(newG, metrics);
	}
	
	/**
	 * Draws the content (backgroundColor)
	 * @param g
	 */
	private void drawContent(Graphics g) {
		if (isActive()) {
			g.setColor(Color.gray);
		} else { 
			g.setColor(Color.white);
		}
		g.fillRect(super.getX(),super.getY(), super.getWidth(), super.getHeight());
	}
	
	/**
	 * Draw the text from this textBox.
	 * @param g
	 * @param metrics
	 */
	private void drawText(Graphics g, FontMetrics metrics) {

		
		if (selectedText != "") {
			int y =((this.getHeight() - metrics.getHeight()) / 2) -yOffset;
			int x = metrics.stringWidth(this.leftText)+xOffset;
			g.setColor(Color.blue);
			g.fillRect(x, y, metrics.stringWidth(selectedText), metrics.getHeight());
			g.setColor(Color.black);
		}
		
		int y = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent()-yOffset;
		//g.drawString(this.getText(), super.getX(), y);
		g.drawString(this.getText(), xOffset, y);
	}
	
	/**
	 * Draws the cursor.
	 * @param g
	 * @param metrics
	 */
	private void drawCursor(Graphics g, FontMetrics metrics) {
		int y = ((this.getHeight() - metrics.getHeight()) / 2);
		int x = metrics.stringWidth(leftText)+xOffset;
		if (cursorOnTheRightOfSelectedText()) {
			x += metrics.stringWidth(selectedText); 
		}
		g.fillRect(x, y, Math.max(1,metrics.getHeight()/10), metrics.getHeight());
	}
	
	/**
	 * Does the needed actions for the enter key.
	 */
	void handleEnter() {
		this.setActive(false);
	}
	
	/**
	 * Does the needed actions for the backSpace key.
	 */
	private void handleBackSpace() {
		deleteAllSelected();
		if (this.leftText.length() > 0) { // check if left string is not empty
			this.setLeftText(this.leftText.substring(0, this.leftText.length() - 1));
		}
	}
	
	/**
	 * Does the needed actions for the delete key.
	 */
	private void handleDelete() {
		deleteAllSelected();
		if (this.rightText.length() > 0) { // check if right string is not empty
			this.setRigthText(this.rightText.substring(1));
		}
	}
	
	/**
	 * Does the needed actions for the Left arrow key.
	 */
	private void handleLeft(int modifier) {
		if (modifier == 64) { //Shift
			if (selectedText == "") {
				cursorOnTheRightOfSelectedText = true;
			}
			if (cursorOnTheRightOfSelectedText()) {
				if (this.leftText.length() != 0) {
					selectedText = leftText.charAt(leftText.length() - 1) + selectedText;
					leftText = leftText.substring(0, leftText.length() - 1);
				}
			} else {
				if (this.selectedText.length() != 0) {
					rightText = selectedText.charAt(selectedText.length() - 1) + rightText;
					selectedText = selectedText.substring(0, selectedText.length() - 1);
				}
			}
		} else {
			this.unselectAllTextToRight();
			if (this.leftText.length() != 0) {
				rightText = leftText.charAt(leftText.length() - 1) + rightText;
				leftText = leftText.substring(0, leftText.length() - 1);
			}
		}
	}
	
	/**
	 * Does the needed actions for the Right arrow key.
	 */
	private void handleRight(int modifier) {
		if (modifier == 64) { //Shift
			if (selectedText == "") {
				cursorOnTheRightOfSelectedText = false;
			}
			if (cursorOnTheRightOfSelectedText()) {
				if (this.selectedText.length() != 0) {
					leftText = leftText + selectedText.charAt(0);
					selectedText = selectedText.substring(1);
				}
			} else {
				if (this.rightText.length() != 0) {
					selectedText = selectedText + rightText.charAt(0);
					rightText = rightText.substring(1);
				}
			}
		} else {
			this.unselectAllTextToLeft();
			if (this.rightText.length() != 0) {
				leftText = leftText + rightText.charAt(0);
				rightText = rightText.substring(1);
			}
		}
	}

	/**
	 * Does the needed actions for the End key.
	 */
	private void handleEnd(int modifier) {
		if (modifier == 64) { //Shift
			if (cursorOnTheRightOfSelectedText()) {
				this.unselectAllTextToLeft();
			} else {
				this.unselectAllTextToRight();
			}
			this.selectAllRight();
			this.cursorOnTheRightOfSelectedText = false;
		} else {
			this.unselectAllTextToRight();
			leftText = leftText + rightText;
			rightText = "";
		}
	}
	
	/**
	 * Does the needed actions for the home key.
	 */
	private void handleHome(int modifier) {
		if (modifier == 64) { //Shift
			if (cursorOnTheRightOfSelectedText()) {
				this.unselectAllTextToLeft();
			} else {
				this.unselectAllTextToRight();
			}
			this.selectAllLeft();
			this.cursorOnTheRightOfSelectedText = true;
		} else {
			this.unselectAllTextToLeft();
			rightText = leftText + rightText;
			leftText = "";
		}
	}
	
	/**
	 * Does the needed actions for the undefined keys.
	 */
	private void handleUndefined(char keyChar) {
		deleteAllSelected();
		leftText = leftText + keyChar;
	}
		
	/**
	 * Does the needed actions for the escape key.
	 */
	private void handleEscape() {
		setActiveNoUnselect(false);
		leftText = previousText;
		rightText = "";
		selectedText = "";
	}

	/**
	 * @return the selectedText
	 */
	public String getSelectedText() {
		return selectedText;
	}

	/**
	 * @param selectedText the selectedText to set
	 */
	public void setSelectedText(String selectedText) {
		if (selectedText == null) {
			throw new IllegalArgumentException("Null given to TextBox");
		}
		this.selectedText = selectedText;
	}
	
	/**
	 * Selects all the content in textBox
	 */
	private void selectAll() {
		this.setSelectedText(this.leftText + this.selectedText + this.rightText);
		this.leftText = "";
		this.rightText = "";
	}
	
	/**
	 * Selects all the content in textBox left from the cursor
	 */
	private void selectAllLeft() {
		this.setSelectedText(this.leftText + this.selectedText);
		this.leftText = "";
	}
	
	/**
	 * Selects all the content in textBox right from the cursor
	 */
	private void selectAllRight() {
		this.setSelectedText(this.selectedText + this.rightText);
		this.rightText = "";
	}
	
	/**
	 * Delete all the content of selected text.
	 */
	private void deleteAllSelected() {
		this.selectedText = "";
	}

	/**
	 * Unselect all text right.
	 */
	protected void unselectAllTextToRight() {
		setRigthText(selectedText + rightText); 
		this.selectedText = "";
	}
	
	/**
	 * Unselect all text left.
	 */
	protected void unselectAllTextToLeft() {
		setLeftText(leftText + selectedText); 
		this.selectedText = "";
	}
	
	/**
	 * Sets the content of the TextBox to the given value.
	 * @param text - the new content
	 */
	public void replaceBox(String text) {
		if (text == null) {
			throw new IllegalArgumentException("Null given to TextBox");
		}
		this.setLeftText(text);
		this.setSelectedText("");
		this.setRigthText("");
		this.notifyAdjustmentListenerReset(this.getWidth(),this.getWidthText(),this.getHeight(),this.getHeight());
	}
	
	@Override
	public void handleUnselect() {
		this.leftText = this.leftText + this.selectedText + this.rightText;
		this.setSelectedText("");
		this.setRigthText("");
	}

	/**
	 * @return this.name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new value of this.name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns this TextBox under string format.
	 */
	@Override
	public String toString() {
		return this.getName() +"=" + this.getText();
	}

	/**
	 * @return the cursorOnTheRightOfSelectedText
	 */
	private Boolean cursorOnTheRightOfSelectedText() {
		return cursorOnTheRightOfSelectedText;
	}

	@Override
	public void scrollBarMoved(double ratio,Direction direction) {
		if(direction== Direction.HORIZONTAL) {
			int newOffset = (int)( ratio*this.getWidthText());
			this.setxOffset(-newOffset);
		}
	}
	
	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset + CONSTANTYOFFSET;
	}
	
	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset +CONSTANTXOFFSET;
	}
	
}