package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.SavePageListener;
import EventListeners.SeparatorBarMoveListener;

public class SeperatorBar extends GUIElement {
	
	Direction direction;
	
	Color color=new Color(102,51,0); //Dark brown color
	
	int STANDARDSIZE=4;
	
	Container rootContainer;
	
	private int offsetReference;
	
	private ArrayList<SeparatorBarMoveListener> separatorBarMoveListeners = new ArrayList<>();
	
	public SeperatorBar(Container cont,int[] position,Direction dir) {
		super(dir.getPosition(position)[0],dir.getPosition(position)[1]);
		this.setDirection(dir);
		int[] heigthAndWidth =dir.adjustWidthAndHeigth(cont, STANDARDSIZE);
		this.setWidth(heigthAndWidth[0]);
		this.setHeight(heigthAndWidth[1]);
		this.setRootContainer(cont);
	}

	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {

	}

	@Override
	protected void handleUnselect() {

	}

	@Override
	public void handleClick() {
	}
	
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
	 * Moves the sepertorBar, the bar can only move vertical when a horizontal bar
	 * the bar can only move horizontal when a vertical bar.
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
			else {
				System.out.println(this.inBounds(this.getEndY()+offset));
			}
		}
		
		separatorBarMoveListeners.forEach(listener -> listener.barMoved());
	}
	
	private boolean inBounds(int value) {
		if(this.getDirection()==Direction.VERTICAL) {
			return (this.getRootContainer().getWidth()>=value +this.getWidth() && value>=0);
		}
		else {
			return (this.getRootContainer().getHeight()>=value +getHeight() && value>=0);
		}

	}
	
	public void updateBar(){
		if(this.getDirection()==Direction.VERTICAL) {
			this.setHeight(this.getRootContainer().getHeight());
		}
		else {
			this.setWidth(this.getRootContainer().getWidth());
		}
	}


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
		this.setX( Math.max(0,Math.min(this.getRootContainer().getWidth()-this.getWidth(), x)));
	}
	
	/**
	 * Sets the value yPos of this class
	 * 
	 * @param y - new value of this.yPos
	 */
	public void  changeYPosition(int y) {
		this.setY(Math.max(0,Math.min(this.getRootContainer().getHeight()-this.getHeight(), y)));
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Container getRootContainer() {
		return rootContainer;
	}

	public void setRootContainer(Container rootContainer) {
		this.rootContainer = rootContainer;
	}
	
	public int getOffsetReference() {
		return offsetReference;
	}

	public void setOffsetReference(int newOffsetReference) {
		this.offsetReference = newOffsetReference;
	}
	
	public void addSeparatorBarMoveListener(SeparatorBarMoveListener listener) {
		this.getSeparatorBarMoveListeners().add(listener);
	}

	public void removeSavePageListener(SeparatorBarMoveListener listener) {
		this.getSeparatorBarMoveListeners().remove(listener);
	}
	
	protected ArrayList<SeparatorBarMoveListener> getSeparatorBarMoveListeners() {
		return separatorBarMoveListeners;
	}
	
}
