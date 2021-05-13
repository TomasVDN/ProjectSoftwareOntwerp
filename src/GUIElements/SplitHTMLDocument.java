package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

public class SplitHTMLDocument extends Pane {
	
	private Pane leftPanel;
	private Pane rightPanel;
	private boolean activeOnLeft = true;
	private SeperatorBar bar;
	private Direction dir;

	public SplitHTMLDocument(HTMLDocument original, Direction direction) { //TODO vertical
		super(original.getX(), original.getY(), original.getWidth(), original.getHeight());
		
		this.dir = direction;
		
		if (direction == Direction.VERTICAL) {
			initVerticalPanels(original);
		} else {
			initHorizontalPanels(original);
		}
		
//		int[] middleContainer = new int[] {this.getWidth()/2,this.getHeight()/2};
//		this.bar = new SeperatorBar(this, middleContainer, direction);		
	}

	private void initHorizontalPanels(HTMLDocument original) {
		HTMLDocument tempLeftPanel = original;
		tempLeftPanel.setHeight(Math.floorDiv(getHeight(), 2));
		tempLeftPanel.setActive(true);
		HTMLDocument tempRightPanel = new HTMLDocument(getX(), getY() + Math.floorDiv(getHeight(), 2), getWidth(), Math.floorDiv(getHeight(), 2), original.getUrl(), original.getHTMLCode());
	
		original.getListeners().forEach(listener -> tempRightPanel.addRedrawListener(listener));
		
		tempLeftPanel.redraw();
		tempRightPanel.redraw();
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
	}

	private void initVerticalPanels(HTMLDocument original) {
		HTMLDocument tempLeftPanel = original;
		tempLeftPanel.setWidth(Math.floorDiv(getWidth(), 2));
		tempLeftPanel.setActive(true);
		HTMLDocument tempRightPanel = new HTMLDocument(getX() + Math.floorDiv(getWidth(), 2), getY(), Math.floorDiv(getWidth(), 2), getHeight(), original.getUrl(), original.getHTMLCode());
	
		original.getListeners().forEach(listener -> tempRightPanel.addRedrawListener(listener));
		
		tempLeftPanel.redraw();
		tempRightPanel.redraw();
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
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
	public Pane splitActiveHTMLDocumentVertical() {
		if (activeOnLeft)
			leftPanel = leftPanel.splitActiveHTMLDocumentVertical();
		else
			rightPanel = rightPanel.splitActiveHTMLDocumentVertical();
		
		return this;
	}
	
	@Override
	public Pane splitActiveHTMLDocumentHorizontal() {
		if (activeOnLeft)
			leftPanel = leftPanel.splitActiveHTMLDocumentHorizontal();
		else
			rightPanel = rightPanel.splitActiveHTMLDocumentHorizontal();
		
		return this;
	}

	@Override
	public void paint(Graphics g) {
		leftPanel.paint(g);
		rightPanel.paint(g);
		Graphics newG = g.create(getX(), getY(), getWidth()+1, getHeight()+1);
		//bar.paint(newG);
	}

	@Override
	public Pane deleteActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel = leftPanel.deleteActiveHTMLDocument();
		else 
			rightPanel = rightPanel.deleteActiveHTMLDocument();
		
		if (leftPanel == null) {
			if (dir == Direction.VERTICAL) {
				rightPanel.setX(getX());
				rightPanel.updateWidth(getWidth());
				rightPanel.updateHeight(getHeight());
			} else {
				rightPanel.setY(getY());
				rightPanel.updateWidth(getWidth());
				rightPanel.updateHeight(getHeight());
			}
			
			rightPanel.setActive(true);
			return rightPanel;
		}
		
		if (rightPanel == null) {
			if (dir == Direction.VERTICAL) {
				leftPanel.updateWidth(getWidth());
				leftPanel.updateHeight(getHeight());
			} else {
				leftPanel.updateWidth(getWidth());
				leftPanel.updateHeight(getHeight());
			}
			
			leftPanel.setActive(true);
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
	public void resetAllElements(ArrayList<GUIElement> guiList, String path, String code) {
		this.getActiveHTMLDocument().resetAllElements(guiList, path, code);
	}
	
	@Override
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		this.getActiveHTMLDocument().addMultipleElements(guiList);
	}

	@Override
	public void updateWidth(int width) {
		this.setWidth(width);
		
		if (dir == Direction.VERTICAL) {
			int panelWidth = Math.floorDiv(width, 2);
			int xRight = getX() + panelWidth;
			leftPanel.setX(getX());
			rightPanel.setX(xRight);
			
			leftPanel.updateWidth(panelWidth);
			rightPanel.updateWidth(panelWidth);
		} else {
			leftPanel.updateWidth(width);
			rightPanel.updateWidth(width);
		}
	}

	@Override
	public void updateHeight(int height) {
		this.setHeight(height);
		
		if (dir == Direction.HORIZONTAL) {
			int panelHeight = Math.floorDiv(height, 2);
			int yRight = getY() + panelHeight;
			leftPanel.setY(getY());
			rightPanel.setY(yRight);
			
			leftPanel.updateHeight(panelHeight);
			rightPanel.updateHeight(panelHeight);
		} else {
			leftPanel.updateHeight(height);
			rightPanel.updateHeight(height);
		}
	}
	
	@Override
	public void setX(int x) {
		super.setX(x);
		
		if (dir == Direction.VERTICAL) {
			leftPanel.setX(x);
			int xRight = getX() + Math.floorDiv(getWidth(), 2);
			rightPanel.setX(xRight);
		} else {
			leftPanel.setX(x);
			rightPanel.setX(x);
		}
	}
	
	@Override
	public void setY(int y) {
		super.setY(y);
		
		if (dir == Direction.HORIZONTAL) {
			leftPanel.setY(y);
			int xRight = getY() + Math.floorDiv(getHeight(), 2);
			rightPanel.setY(xRight);
		} else {
			leftPanel.setY(y);
			rightPanel.setY(y);
		}
	}
}