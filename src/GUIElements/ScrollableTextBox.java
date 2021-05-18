package GUIElements;

public class ScrollableTextBox extends Container {
	
	public static final int DEFAULT_SCROLLBAR_SMALL_SIZE = 25;
	private TextBox textBox;
	
	public ScrollableTextBox(int x, int y, int w, int h) {
		super(x,y,w,h+10);
		TextBox textbox= new TextBox(x, y, w, h);
		ScrollBar scrollBar = new ScrollBar(Direction.HORIZONTAL, x, textbox.getHeight(), w, 10, textbox.getWidth(), textbox.getWidthText());
		textbox.addAdjustListener(scrollBar);
		scrollBar.addScrollBarListener(textbox);
		this.addElement(textbox);
		this.addElement(scrollBar);
	}
	
	public ScrollableTextBox(int x, int y,TextBox textbox) {
		super(x,y,textbox.getWidth(),textbox.getHeight()+10);
		ScrollBar scrollBar = new ScrollBar(Direction.HORIZONTAL, textbox.getX(), textbox.getHeight(), this.getWidth(), 10, textbox.getWidth(), textbox.getWidthText());
		textbox.addAdjustListener(scrollBar);
		scrollBar.addScrollBarListener(textbox);
		this.addElement(textbox);
		this.addElement(scrollBar);
	}

	public TextBox getTextBox() {
		return textBox;
	}

}


