package GUIElements;

public class ScrollableTextBox extends Container {
	
	public static final int DEFAULT_SCROLLBAR_SMALL_SIZE = 25;
	private TextBox textBox;
	
	public ScrollableTextBox(int x, int y, int w, int h) {
		super(x,y,w,h+10);
		this.textBox = new TextBox(x, y, w, h);
		ScrollBar scrollBar = new ScrollBar(Direction.HORIZONTAL, x, textBox.getHeight(), w, 10, textBox.getWidth(), textBox.getWidthText());
		textBox.addAdjustListener(scrollBar);
		scrollBar.addScrollBarListener(textBox);
		this.addElement(textBox);
		this.addElement(scrollBar);
		
	}
	
	public ScrollableTextBox(int x, int y,TextBox textBox) {
		super(x,y,textBox.getWidth(),textBox.getHeight()+10);
		this.textBox = textBox;
		ScrollBar scrollBar = new ScrollBar(Direction.HORIZONTAL, textBox.getX(), textBox.getHeight(), this.getWidth(), 10, textBox.getWidth(), textBox.getWidthText());
		textBox.addAdjustListener(scrollBar);
		scrollBar.addScrollBarListener(textBox);
		this.addElement(textBox);
		this.addElement(scrollBar);
	}

	public TextBox getTextBox() {
		return this.textBox;
	}

}


