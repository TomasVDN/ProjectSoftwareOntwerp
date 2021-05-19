package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.AdjustmentListener;
import EventListeners.FormListener;
import EventListeners.ScrollBarListener;
import EventListeners.SeparatorBarMoveListener;

public class ScrollBar extends GUIElement implements AdjustmentListener{
	// double is used in smallBarWidth and smallBarX to calculate the ratios more accurately than when using int
	
	private double smallBarSize;
	private double smallBarPosition;
	private int offsetReference; // is used to calculate how much smallBarX has to move when dragging
	private ArrayList<ScrollBarListener> scrollListeners = new ArrayList<ScrollBarListener>();
	
	private double bigBarPosition;
	private double bigBarSize;
	private Direction direction;


	
	
	public ScrollBar(Direction direction, int x, int y, int w, int h, int viewableSize,int totalSize) {
		super(x, y, w, h);
		

		
		this.setDirection(direction);
		
		
		if (this.getDirection() == Direction.HORIZONTAL) {
			this.bigBarPosition = x;
			this.bigBarSize = w;
			this.setSmallBarPosition(x);
		} else {
			this.bigBarPosition = y;
			this.bigBarSize = h;
			this.setSmallBarPosition(y);
		}
		
		this.updateCorrectSmallBarSize(totalSize, viewableSize);;
		
	}


	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void handleUnselect() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
	}	
	
	
	@Override
	public void handlePressClick(int x, int y) {
		if (this.getDirection() == Direction.HORIZONTAL) {
			this.setOffsetReference(x);
		} else {
			this.setOffsetReference(y);
		}
	}
	
	@Override
	public void handleDragMouse(int x, int y, int clickCount, int modifiers) {
		this.dragSmallBar(x, y);
		this.notifyScrollBarListener();
	}
	
	public void dragSmallBar(int x, int y) {
		int newOffset;
		int offset;
		if (this.getDirection() == Direction.HORIZONTAL) {
			newOffset = x;
			offset = x - this.getOffsetReference();
		} else {
			newOffset = y;
			offset = y - this.getOffsetReference();
		}
		
		double oldSmallBarPosition = this.getSmallBarPosition();
		this.setSmallBarPosition(oldSmallBarPosition + offset);
		
		if (this.getSmallBarPosition() + offset < this.getBigBarPosition() || this.getSmallBarPosition() + offset > this.getBigBarPosition() + this.getBigBarSize() - this.getSmallBarSize()) {
			// TODO effe fixe me die if en else omwisselen 
		} else {
			this.setOffsetReference(newOffset);
		}
	}


	@Override
	public void paint(Graphics g) {
		// Draw big bar
		//g.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		g.setColor(Color.lightGray);
		g.fillRect(super.getX(), super.getY(), super.getWidth()-1, super.getHeight()-1);
		
		// Draw small bar
		//g.drawRect(this.getSmallBarX(), this.getY(), this.getSmallBarWidth(), this.getHeight());
		g.setColor(Color.gray);
		
		if (this.getDirection() == Direction.HORIZONTAL) {
			g.fillRect((int) this.getSmallBarPosition(), this.getY(), (int) this.getSmallBarSize(), this.getHeight());
		} else {
			g.fillRect(this.getX(), (int) this.getSmallBarPosition(), this.getWidth(), (int) this.getSmallBarSize());
		}
	}
	
	public boolean smallBarContainsPoint(int x, int y) {
		if (this.getDirection() == Direction.HORIZONTAL) {
			return (x >= this.getSmallBarPosition() && y >= this.getY() && x <= this.getSmallBarPosition() + this.getSmallBarSize() && y <= this.getEndY());
		} else {
			return (x >= this.getX() && y >= this.getSmallBarPosition() && x <= this.getEndX() && y <= this.getSmallBarPosition() + this.getSmallBarSize());
		}
	}


	public double getSmallBarSize() {
		return smallBarSize;
	}
	
	public void setSmallBarSize(double newSmallBarSize) {
		if (newSmallBarSize > this.getBigBarSize()) {
			this.smallBarSize = this.getBigBarSize();
		} else {
			this.smallBarSize = newSmallBarSize;
		}
	}


	public double getSmallBarPosition() {
		return smallBarPosition;
	}


	public void setSmallBarPosition(double newSmallBarPosition) {
		if (newSmallBarPosition < this.getBigBarPosition()) {
			this.smallBarPosition = this.getBigBarPosition();
		} 
		else if (newSmallBarPosition > this.getBigBarPosition() + this.getBigBarSize() - this.getSmallBarSize()) {
			this.smallBarPosition = this.getBigBarPosition() + this.getBigBarSize() - this.getSmallBarSize();
		} 
		else {
			this.smallBarPosition = newSmallBarPosition;
		}
	}


	public int getOffsetReference() {
		return offsetReference;
	}


	public void setOffsetReference(int newOffsetReference) {
		this.offsetReference = newOffsetReference;
	}
	
	@Override
	public GUIElement getGUIAtPosition(int x, int y) {
		if(this.smallBarContainsPoint(x, y)) {
			return this;
		}
		return null;
	}

	/**
	 * Returns a number between 0.0 and 1.0 indicating where the small bar is positioned.
	 * 0.0 means the beginning of the small bar is at the left side of the big bar.
	 * The bigger the number the more the small bar is positioned to the right .
	 * 1.0 will never be reached because the beginning of the small bar can never be completely moved to the right side of the big bar.
	 */
	public double getScrollBarRatio() {
		return (this.getSmallBarPosition() - this.getBigBarPosition()) / (double) this.getBigBarSize();
	}



	public double getBigBarSize() {
		return this.bigBarSize;
	}

	
	public double getBigBarPosition() {
		return this.bigBarPosition;
	}
	
	
	public void updateCorrectSmallBarSize(int totalSize, int viewableSize) {
		// correct size of small bar is the ratio of (viewable size of the content) / (total size of all content) and this multiplied by the size of the scroll bar.
		double ratio = viewableSize / ((double) (totalSize));
		double correctSize = (this.getBigBarSize() * ratio);
		this.setSmallBarSize(correctSize);
	}
	
	public void setSmallBarPositionWithRatio(double ratio) {
        double newSmallBarPosition = this.getBigBarPosition() + this.getBigBarSize() * ratio;
        this.setSmallBarPosition(newSmallBarPosition);
    }
	
	/**
	 * Sets the value xPos of this class.
	 * 
	 * @param x - new value of this.xPos
	 */
    @Override
    public void setX(int x) {
        super.setX(x); // TODO good practice ??
        if (this.getDirection() == Direction.HORIZONTAL) {
            double oldRatio = this.getScrollBarRatio();
            this.bigBarPosition = x;
            this.setSmallBarPosition(x);
            this.setSmallBarPositionWithRatio(oldRatio);
        }
    }
    
    /**
     * Sets the value yPos of this class
     * 
     * @param y - new value of this.yPos
     */
    @Override
    public void setY(int y) {
        super.setY(y); // TODO good practice ??
        if (this.getDirection() == Direction.VERTICAL) {
            double oldRatio = this.getScrollBarRatio();
            this.bigBarPosition = y;
            this.setSmallBarPositionWithRatio(oldRatio);
        }
    }
    
	/*
    * Sets the value width of this class. If the new value is negative, set it to 0 instead.
    * 
    * @param width - new value of this.width
    */
   @Override
   public void setWidth(int width) {
       super.setWidth(width); // TODO good practice ??
       if (this.getDirection() == Direction.HORIZONTAL) {
           double oldBigBarSize = this.getBigBarSize();
           double oldSmallBarSize = this.getSmallBarSize();
           double oldRatio = this.getScrollBarRatio();
           this.bigBarSize = width;
           this.updateCorrectSmallBarSize((int)oldBigBarSize, (int)oldSmallBarSize);
           this.setSmallBarPositionWithRatio(oldRatio);
       }
   }
   
   /**
    * Sets the value height of this class. If the new value is negative, set it to 0 instead.
    * 
    * @param height - new value of this.height
    */
   @Override
   public void setHeight(int height) {
       super.setHeight(height); // TODO good practice ??
       if (this.getDirection() == Direction.VERTICAL) {
           double oldBigBarSize = this.getBigBarSize();
           double oldSmallBarSize = this.getSmallBarSize();
           double oldRatio = this.getScrollBarRatio();
           this.bigBarSize = height;
           this.updateCorrectSmallBarSize((int) oldBigBarSize, (int)oldSmallBarSize);
           this.setSmallBarPositionWithRatio(oldRatio);
       }
   }
   




	@Override
	public void elementChanged(int viewableWidth,int newWidth,int viewableHeight,int newHeight) {
        if(this.getDirection() ==  Direction.HORIZONTAL) {
            this.updateCorrectSmallBarSize(newWidth, viewableWidth);
        }
        else {
            this.updateCorrectSmallBarSize(newHeight, viewableHeight);
        }
        this.notifyScrollBarListener();
        
	}
	
	@Override
	public void elementIncreased(int viewableWidth, int totalWidth, int viewableHeight, int totalHeight) {
		double oldSmallBarPositionEnd = this.getSmallBarPosition() + this.getSmallBarSize();
		this.elementChanged(viewableWidth, totalWidth, viewableHeight, totalHeight);
		this.setSmallBarPosition(oldSmallBarPositionEnd - this.smallBarSize);
		this.notifyScrollBarListener();
	}
	
	@Override
	public void elementChangedAndReset(int viewableWidth, int newWidth, int viewableHeight, int newHeight) {
		this.elementChanged(viewableWidth, newWidth, viewableHeight, newHeight);
		this.setSmallBarPosition(0);
		this.notifyScrollBarListener();
	}
	
	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		if(direction==null) {
			throw new IllegalArgumentException("Not a valid direction");
		}
		this.direction = direction;
	}
	
	public void addScrollBarListener(ScrollBarListener listener) {
		if(listener!=null) {
			this.getScrollBarListeners().add(listener);
		}
	}
	
	public void removeScrollBarListener(ScrollBarListener listener) {
		this.getScrollBarListeners().remove(listener);
	}


	
	protected ArrayList<ScrollBarListener> getScrollBarListeners() {
		return scrollListeners;
	}
	
	public void notifyScrollBarListener() {
		this.getScrollBarListeners().forEach(l->l.scrollBarMoved(this.getScrollBarRatio(),this.getDirection()));
	}




	
	
}