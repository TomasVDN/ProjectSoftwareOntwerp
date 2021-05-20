package GUIElements;

import java.awt.Graphics;
import EventListeners.SeparatorBarMoveListener;

/**
 * This class forms the inner nodes of the HTMLDocument tree.
 * It has each time a direction (Horizontal or Vertical) and 
 * a leftPane and a rightPane. Those panes are RootPanes or SplitHtmlDocument their selves
 *
 */
public class SplitHTMLDocument extends Pane implements SeparatorBarMoveListener{
	
	private Pane leftPanel;
	private Pane rightPanel;
	private boolean activeOnLeft = true;
	private SeperatorBar bar;
	private Direction dir;

	/**
	 * Creates a new splitHTMLDocument
	 * @param original - the leafpane that would be copied on both sides
	 * @param direction - the direction of the seperatorBar between the panes
	 */
	public SplitHTMLDocument(LeafPane original, Direction direction) {
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
		this.activeOnLeft=true;
		original.setActive(true);
		
	}
	
	/**
	 *  Initialize the document to be splitted with a horizontal seperator bar
	 * @param original - the htmlDocument , the left node becomes the original document
	 * 	the right side is a copy of the html that is reloaded
	 */
	private void initHorizontalPanels(LeafPane original) {
		LeafPane tempLeftPanel = original;
		LeafPane tempRightPanel = original.copy();
		
		int heightLeftPanel = Math.floorDiv(getHeight(), 2);
		
		tempLeftPanel.setX(0);
		tempLeftPanel.setY(0);
		
		tempRightPanel.setX(0);
		tempRightPanel.setY(heightLeftPanel);
		
		tempLeftPanel.setHeight(heightLeftPanel);
		tempRightPanel.setHeight(heightLeftPanel);
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
	}

	/**
	 *  Initialize the document to be splitted with a vertical seperator bar
	 * 	@param original - the htmlDocument , the left node becomes the original document
	 * 		the right side is a copy of the html that is reloaded
	 */
	private void initVerticalPanels(LeafPane original) {
		LeafPane tempLeftPanel = original;
		LeafPane tempRightPanel = original.copy();
		
		int widthPanel = Math.floorDiv(getWidth(), 2);
		
		tempLeftPanel.setX(0);
		tempLeftPanel.setY(0);
		
		tempRightPanel.setX(widthPanel);
		tempRightPanel.setY(0);

		tempLeftPanel.setWidth(widthPanel);
		tempRightPanel.setWidth(widthPanel);
		
		this.leftPanel = tempLeftPanel;
		this.rightPanel = tempRightPanel;
	}

	/**
	 * Gets the active HTMLDocument, this class has a variable that is true if the activeELement
	 * is on the left side, if false it is on the rigth side (if none it is set to left)
	 */
	@Override
	public HTMLDocument getActiveHTMLDocument() {
		if (activeOnLeft) {
			return leftPanel.getActiveHTMLDocument();
		} else {
			return rightPanel.getActiveHTMLDocument();
		}
	}

	/**
	 * Changes the active element to the HTMLDocument at the given position
	 */
	@Override
	public HTMLDocument changeActiveHTMLDocument(int x, int y) {
		if(this.containsPoint(x, y)) {
			int xRel = x - getX();
			int yRel = y - getY();
			resetActiveHTMLDocument();
			
			if (leftPanel.containsPoint(xRel, yRel))
				return leftPanel.changeActiveHTMLDocument(xRel, yRel);
			else if (rightPanel.containsPoint(xRel, yRel)){
				activeOnLeft = false;
				return rightPanel.changeActiveHTMLDocument(xRel, yRel);
			}
		}
		return null;
		
	}

	/**
	 * Resets the whole tree, set all elements inactive 
	 */
	@Override
	public void resetActiveHTMLDocument() {
		if (activeOnLeft)
			leftPanel.resetActiveHTMLDocument();
		else
			rightPanel.resetActiveHTMLDocument();
		activeOnLeft = true;
	}

	/**
	 * Splits the active HTMLDocument vertical and returns the new Tree
	 */
	@Override
	public Pane splitActiveHTMLDocumentVertical() {
		if (activeOnLeft)
			leftPanel = leftPanel.splitActiveHTMLDocumentVertical();
		else
			rightPanel = rightPanel.splitActiveHTMLDocumentVertical();
		
		return this;
	}
	
