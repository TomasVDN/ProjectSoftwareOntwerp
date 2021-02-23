package htmlElement;

import java.awt.Color;
import java.awt.Graphics;

public class textCursor extends Box {
	
	public textCursor(int x, int y, int w, int h, Color color,String leftText,String rigthText,Text textOfBox) {
		super(x, y, w, h, color);
		// TODO Auto-generated constructor stub
	}
	private String leftText;
	private String rigthText;
	private boolean textCursorOn;
	private Text textOfBox;
	
	
	
	@Override
	public void paint(Graphics g) {
		if(this.isTextCursorOn()) {
			this.paint(g);
		}
	}
	
	public void moveRigth() {
		
	}
	
	
	public String getLeftText() {
		return leftText;
	}

	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}

	public String getRigthText() {
		return rigthText;
	}

	public void setRigthText(String rigthText) {
		this.rigthText = rigthText;
	}

	public boolean isTextCursorOn() {
		return textCursorOn;
	}

	public void setTextCursorOn(boolean textCursorOn) {
		this.textCursorOn = textCursorOn;
	}
	



}
