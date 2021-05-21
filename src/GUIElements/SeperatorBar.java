package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.SeparatorBarMoveListener;

/**
 * This is a seperatorBar class, it has a rootContainer which holds 2 sub classes.
 * The seperator bar forms a border between those sub classes which can change dynamically
 *
 */
public class SeperatorBar extends GUIElement {
	
	private Direction direction;	
	private Color color=new Color(102,51,0); //Dark brown color	
	private int STANDARDSIZE=4;	
	private Container rootContainer;	
	private int offsetReference;	
	private ArrayList<SeparatorBarMoveListener> separatorBarMoveListeners = new ArrayList<>();
	
	/**
	 * Creates a seperatorBar
	 * @param cont - the root container, this is used to know the max height and width that the
	 * 					bar can move
	 * @param position - the position of the seperator bar
	 * @param dir - the direction of the bar, directions supported are horizontal and vertical
	 */
	public SeperatorBar(Container cont,int position,Direction dir) {
		super(positionBar(dir,position)[0],positionBar(dir,position)[1]);
		this.setDirection(dir);
		this.setRootContainer(cont);
		int[] heigthAndWidth = dir.adjustWidthAndHeigth(cont, STANDARDSIZE);
		this.setWidth(heigthAndWidth[0]);
		this.setHeight(heigthAndWidth[1]);
	}
	/**
	 * Sets the position coresponding to the direction of the bar
	 */
	public static int[] positionBar(Direction dir,int position) {
		if(dir==Direction.VERTICAL) {
			return new int[] {position,0};
		}
		else {
			return new int[] {0,position};
		}
	}

	/**
	 * Handles the key event of a seperator Bar
	 */
	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {

	}

	/**
	 * Handles the unselection of a seperatorbar
	 */
	@Override
	protected void handleUnselect() {

	}

	/**
	 * handles a click of a seperator bar
	 */
	@Override
	public void handleClick() {
	}
	
	/**
	 *  Handles the press click of a seperator bar
	 *  When a bar is pressed his offset reference is saved inside, this is used
	 *  when the mouse go of the screen for example
	 */
	@Override 
	public void handlePressClick(int x, int y) {
		if(this.getDirection()== Direction.VERTICAL) {
			this.setOffsetReference(x);
		}
		else {
			this.setOffsetReference(y);
		}
	}
	
	/**
	 * Moves the sepertorBar, the bar can only move vertical when it is a horizontal bar
	 * the bar can only move horizontal when it is a vertical bar.
	 */
	@Override
	public void handleDragMouse(int x, int y, int clickCount, int modifiers) {
		int newOffset;
		int offset;
		if (this.getDirection() == Direction.VERTICAL) {
			newOffset = x;
			offset = x - this.getOffsetReference();
			this.changeXPosition(offset+this.getX());
			if( this.inBounds(this.getEndX()+offset)) {
				this.setOffsetReference(newOffset);
			}
		} else {
			newOffset = y;
			offset = y - this.getOffsetReference();
			this.changeYPosition(offset+this.getY());
			if( this.inBounds(this.getEndY()+offset)) {
				this.setOffsetReference(newOffset);
			}
		}
		
		separatorBarMoveListeners.forEach(listener -> listener.seperatorBarMoved());
	}
	
	/**
	 * Returns true if the given value is inside the container
	 * This value corresponds to an x position when the seperator bar is vertical
	 * and a y position when the bar is horizontal
	 */
	private boolean inBounds(int value) {
		if(this.getDirection()==Direction.VERTICAL) {
			return (this.getRootContainer().getWidth()>=value +this.getWidth() && value>=0);
		}
		else {
			return (this.getRootContainer().getHeight()>=value +getHeight() && value>=0);
		}

	}
	
	/**
	 * Update the size of the bar so that it takes the full size of the container
	 */
	public void updateBar(){
		if(this.getDirection()==Direction.VERTICAL) {
			this.setHeight(this.getRootContainer().getHeight());
		}
		else {
			this.setWidth(this.getRootContainer().getWidth());
		}
	}


	/**
	 * Paints the bar
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
	}

	
	/**
	 * Sets the value xPos of this class.
	 * 
	 * @param x - new value of this.xPos
	 */
	public void changeXPosition(int x) {
		this.setX( Math.max(this.getWidth(),Math.min(this.getRootContainer().getWidth()-this.getWidth(), x)));
	}
	
	/**
	 * Sets the value yPos of this class
	 * 
	 * @param y - new value of this.yPos
	 */
	public void  changeYPosition(int y) {
		this.setY(Math.max(this.getHeight(),Math.min(this.getRootContainer().getHeight()-this.getHeight(), y)));
	}
	
	/**
	 * Gets the direction of the bar
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the direction of the bar to given direction
	 */
	private void setDirection(Direction direction) {
		if(direction==null) {
			throw new IllegalArgumentException("Not a valid direction");
		}
		this.direction = direction;
	}
	
	/**
	 * returns the root container were this bar is pointing to
	 */
	public Container getRootContainer() {
		return rootContainer;
	}

	/**
	 * Sets the root container to the given container
	 */
	private void setRootContainer(Container rootContainer) {
		if(rootContainer==null) {
			throw new IllegalArgumentException("not a valid container");
		}
		this.rootContainer = rootContainer;
	}
	
	/**
	 * returns the offsetReference of this class 
	 * The offset reference hold the absolute position pressed on this element.
	 * This is then later used to move the seperator bar by calculating the difference between
	 * its position dragged and the offset reference
	 */
	public int getOffsetReference() {
		return offsetReference;
	}

	/**
	 * Set the offset reference to the given value
	 */
	private void setOffsetReference(int newOffsetReference) {
		this.offsetReference = newOffsetReference;
	}
	/**
	 * Adds a new seperatorBar moved listener to this class
	 */
	public void addSeparatorBarMoveListener(SeparatorBarMoveListener listener) {
		if(listener!=null) {
			this.getSeparatorBarMoveListeners().add(listener);
		}
	}
	/**
	 * removes the given listener of the seperatorBar move listeners.
	 */
	public void removeSeparatorBarMoveListener(SeparatorBarMoveListener listener) {
		this.getSeparatorBarMoveListeners().remove(listener);
	}


	
	protected ArrayList<SeparatorBarMoveListener> getSeparatorBarMoveListeners() {
		return separatorBarMoveListeners;
	}
	
}
