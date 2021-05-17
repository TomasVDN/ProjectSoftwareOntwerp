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
			this.bar = new SeperatorBar(this, this.getWidth()/2, direction);
		} else {
			initHorizontalPanels(original);
			this.bar = new SeperatorBar(this, this.getHeight()/2, direction);
		}
		this.bar.addSeparatorBarMoveListener(this);
	}

	private void initHorizontalPanels(HTMLDocument original) {
		HTMLDocument tempLeftPanel = original;
		HTMLDocument tempRightPanel = original.copy();
		
		int heightLeftPanel = Math.floorDiv(getHeight(), 2);
		
		tempLeftPanel.setX(0);
		tempLeftPanel.setY(0);
		
		tempRightPanel.setX(0);
		tempRightPanel.setY(heightLeftPanel);
		
		tempLeftPanel.setHeight(heightLeftPanel);
		tempRightPanel.setHeight(heightLeftPanel);
		
		//tempLeftPanel.redraw();
		//tempRightPanel.redraw();
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
	}

	private void initVerticalPanels(HTMLDocument original) {
		HTMLDocument tempLeftPanel = original;
		HTMLDocument tempRightPanel = original.copy();
		
		int widthPanel = Math.floorDiv(getWidth(), 2);
		
		tempLeftPanel.setX(0);
		tempLeftPanel.setY(0);
		
		tempRightPanel.setX(widthPanel);
		tempRightPanel.setY(0);

		tempLeftPanel.setWidth(widthPanel);
		tempRightPanel.setWidth(widthPanel);
		
		//tempLeftPanel.redraw();
		//tempRightPanel.redraw();
		
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
			rightPanel.updateRightClosestChildWidth(getX(), getWidth());
			rightPanel.updateRightClosestChildHeight(getY(), getHeight());//TODO ziet mij er lelijk uit
			rightPanel.setActive(true);
			rightPanel.updateAllBars();
			return rightPanel;
		}
		
		if (rightPanel == null) {
			leftPanel.updateRightClosestChildWidth(getX(), getWidth());
			leftPanel.updateRightClosestChildHeight(getY(), getHeight());
			
			leftPanel.setActive(true);
			leftPanel.updateAllBars();
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
	public void addMultipleElements(ArrayList<GUIElement> guiList) {
		this.getActiveHTMLDocument().addMultipleElements(guiList);
	}
	
	/**
	 * Update all the bars to get the correct width and height
	 */
	@Override
	public void updateAllBars(){
		bar.updateBar();
		leftPanel.updateAllBars();
		rightPanel.updateAllBars();
	}
	
	/**
	 * The action that has to be performed if this class seperator bar has moved
	 */
	@Override
	public void barMoved() {
		if (dir == Direction.VERTICAL) {
			moveVerticalBar();
		} else {
			moveHorizontalBar();
		}
		updateAllBars();
	}
	

	/**
	 * When the vertical bar is moved the closest childs changes their position and width
	 * The rest remains unchanged
	 */
	public void moveVerticalBar() {
		int widthLeftPanel = this.bar.getX();
		int widthRightPanel = this.getWidth() - widthLeftPanel;
		leftPanel.updateLeftClosestChildWidth(0, widthLeftPanel);
		rightPanel.updateRightClosestChildWidth(widthLeftPanel, widthRightPanel);

	}
	

	/**
	 * This method makes sure that only the most rightChild changes, the absolute value
	 * of the other childs must remain the same. The rightpanel x coordinate has to change accordingly
	 */
	@Override
	public void updateRightClosestChildWidth(int newXPos, int newWidth) {//TODO het kan ook zijn dat rechterkant groter wordt
		this.setX(newXPos);
		this.setWidth(newWidth);
		if (dir == Direction.VERTICAL) {
			int widthLeftPanel = newWidth - rightPanel.getWidth();
			leftPanel.updateRightClosestChildWidth(0, widthLeftPanel);
			bar.changeXPosition(widthLeftPanel);
			rightPanel.setX(widthLeftPanel);
		} else {
			leftPanel.updateRightClosestChildWidth(0, newWidth);
			rightPanel.updateRightClosestChildWidth(0, newWidth);
		}
	}
	
	/**
	 * This method makes sure that only the most leftChild changes, the absolute value
	 * of the other childs must remain the same. 
	 */
	@Override
	public void updateLeftClosestChildWidth(int newXPos, int newWidth) {//TODO het kan ook zijn dat rechterkant groter wordt
		this.setX(newXPos);
		this.setWidth(newWidth);
		if (dir == Direction.VERTICAL) {
			int widthRigthPanel = newWidth - leftPanel.getWidth();
			int widthLeftPanel = leftPanel.getWidth();
			rightPanel.updateLeftClosestChildWidth(widthLeftPanel, widthRigthPanel);
		} else {
			leftPanel.updateLeftClosestChildWidth(0, newWidth);
			rightPanel.updateLeftClosestChildWidth(0, newWidth);
		}
	}
	
	/**
	 * When the horizontal bar is moved the closest childs changes their position and width
	 * The rest remains unchanged
	 */
	public void moveHorizontalBar() {
		int heightLeftPanel = this.bar.getY();
		int heightRightPanel = this.getHeight() - heightLeftPanel;
		leftPanel.updateLeftClosestChildHeight(0, heightLeftPanel);
		rightPanel.updateRightClosestChildHeight(heightLeftPanel, heightRightPanel);

	}
	
	/**
	 * This method makes sure that only the most rightChild changes, the absolute value
	 * of the other childs must remain the same. 
	 */
	@Override
	public void updateRightClosestChildHeight(int newYPos, int newHeight) {
		this.setY(newYPos);
		this.setHeight(newHeight);
		if (dir == Direction.HORIZONTAL) {
			int heightLeftPanel = newHeight-rightPanel.getHeight();
			leftPanel.updateRightClosestChildHeight(0, heightLeftPanel);
			bar.changeYPosition(heightLeftPanel);
			rightPanel.setY(heightLeftPanel);
			//rightPanel.updateRightClosestChildHeight(heightLeftPanel, this.getHeight() - heightLeftPanel);
		} else {
			leftPanel.updateRightClosestChildHeight(0, newHeight);
			rightPanel.updateRightClosestChildHeight(0, newHeight);
		}
		
	}

	/**
	 * This method makes sure that only the most leftChild changes, the absolute value
	 * of the other childs must remain the same. 
	 */
	@Override
	public void updateLeftClosestChildHeight(int newYPos, int newHeight) {
		this.setY(newYPos);
		this.setHeight(newHeight);
		if (dir == Direction.HORIZONTAL) {
			int heightLeftPanel = leftPanel.getHeight();
			rightPanel.updateLeftClosestChildHeight(heightLeftPanel, newHeight - heightLeftPanel);
		} else {
			leftPanel.updateLeftClosestChildHeight(0, newHeight);
			rightPanel.updateLeftClosestChildHeight(0, newHeight);
		}
	}

	@Override
	public HTMLDocument setHTMLDocumentActive(int x, int y) {
		int xRel = x - getX();
		int yRel = y - getY();
		
		if(leftPanel.containsPoint(xRel, yRel)) {
			this.activeOnLeft =true;
			return leftPanel.setHTMLDocumentActive(xRel, yRel);
		}
		else if (rightPanel.containsPoint(xRel, yRel)) {
			this.activeOnLeft =false;
			return rightPanel.setHTMLDocumentActive(xRel, yRel);
		}
		// not clicked on any panel, don't change anything
		return null;
	}
}