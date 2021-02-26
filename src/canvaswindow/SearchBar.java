package canvaswindow;
import java.awt.Color;

import htmlElement.TextBox;

public class SearchBar extends TextBox{
	
	private MyCanvasWindow window;

	public SearchBar(int x, int y, int w, int h, MyCanvasWindow window) {
		super(x, y, w, h);
		this.window = window;
	}
	
	/*
	 * Misschien een constructor maken die de SearchBar altijd op een default plaats zet?
	 * De getallen hieronder zijn maar een voorbeeld en dit kan best afhankelijk zijn van window.
	 */
	public SearchBar(MyCanvasWindow window) {
		super(1, 1, window.getWidth() - 3, 40);
		this.window = window;
	}
	
	/**
	 * Does the needed actions for the enter key.
	 */
	@Override
	protected void handleEnter() {
		this.getWindow().readFile(this.getTextCursor().getTextFromTextCursor().getText()); //TODO getText.getText is niet het mooiste ooit
		this.setActive(false);
		this.getBox().setColor(Color.white);
	}

	private MyCanvasWindow getWindow() {
		return this.window;
	}
}
