package canvaswindow;

import java.awt.Graphics;
import java.awt.event.MouseEvent; // geen idee als deze import mag
import java.util.ArrayList;
import java.util.Iterator;

import browsrhtml.HtmlLexer.TokenType;
import htmlElement.Button;
import htmlElement.GUIElement;
import htmlElement.Text;
import htmlElement.TextBox;
import inputReader.InputReader;


public class MyCanvasWindow extends CanvasWindow {
	
	private InputReader fileReader;
	private ArrayList<GUIElement> elements = new ArrayList<GUIElement>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<TextBox> textBoxes = new ArrayList<TextBox>();
	
	private SearchBar bar;
	

	//TODO test must be given from input file
	public MyCanvasWindow(String title) {
		super(title);
		fileReader = new InputReader(this);
		/*Button button = new Button(40, 40 * 12, 40, "Click me");
		elements.add(button);
		buttons.add(button);*/
		SearchBar bar = new SearchBar(this);
        this.addTextBox(bar);
        this.addElement(bar);
        
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * adds the given element to the element list
	 * @param elem
	 */
	public void addElement(GUIElement elem) {
		elements.add(elem);
	}
	
	/**
	 * removes the first of the given element from the element list
	 * @param elem
	 */
	public void removeElement(GUIElement elem) {
		Boolean found = false;
		int i =0;
		while (!found && i<elements.size()) {
			if(elements.get(i)== elem) {
				elements.remove(i);
				found=true;
			}
		}
	}
	
	/**
	 * removes all elements in list
	 */
	public void clearElements() {
		elements.clear();
	}
	
	/**
	 * adds the given element to the element list
	 * @param elem
	 */
	public void addTextBox(TextBox textBox) {
		textBoxes.add(textBox);
	}
	
	/**
	 * Returns the InputReader of this class
	 * 
	 * @return this.fileReader
	 */
	public InputReader getReader() {
		return this.fileReader;
	}
	
	@Override
	protected void handleShown() {
		repaint();
	}
	
	@Override
	protected void paint(Graphics g) {
		for(int i =0 ; i< this.elements.size();i++) {
			elements.get(i).update(g);
		}
		elements.forEach((n) -> n.paint(g));
	}
	
	@Override
	protected void handleResize() {
		repaint();
	}
	
	@Override
	public void handleKeyEvent(int id, int keyCode, char keyChar){
		textBoxes.forEach((n) -> {
			n.handleKeyBoardEvent(id,keyCode, keyChar);
		});
		this.repaint();
	}
	
	/**
	 * TODO
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
		
		textBoxes.forEach((n) -> {
			n.handleMouseEvent(id, x, y);
			
		});
		this.repaint();
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
	
	/**
	 * Method used to open a new page. Expects a path (TODO change to url).
	 * 
	 * @param path - path to the page to open
	 */
	public void readFile(String path) {
		this.clearWindow();
		fileReader.readFile(path);
	}
	
	/**
	 * Clears the entire window and respawns the bar.
	 */
	public void clearWindow() {
		elements = new ArrayList<GUIElement>();
		elements.add(bar);
		setIncrement(0);
	}
	
	public SearchBar getBar() {
		return bar;
	}

	public void setBar(SearchBar bar) {
		this.bar = bar;
	}

}
