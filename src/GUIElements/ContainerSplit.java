package GUIElements;

import java.awt.Graphics;

public class ContainerSplit extends Container {
	private Container left;
	private Container right;
	private boolean activeOnLeft;
	private boolean isVertical;

	public ContainerSplit(int x, int y, int w, int h, Container original, boolean isVertical) {
		super(x, y, w, h, null);
		
		this.activeOnLeft = true;
		this.isVertical = isVertical;
		
		left = initLeftContainer(original);
		right = initRightContainer(original);
		
		left.redraw();
		right.redraw();
	}

	private Container initLeftContainer(Container original) {
		if (isVertical) {
			return new Container(original.getX(), original.getY(), Math.floorDiv(original.getWidth(),2), original.getHeight(), original.getLoadedUrl());
		}
		else {
			return new Container(original.getX(), original.getY(), original.getWidth(), Math.floorDiv(original.getHeight(),2), original.getLoadedUrl());
		}
	}
	
	private Container initRightContainer(Container original) {
		if (isVertical) {
			return new Container(original.getX() + Math.floorDiv(original.getWidth(),2), original.getY(), Math.floorDiv(original.getWidth(),2), original.getHeight(), original.getLoadedUrl());
		}
		else {
			return new Container(original.getX(), original.getY() + Math.floorDiv(original.getHeight(),2), original.getWidth(), Math.floorDiv(original.getHeight(),2), original.getLoadedUrl());
		}
	}
	
	@Override
	public void paint(Graphics g) {
		left.paint(g);
		right.paint(g);
	}
	

}
