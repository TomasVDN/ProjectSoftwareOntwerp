package GUIElements;

import java.awt.Graphics;

public class SplitHTMLDocument extends Panel {
	
	private Panel leftPanel;
	private Panel rightPanel;
	private boolean activeOnLeft = true;

	public SplitHTMLDocument(HTMLDocument original) { //TODO vertical
		super(original.getX(), original.getY(), original.getWidth(), original.getHeight());
		leftPanel = new HTMLDocument(getX(), getY(), Math.floorDiv(getWidth(), 2), getHeight(), original.getUrl(), original.getHTMLCode());
		rightPanel = new HTMLDocument(getX() + Math.floorDiv(getWidth(), 2), getY(), Math.floorDiv(getWidth(), 2), getHeight(), original.getUrl(), original.getHTMLCode());
	}

	@Override
	public Panel getActiveHTMLDocument() {
		if (activeOnLeft) {
			return leftPanel.getActiveHTMLDocument();
		} else {
			return rightPanel.getActiveHTMLDocument();
		}
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		resetActiveHTMLDocument();
		if (leftPanel.inBounds(x, y))
			leftPanel.changeActiveHTMLDocument(x, y);
		else {
			rightPanel.changeActiveHTMLDocument(x, y);
			activeOnLeft = false;
		}
	}

	@Override
	public void resetActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel.resetActiveHTMLDocument();
		else
			rightPanel.resetActiveHTMLDocument();
		activeOnLeft = true;
	}

	@Override
	public Panel splitActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel = leftPanel.splitActiveHTMLDocument();
		else
			rightPanel = rightPanel.splitActiveHTMLDocument();
		
		return this;
	}

	@Override
	public void paint(Graphics g) {
		leftPanel.paint(g);
		rightPanel.paint(g);
	}

	@Override
	public Panel deleteActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel = leftPanel.deleteActiveHTMLDocument();
		else 
			rightPanel = rightPanel.deleteActiveHTMLDocument();
		
		if (leftPanel == null) {
			return rightPanel;
		}
		
		if (rightPanel == null) {
			return leftPanel;
		}
		
		return this;
	}
	
}
