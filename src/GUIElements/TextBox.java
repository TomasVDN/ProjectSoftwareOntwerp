package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.KeyEvent;

public class TextBox extends GUIElement {

	private String leftText = "", rightText = "";
	private String previousText = "";
	private String selectedText = "";
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	
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
	public void setLeftText(String leftText) {
		if (leftText == null) {
			return;
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
			return;
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
	 * @return the previousText
	 */
	public String getPreviousText() {
		return previousText;
	}

	/**
	 * @param previousText - the new value of this.previousText to set
	 */
	public void setPreviousText(String previousText) {
		if (rightText == null) {
			return;
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
			this.unselectAllText();
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
		case 10://enter
			this.handleEnter();
			break;
		default:
			if (keyChar != KeyEvent.CHAR_UNDEFINED) {
				this.handleUndefined(keyChar);
			}
			break;
		}
	}
	
	/**
	 * Paints all the components of this textBox.
	 */
	@Override
	public void paint(Graphics g, int xContainer, int yContainer) {
		g.setFont(font);
		FontMetrics metrics =  g.getFontMetrics(g.getFont());
		
		//content
		if (isActive()) {
			g.setColor(Color.gray);
		} else { 
			g.setColor(Color.white);
		}
		g.fillRect(super.getX() + xContainer,super.getY() + yContainer, super.getWidth(), super.getHeight());
		g.setColor(Color.black);
					
		//Border
		g.drawRect(super.getX() + xContainer,super.getY() + yContainer, super.getWidth(), super.getHeight());
		
		int y = this.getY() + yContainer +  ((this.getHeight() - metrics.getHeight()) / 2);
		
		
		//Text
		Shape oldClip = g.getClip();
		g.setClip(getX() + xContainer, getY() + yContainer, getWidth(), getHeight());
		if (selectedText != "") {
			g.setColor(Color.blue);
			g.fillRect(super.getX()+10 + xContainer,y, metrics.stringWidth(getSelectedText()) + 10, metrics.getHeight());
			g.setColor(Color.black);
		}
		
		y = this.getY() + yContainer +  ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
		g.drawString(this.getText(), super.getX()+10+ xContainer, y);
	
		
		//cursor
		if (isActive()) {
			y = this.getY() + yContainer +  ((this.getHeight() - metrics.getHeight()) / 2);
			g.fillRect(super.getX() + xContainer + metrics.stringWidth(getLeftText()) + 10, y, metrics.getHeight() / 10, metrics.getHeight());
		}
			
		g.setClip(oldClip);
	}
	
	/**
	 * Does the needed actions for the enter key.
	 */
	void handleEnter() {
		this.setActive(false);// gewone textbox gaat inactief worden bij enter
	}
	
	/**
	 * Does the needed actions for the backSpace key.
	 */
	private void handleBackSpace() {
		deleteAllSelected();
		if (this.getLeftText().length() > 0) { // check if left string is not empty
			this.setLeftText(this.getLeftText().substring(0, this.getLeftText().length() - 1));
		}
	}
	
	/**
	 * Does the needed actions for the delete key.
	 */
	private void handleDelete() {
		deleteAllSelected();
		if (this.getRightText().length() > 0) { // check if right string is not empty
			this.setRigthText(this.getRightText().substring(1));
		}
	}
	
	/**
	 * Does the needed actions for the Left arrow key.
	 */
	private void handleLeft() {
		if (this.getLeftText().length() != 0) {
			rightText = getLeftText().charAt(getLeftText().length() - 1) + getRightText();
			leftText = getLeftText().substring(0, getLeftText().length() - 1);
		}
	}
	
	/**
	 * Does the needed actions for the Right arrow key.
	 */
	private void handleRight() {
		this.unselectAllText();
		if (this.getRightText().length() != 0) {
			leftText = getLeftText() + getRightText().charAt(0);
			rightText = getRightText().substring(1);
		}
	}

	/**
	 * Does the needed actions for the End key.
	 */
	private void handleEnd() {
		this.unselectAllText();
		leftText = getLeftText() + getRightText();
		rightText = "";
	}
	
	/**
	 * Does the needed actions for the home key.
	 */
	private void handleHome() {
		this.unselectAllText();
		rightText = getLeftText() + getRightText();
		leftText = "";
	}
	
	/**
	 * Does the needed actions for the undefined keys.
	 */
	private void handleUndefined(char keyChar) {
		deleteAllSelected();
		leftText = getLeftText() + keyChar;
	}
		
	/**
	 * Does the needed actions for the escape key.
	 */
	private void handleEscape() {
		setActive(false);
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
		this.selectedText = selectedText;
	}
	
	/**
	 * Selects all the content in textBox
	 */
	private void selectAll() {
		this.setSelectedText(this.getLeftText() + this.getSelectedText() + this.getRightText());
		this.leftText = "";
		this.rightText = "";
	}
	
	/**
	 * Delete all the content of selected text.
	 */
	private void deleteAllSelected() {
		this.selectedText = "";
	}

	/**
	 * Unselect all text.
	 */
	protected void unselectAllText() {
		setRigthText(selectedText + rightText); 
		this.selectedText = "";
	}
	
	/**
	 * Sets the content of the TextBox to the given value.
	 * @param text - the new content
	 */
	public void replaceBox(String text) {
		if (text == null) {
			return;
		}
		this.setLeftText(text);
		this.setSelectedText("");
		this.setRigthText("");
	}
	
	@Override
	public void handleUnselect() {
	}
}