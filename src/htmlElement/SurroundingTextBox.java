package htmlElement;

import java.awt.Color;
import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;

public class SurroundingTextBox extends Box {

	private Text text;
	
	
	public SurroundingTextBox(int x, int y, int w, int h, Color color, Text newText) {
		super(x, y, w, h, color);
		this.setText(newText);
	}

	/**
	 * When method is called the blue box of the selected text gets the coordinates and the size of the text
	 */
	public void selectGivenText(Text text) {
		this.setText(text);
		this.setX(this.getText().getLeftX());
		this.setY(this.getText().getUpperY());
		this.setHeight(this.getText().getHeight());
		this.setWidth(this.getText().getWidth());
	}
	
	/**
	 * When method is called the blue box dissapears
	 */
	public void unselectAllText() {
		this.setHeight(0);
		this.setWidth(0);
	}
	
	/**
	 * Clears all selected Text
	 */
	public void clearSelected() {
		if(this.getText()!=null) {
			this.getText().clearText();
			this.unselectAllText();
		}
	}
	@Override
	public void update(Graphics g) {
		this.setX(this.getText().getLeftX());
		this.setY(this.getText().getUpperY());
		this.setHeight(this.getText().getHeight());
		this.setWidth(this.getText().getWidth());
	}
	
	

	public Text getText() {
		return text;
	}


	public void setText(Text text) {
		this.text = text;
	}

}