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
	
	private ArrayList<SeparatorBarMoveListener> separatorBarMoveListeners = new ArrayList<>();
	
	public SeperatorBar(Container cont,int[] position,Direction dir) {
		// TODO Auto-generated constructor stub
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
	
	/**
	 * Moves the sepertorBar, the bar can only move vertical when a horizontal bar
	 * the bar can only move horizontal when a vertical bar.
	 */
	@Override
	public void handleDragMouse(int x, int y, int clickCount, int modifiers) {
		int[] dragPosition = this.positionInContainer(x, y);
		int[] seperatorPosition = this.getDirection().getPosition(dragPosition);
		this.setPosition(seperatorPosition[0], seperatorPosition[1]);
		separatorBarMoveListeners.forEach(listener -> listener.barMoved());
	}
	
	private int[] positionInContainer(int x, int y) {
		int absoluteX = x-this.getRootContainer().getX();
		int absoluteY = y-this.getRootContainer().getY();
		int boundedX = Math.min(this.getRootContainer().getWidth(), Math.max(absoluteX, 0));
		int boundedY = Math.min(this.getRootContainer().getHeight(), Math.max(absoluteY, 0));
		return new int[] {boundedX,boundedY};
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
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
