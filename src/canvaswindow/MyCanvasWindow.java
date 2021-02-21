package canvaswindow;

import java.awt.Graphics;
import java.awt.event.MouseEvent; // geen idee als deze import mag
import java.util.ArrayList;

import browsrhtml.HtmlLexer.TokenType;
import htmlElement.Button;
import htmlElement.GUIElement;
import htmlElement.Text;
import main.InputReader;


public class MyCanvasWindow extends CanvasWindow {
	
	private InputReader fileReader;
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	//TODO test must be given from input file
	public MyCanvasWindow(String title) {
		super(title);
		fileReader = new InputReader(this);
		fileReader.readFile("src/input.txt");
		Button button = new Button(40, 40 * 12, 40, "Click me");
		elements.add(button);
		buttons.add(button);

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
	
	/**
	 * De inhoud van deze functie is tijdelijk. Vooral gemaakt om
	 * buttons werkend te krijgen
	 */
	@Override
	public void handleMouseEvent(int id, int x, int y, int clickCount){
		// deze for loop gaat alle buttons af en checkt of er geen
		// event zich afspeelt in de buttons
		buttons.forEach((n) -> {
			if (n.checkCoordinates(x, y) && id == MouseEvent.MOUSE_CLICKED) {
				elements.clear();
				buttons.forEach((i) -> elements.add(i)); //buttons werden ook verwijdert uit elements dus moeten openieuw toegevoegd worden
				setIncrement(0); // die lelijke variable incrementen (pls doe dit weg)
				fileReader.readFile("src/input2.txt"); //deze button lees de andere file in
				repaint(); //opnieuw het scherm drawen
			}
		});
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
