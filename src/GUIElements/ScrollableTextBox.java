package GUIElements;

/**
 * Class that implements the scrolling feature for the TextBox. It contains a TextBox and one scrollbar.
 * All uncommented methods are redirections to the TextBox methods.
 */
public class ScrollableTextBox extends Container {
	
	public static final int DEFAULT_SCROLLBAR_SMALL_SIZE = 10;
	private TextBox textBox;
	
	/**
	 * Constructor of the ScrollableTextBox class. 
	 * @param x - x coordinate of the ScrollableTextBox
	 * @param y - y coordinate of the ScrollableTextBox
	 * @param w - width of the ScrollableTextBox
	 * @param h - height of the ScrollableTextBox
	 */
	public ScrollableTextBox(int x, int y, int w, int h) {
		super(x,y,w,h+DEFAULT_SCROLLBAR_SMALL_SIZE);
		this.textBox = new TextBox(x, y, w, h);
		ScrollBar scrollBar = new ScrollBar(Direction.HORIZONTAL, x, textBox.getHeight(), w, DEFAULT_SCROLLBAR_SMALL_SIZE, textBox.getWidth(), textBox.getWidthText());
		textBox.addAdjustListener(scrollBar);
		scrollBar.addScrollBarListener(textBox);
		this.addElement(textBox);
		this.addElement(scrollBar);
		
	}
	
	/**
	 * Alternative constructor of the ScrollableTextBox class that doesn't need width and height. 
	 * @param x - x coordinate of the ScrollableTextBox
	 * @param y - y coordinate of the ScrollableTextBox
	 * @param textBox - Textbox contained in this ScrollableTextBox
	 */
	public ScrollableTextBox(int x, int y,TextBox textBox) {
		super(x,y,textBox.getWidth(),textBox.getHeight()+DEFAULT_SCROLLBAR_SMALL_SIZE);
		this.textBox = textBox;
		ScrollBar scrollBar = new ScrollBar(Direction.HORIZONTAL, textBox.getX(), textBox.getHeight(), this.getWidth(), DEFAULT_SCROLLBAR_SMALL_SIZE, textBox.getWidth(), textBox.getWidthText());
		textBox.addAdjustListener(scrollBar);
		scrollBar.addScrollBarListener(textBox);
		this.addElement(textBox);
		this.addElement(scrollBar);
	}

	/**
	 * Return the TextBox of this ScrollableTextBox
	 * @return this.textBox
	 */
	public TextBox getTextBox() {
		return this.textBox;
	}

}


