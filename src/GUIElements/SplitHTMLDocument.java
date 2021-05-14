package GUIElements;

import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.SeparatorBarMoveListener;

public class SplitHTMLDocument extends Pane implements SeparatorBarMoveListener{
	
	private Pane leftPanel;
	private Pane rightPanel;
	private boolean activeOnLeft = true;
	private SeperatorBar bar;
	private Direction dir;

	public SplitHTMLDocument(HTMLDocument original, Direction direction) {
		super(original.getX(), original.getY(), original.getWidth(), original.getHeight());
		
		this.dir = direction;
		
		if (direction == Direction.VERTICAL) {
			initVerticalPanels(original);
		} else {
			initHorizontalPanels(original);
		}
		
		int[] middleContainer = new int[] {this.getWidth()/2,this.getHeight()/2};
		this.bar = new SeperatorBar(this, middleContainer, direction);
		this.bar.addSeparatorBarMoveListener(this);
	}

	private void initHorizontalPanels(HTMLDocument original) {
		HTMLDocument tempLeftPanel = original.copy();
		HTMLDocument tempRightPanel = original.copy();
		
		int heightLeftPanel = Math.floorDiv(getHeight(), 2);
		
		tempLeftPanel.setX(0);
		tempLeftPanel.setY(0);
		
		tempRightPanel.setX(0);
		tempRightPanel.setY(heightLeftPanel);
		
		tempLeftPanel.setHeight(heightLeftPanel);
		tempRightPanel.setHeight(heightLeftPanel);
		
		tempLeftPanel.setActive(true);
		
		tempLeftPanel.redraw();
		tempRightPanel.redraw();
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
	}

	private void initVerticalPanels(HTMLDocument original) {
		HTMLDocument tempLeftPanel = original.copy();
		HTMLDocument tempRightPanel = original.copy();
		
		int widthPanel = Math.floorDiv(getWidth(), 2);
		
		tempLeftPanel.setX(0);
		tempLeftPanel.setY(0);
		
		tempRightPanel.setX(widthPanel);
		tempRightPanel.setY(0);

		tempLeftPanel.setWidth(widthPanel);
		tempRightPanel.setWidth(widthPanel);
		
		tempLeftPanel.setActive(true);
		
		tempLeftPanel.redraw();
		tempRightPanel.redraw();
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
	}

	@Override
	public HTMLDocument getActiveHTMLDocument() {
		if (activeOnLeft) {
			return leftPanel.getActiveHTMLDocument();
		} else {
			return rightPanel.getActiveHTMLDocument();
		}
	}

	@Override
	public void changeActiveHTMLDocument(int x, int y) {
		int xRel = x - getX();
		int yRel = y - getY();
		
		resetActiveHTMLDocument();
		
		if (leftPanel.containsPoint(xRel, yRel))
			leftPanel.changeActiveHTMLDocument(xRel, yRel);
		else {
			rightPanel.changeActiveHTMLDocument(xRel, yRel);
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
		Graphics newG =  g.create(getX(), getY(), getWidth(), getHeight());
		
		leftPanel.paint(newG);
		rightPanel.paint(newG);
		
		
		bar.paint(newG);
	}

	@Override
	public Pane deleteActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel = leftPanel.deleteActiveHTMLDocument();
		else 
			rightPanel = rightPanel.deleteActiveHTMLDocument();
		
		if (leftPanel == null) {			
			rightPanel.updateWidth(getX(), getWidth());
			rightPanel.updateHeight(getY(), getHeight());
			
			
			rightPanel.setActive(true);
			return rightPanel;
		}
		
		if (rightPanel == null) {
			leftPanel.updateWidth(getX(), getWidth());
			leftPanel.updateHeight(getY(), getHeight());
			
			leftPanel.setActive(true);
			return leftPanel;
		}
		
		return this;
	}
	
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		int xRel = x - getX();
		int yRel = y - getY();
		
		if (bar.containsPoint(xRel, yRel)) {
			return bar;
		}
		
		this.changeActiveHTMLDocument(x, y);
		
		if (activeOnLeft) {
			return leftPanel.getGUIAtPosition(xRel, yRel);
		} else {
			return rightPanel.getGUIAtPosition(xRel, yRel);
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
	
	public void barMoved() {
		if (dir == Direction.VERTICAL) {
			moveVerticalBar();
		} else {
			moveHorizontalBar();
		}
	}
	

	public void moveVerticalBar() {
		int widthLeftPanel = this.bar.getX();
		int widthRightPanel = this.getWidth() - widthLeftPanel;
		
		leftPanel.updateWidth(0, widthLeftPanel);
		rightPanel.updateWidth(widthLeftPanel, widthRightPanel);
	}
	
	@Override
	public void updateWidth(int newXPos, int newWidth) {
		this.setX(newXPos);
		this.setWidth(newWidth);
		
		if (dir == Direction.VERTICAL) {
			int widthLeftPanel = newWidth - rightPanel.getWidth();
			leftPanel.updateWidth(0, widthLeftPanel);
			bar.setX(widthLeftPanel);
			rightPanel.updateWidth(widthLeftPanel, this.getWidth() - widthLeftPanel);
		} else {
			leftPanel.updateWidth(0, newWidth);
			rightPanel.updateWidth(0, newWidth);
		}
		
	}
	
	public void moveHorizontalBar() {
		int heightLeftPanel = this.bar.getY();
		int heightRightPanel = this.getHeight() - heightLeftPanel;
		
		leftPanel.updateHeight(0, heightLeftPanel);
		rightPanel.updateHeight(heightLeftPanel, heightRightPanel);
	}
	
	@Override
	public void updateHeight(int newYPos, int newHeight) {
		this.setY(newYPos);
		this.setHeight(newHeight);
		
		if (dir == Direction.VERTICAL) {
			int heightLeftPanel = newHeight - rightPanel.getHeight();
			leftPanel.updateHeight(0, heightLeftPanel);
			bar.setX(heightLeftPanel);
			rightPanel.updateHeight(heightLeftPanel, this.getHeight() - heightLeftPanel);
		} else {
			leftPanel.updateHeight(0, newHeight);
			rightPanel.updateHeight(0, newHeight);
		}
		
	}
}