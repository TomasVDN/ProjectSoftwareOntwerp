package canvaswindow;

import java.awt.Graphics;
import java.util.ArrayList;

import browsrhtml.HtmlLexer.TokenType;
import htmlElement.GUIElement;
import htmlElement.Text;
import main.InputReader;


public class MyCanvasWindow extends CanvasWindow {
	
	private InputReader fileReader;
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	
	//TODO test must be given from input file
	public MyCanvasWindow(String title) {
		super(title);
		fileReader = new InputReader(this);
		fileReader.readFile("src/input.txt");
	}
	
	public InputReader getReader() {
		return this.fileReader;
	}
	
	@Override
	protected void handleShown() {
		repaint();
	}
	
	@Override
	protected void paint(Graphics g) {
		elements.forEach((n) -> n.paint(g));
	}
	
	@Override
	protected void handleResize() {
		repaint();
	}
	
	@Override
	public void handleKeyEvent(int id, int keyCode, char keyChar){
		
	}
	
	@Override
	public void handleMouseEvent(int id, int x, int y, int clickCount){
		
	}
	
	//TODO remove this utterly disgusting variable
	private int increment = 0;
	
	public void addTextElement(String textToAdd) {
		Text text = new Text(40, 40 + this.increment * 40, 10, 10, textToAdd);
		elements.add(text);
		setIncrement(getIncrement() + 1);
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

}
