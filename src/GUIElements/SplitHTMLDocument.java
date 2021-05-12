package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class SplitHTMLDocument extends Pane {
	
	private Pane leftPanel;
	private Pane rightPanel;
	private boolean activeOnLeft = true;

	public SplitHTMLDocument(HTMLDocument original) { //TODO vertical
		super(original.getX(), original.getY(), original.getWidth(), original.getHeight());
		leftPanel = original;
		leftPanel.setWidth(Math.floorDiv(getWidth(), 2));
		leftPanel.setActive(true);
		rightPanel = new HTMLDocument(getX() + Math.floorDiv(getWidth(), 2), getY(), Math.floorDiv(getWidth(), 2), getHeight(), original.getUrl(), original.getHTMLCode());
	}

	@Override
	public Pane getActiveHTMLDocument() {
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
	public Pane splitActiveHTMLDocument() {
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
	public Pane deleteActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel = leftPanel.deleteActiveHTMLDocument();
		else 
			rightPanel = rightPanel.deleteActiveHTMLDocument();
		
		if (leftPanel == null) {
			rightPanel.setX(getX());
			rightPanel.updateWidth(getWidth());
			rightPanel.setActive(true);
			return rightPanel;
		}
		
		if (rightPanel == null) {
			leftPanel.setActive(true);
			leftPanel.updateWidth(getWidth());
			return leftPanel;
		}
		
		return this;
	}
	
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		this.changeActiveHTMLDocument(x, y);
		
		if (activeOnLeft) {
			return leftPanel.getGUIAtPosition(x, y);
		} else {
			return rightPanel.getGUIAtPosition(x, y);
		}
	}
	
	@Override
	public void addElement(GUIElement element) {
		this.getActiveHTMLDocument().addElement(element);
	}
	
	@Override
	public void resetAllElements(ArrayList<GUIElement> guiList) {
		this.getActiveHTMLDocument().resetAllElements(guiList);
	}
	
	@Override
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		this.getActiveHTMLDocument().addMultipleElements(guiList);
	}

	@Override
	public void updateWidth(int width) {
		this.setWidth(width);
		
		int panelWidth = Math.floorDiv(width, 2);
		int xRight = getX() + panelWidth;
		leftPanel.setX(getX());
		rightPanel.setX(xRight);
		
		leftPanel.updateWidth(panelWidth);
		rightPanel.updateWidth(panelWidth);
	}
}