	/**
	 * Splits the active HTMLDocument horizontal and returns the new Tree
	 */
	@Override
	public Pane splitActiveHTMLDocumentHorizontal() {
		if (activeOnLeft)
			leftPanel = leftPanel.splitActiveHTMLDocumentHorizontal();
		else
			rightPanel = rightPanel.splitActiveHTMLDocumentHorizontal();
		
		return this;
	}

	/**
	 * Paints the splitHTMLCode with the seperatorBar
	 */
	@Override
	public void paint(Graphics g) {
		Graphics newG =  g.create(getX(), getY(), getWidth(), getHeight());
		
		leftPanel.paint(newG);
		rightPanel.paint(newG);
		
		
		bar.paint(newG);
	}

	/**
	 * Deletes the ActiveHTMLDocument and adjust the tree so that every node has 2 childeren
	 */
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
	
	/**
	 * Gets GUI at the given position, checks first if the seperatorBar is selected
	 */
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		int xRel = x - getX();
		int yRel = y - getY();
		
		if (bar.containsPoint(xRel, yRel)) {
			return bar;
		}
		
		//this.changeActiveHTMLDocument(x, y);
		
		if (leftPanel.containsPoint(xRel, yRel)) {
			return leftPanel.getGUIAtPosition(xRel, yRel);
		} else {
			return rightPanel.getGUIAtPosition(xRel, yRel);
		}
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
	public void seperatorBarMoved() {
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
	private void moveVerticalBar() {
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
		if (dir == Direction.VERTICAL) {
			if(newWidth>this.getLeftPanel().getWidth()) {
				this.setWidth(newWidth);
				int widthLeftPanel = newWidth - rightPanel.getWidth();
				leftPanel.updateRightClosestChildWidth(0, widthLeftPanel);
				bar.changeXPosition(widthLeftPanel);
				rightPanel.setX(widthLeftPanel);
			}
		} else {
			this.setWidth(newWidth);
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
		if (dir == Direction.VERTICAL) {
			if(newWidth>this.getRightPanel().getWidth()) {
				this.setWidth(newWidth);
				int widthRigthPanel = newWidth - leftPanel.getWidth();
				int widthLeftPanel = leftPanel.getWidth();
				rightPanel.updateLeftClosestChildWidth(widthLeftPanel, widthRigthPanel);
			}
		} else {
			this.setWidth(newWidth);
			leftPanel.updateLeftClosestChildWidth(0, newWidth);
			rightPanel.updateLeftClosestChildWidth(0, newWidth);
		}
	}
	
	/**
	 * When the horizontal bar is moved the closest childs changes their position and width
	 * The rest remains unchanged
	 */
	private void moveHorizontalBar() {
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
		if (dir == Direction.HORIZONTAL) {
			if(newHeight>this.getRightPanel().getHeight()) {
				this.setHeight(newHeight);
				int heightLeftPanel = newHeight-rightPanel.getHeight();
				leftPanel.updateRightClosestChildHeight(0, heightLeftPanel);
				bar.changeYPosition(heightLeftPanel);
				rightPanel.setY(heightLeftPanel);
			}
			//rightPanel.updateRightClosestChildHeight(heightLeftPanel, this.getHeight() - heightLeftPanel);
		} else {
			this.setHeight(newHeight);
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
		if (dir == Direction.HORIZONTAL) {
			if(newHeight>this.getRightPanel().getHeight()) {
				this.setHeight(newHeight);
				int heightLeftPanel = leftPanel.getHeight();
				rightPanel.updateLeftClosestChildHeight(heightLeftPanel, newHeight - heightLeftPanel);
			}
		} else {
			this.setHeight(newHeight);
			leftPanel.updateLeftClosestChildHeight(0, newHeight);
			rightPanel.updateLeftClosestChildHeight(0, newHeight);
		}
	}
	
	public Direction getDir() {
		return dir;
	}
	
	
	public Pane getRightPanel() {
		return this.rightPanel;
	}
	
	
	public Pane getLeftPanel() {
		return this.leftPanel;
	}





}