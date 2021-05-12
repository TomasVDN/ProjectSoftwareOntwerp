package GUIElements;

public abstract class Panel extends Container {

	public Panel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public abstract Panel getActiveHTMLDocument();
	public abstract void changeActiveHTMLDocument(int x, int y);
	public abstract void resetActiveHTMLDocument();
	public abstract Panel splitActiveHTMLDocument();
	public abstract Panel deleteActiveHTMLDocument();
	
	public boolean inBounds(int x, int y) {
		if (this.getX() < x && x < this.getX() + this.getWidth() && this.getY() < y && y < this.getY() + this.getHeight())
			return true;
		else 
			return false;
	}
}
