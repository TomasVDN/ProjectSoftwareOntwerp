package GUIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import facades.EventReader;

public class TextBox extends GUIElement {

	private String leftText = "", rightText = "";
	private String previousText;
	private String selectedText = "";
	private Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	
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
	 * @param leftText the leftText to set
	 */
	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}

	/**
	 * @return the rightText
	 */
	public String getRightText() {
		return rightText;
	}

	/**
	 * @param rightText the rightText to set
	 */
	public void setRigthText(String rightText) {
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
	 * @param previousText the previousText to set
	 */
	public void setPreviousText(String previousText) {
		this.previousText = previousText;
	}

	@Override
	public void handleClick() {
		if (!isActive()) {
			this.previousText = getText();
			this.selectAll();
			setActive(true);
		} else {
			this.unselectAllText();
		}
		
		new ArrayList<Runnable>(clickListeners).stream().forEach(l -> l.run());
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifier) {
		if (!this.isActive()) {
			return;
		}
		
		//HANDLE ONLY THE KEYS NEEDED FOR TEXT EDITING!!! The rest should be given with a keyboardListener
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

	void handleEnter() {
		this.setActive(false);// gewone textbox gaat inactief worden bij enter
	}

	@Override
	public void handleUnselect() {
		unselectAllText();
		new ArrayList<Runnable>(unselectListener).stream().forEach(l -> l.run());
	}
	
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
		y = this.getY() + yContainer +  ((this.getHeight() - metrics.getHeight()) / 2);
		g.fillRect(super.getX() + xContainer + metrics.stringWidth(getLeftText()) + 10, y, metrics.getHeight() / 10, metrics.getHeight());
		
		g.setClip(oldClip);
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
	
	public void selectAll() {
		this.setSelectedText(this.getLeftText() + this.getSelectedText() + this.getRightText());
		this.leftText = "";
		this.rightText = "";
	}
	
	private void deleteAllSelected() {
		this.selectedText = "";
	}

	protected void unselectAllText() {
		setRigthText(selectedText + rightText); 
		this.selectedText = "";
	}
	
	public void replaceBox(String text) {
		this.setLeftText(text);
		this.setSelectedText("");
		this.setRigthText("");
		
	}
}