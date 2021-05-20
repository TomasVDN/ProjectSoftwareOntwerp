package GUIElements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import EventListeners.AdjustmentListener;
import EventListeners.ScrollBarListener;

/**
 * Class that implements a scrollbar. This is a GUIElement composed of two bars. One to show the total length of the GUIElement it is 
 * attached to, and one to show the length of the visible part of the GUIElement. This last one is dragable to move the viewed part.
 *
 */
public class ScrollBar extends GUIElement implements AdjustmentListener{

	// double is used in smallBarWidth and smallBarX to calculate the ratios more accurately than when using int
	private double smallBarSize;
	private double smallBarPosition;
	private int offsetReference; // is used to calculate how much smallBarX has to move when dragging
	private ArrayList<ScrollBarListener> scrollListeners = new ArrayList<ScrollBarListener>();
	
	private double bigBarPosition;
	private double bigBarSize;
	private Direction direction;

	/**
	 * Constructor of the scrollbar class.
	 * @param x - x coordinate of this scrollbar
     * @param y - y coordinate of this scrollbar
     * @param w - width of this scrollbar
     * @param h - height of this scrollbar
	 * @param viewableSize - size of the small bar
	 * @param totalSize - size of the large bar
	 * @throws IllegalArgumentException when direction == Horizontal && width <= 0
	 * 			| when direction == Vertical && height <= 0
	 */
	public ScrollBar(Direction direction, int x, int y, int w, int h, int viewableSize,int totalSize) {
		super(x, y, w, h);

		this.setDirection(direction);
		
		if (this.getDirection() == Direction.HORIZONTAL) {
			if (w <= 0) {
				throw new IllegalArgumentException("The width of a horizontal scrollbar can't be 0.");
			}
			this.bigBarPosition = x;
			this.bigBarSize = w;
			this.setSmallBarPosition(x);
		} else {
			if (h <= 0) {
				throw new IllegalArgumentException("The height of a vertical scrollbar can't be 0.");
			}
			this.bigBarPosition = y;
			this.bigBarSize = h;
			this.setSmallBarPosition(y);
		}
		
		this.updateCorrectSmallBarSize(totalSize, viewableSize);;
	}

	/**
	 * Handle left click.	
	 */
	@Override
	public void handlePressClick(int x, int y) {
		if (this.getDirection() == Direction.HORIZONTAL) {
			this.setOffsetReference(x);
		} else {
			this.setOffsetReference(y);
		}
	}
	
	/**
	 * Handle mouse draging. This will displace the small bar and then notify the attached Listeners of the displacement.
	 */
	@Override
	public void handleDragMouse(int x, int y, int clickCount, int modifiers) {
		this.dragSmallBar(x, y);
		this.notifyScrollBarListener();
	}
	
	/**
	 * Displace the small bar.
	 * @param x
	 * @param y
	 */
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

	/**
	 * Paints the two bars of the scrollbar.
	 * @param g - graphics to paint with
	 */
	@Override
	public void paint(Graphics g) {
		// Draw big bar
		g.setColor(Color.lightGray);
		g.fillRect(super.getX(), super.getY(), super.getWidth()-1, super.getHeight()-1);
		
		// Draw small bar
		g.setColor(Color.gray);
		
		if (this.getDirection() == Direction.HORIZONTAL) {
			g.fillRect((int) this.getSmallBarPosition(), this.getY(), (int) this.getSmallBarSize(), this.getHeight());
		} else {
			g.fillRect(this.getX(), (int) this.getSmallBarPosition(), this.getWidth(), (int) this.getSmallBarSize());
		}
	}
	
	/**
	 * Returns true if (x,y) is within the boundaries from the small bar.
	 * @param x
	 * @param y
	 * @return true if within boundaries, otherwise false
	 */
	public boolean smallBarContainsPoint(int x, int y) {
		if (this.getDirection() == Direction.HORIZONTAL) {
			return (x >= this.getSmallBarPosition() && y >= this.getY() && x <= this.getSmallBarPosition() + this.getSmallBarSize() && y <= this.getEndY());
		} else {
			return (x >= this.getX() && y >= this.getSmallBarPosition() && x <= this.getEndX() && y <= this.getSmallBarPosition() + this.getSmallBarSize());
		}
	}

	/**
	 * Return the size of the small bar.
	 * @return smallBarSize
	 */
	public double getSmallBarSize() {
		return smallBarSize;
	}
	
	/**
	 * Set the size of the small bar to the given value.
	 * @param newSmallBarSize
	 */
	public void setSmallBarSize(double newSmallBarSize) {
		if (newSmallBarSize > this.getBigBarSize()) {
			this.smallBarSize = this.getBigBarSize();
		} else {
			this.smallBarSize = newSmallBarSize;
		}
	}

	/**
	 * Return the position of the small bar.
	 * @return smallBarPosition
	 */
	public double getSmallBarPosition() {
		return smallBarPosition;
	}

	/**
	 * Set the position of the small bar to the given value.
	 * @param newSmallBarPosition
	 */
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

	/**
	 * Return the offsetReference
	 * @return this.offsetReference
	 */
	public int getOffsetReference() {
		return offsetReference;
	}

	/**
	 * Set the offsetReference to the given value
	 * @param newOffsetReference
	 */
	public void setOffsetReference(int newOffsetReference) {
		this.offsetReference = newOffsetReference;
	}
	
	/**
	 * Returns whether the small bar is located at position (x,y)
	 */
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

	/**
	 * Return the size of the big bar.
	 * @return bigBarSize
	 */
	public double getBigBarSize() {
		return this.bigBarSize;
	}

	/**
	 * Return the position of the big bar.
	 * @return bigBarPosition
	 */
	public double getBigBarPosition() {
		return this.bigBarPosition;
	}
	
	/**
	 * Update the size of the small bar. It is equal to: (viewable size of the content) / (total size of all content) * size scrollbar
	 * @param totalSize
	 * @param viewableSize
    * @throws IllegalArgumentException when totalSize <= 0
	 */
	public void updateCorrectSmallBarSize(int totalSize, int viewableSize) {
		if (totalSize <= 0) {
			throw new IllegalArgumentException("The totalSize in the method updateCorrectSmallBarSize can't be 0 or smaller.");
		}
		double ratio = viewableSize / ((double) (totalSize));
		double correctSize = (this.getBigBarSize() * ratio);
		this.setSmallBarSize(correctSize);
	}
	
	/**
	 * Updates the position of the small bar in function of the given ratio.
	 * @param ratio
	 */
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
        super.setX(x);

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
        super.setY(y);
        
        if (this.getDirection() == Direction.VERTICAL) {
            double oldRatio = this.getScrollBarRatio();
            
            this.bigBarPosition = y;
            this.setSmallBarPositionWithRatio(oldRatio);
        }
    }
    
	/**
    * Sets the value width of this class. If the new value is negative, set it to 0 instead.
    * @param width - new value of this.width
    * @throws IllegalArgumentException when direction == Horizontal && width <= 0
    */
   @Override
   public void setWidth(int width) {
       super.setWidth(width);
       
       if (this.getDirection() == Direction.HORIZONTAL) {
			if (width <= 0) {
				throw new IllegalArgumentException("The width of a horizontal scrollbar can't be 0.");
			}
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
    * @throws IllegalArgumentException when direction == Vertical && height <= 0
    */
   @Override
   public void setHeight(int height) {
       super.setHeight(height);

       if (this.getDirection() == Direction.VERTICAL) {
			if (height <= 0) {
				throw new IllegalArgumentException("The height of a vertical scrollbar can't be 0.");
			}
           double oldBigBarSize = this.getBigBarSize();
           double oldSmallBarSize = this.getSmallBarSize();
           double oldRatio = this.getScrollBarRatio();
           
           this.bigBarSize = height;
           this.updateCorrectSmallBarSize((int) oldBigBarSize, (int)oldSmallBarSize);
           this.setSmallBarPositionWithRatio(oldRatio);
       }
   	}
   
   /**
    * Method that needs to be called when the linked GUIElement has changed. This will update the small bar.
    */
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
	
	/**
	    * Method that needs to be called when the linked GUIElement has changed. 
	    * This will update the small bar and set it at the beginning of the big bar.
	    */
	@Override
	public void elementChangedKeepAtBeginning(int viewableWidth, int newWidth, int viewableHeight, int newHeight) {
		this.elementChanged(viewableWidth, newWidth, viewableHeight, newHeight);
		this.setSmallBarPosition(0);
		this.notifyScrollBarListener();
	}
	
	/**
    * Method that needs to be called when the linked GUIElement has changed. 
    * This will update the small bar and set it at the end of the big bar.
    */
	@Override
	public void elementChangedKeepAtEnd(int viewableWidth, int totalWidth, int viewableHeight, int totalHeight) {
		double oldSmallBarPositionEnd = this.getSmallBarPosition() + this.getSmallBarSize();
		this.elementChanged(viewableWidth, totalWidth, viewableHeight, totalHeight);
		this.setSmallBarPosition(oldSmallBarPositionEnd - this.smallBarSize);
		this.notifyScrollBarListener();
	}
	
	/**
	 * Returns the direction of this scrollbar
	 * @return direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the direction of this scrollbar to the given value.
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		if(direction==null) {
			throw new IllegalArgumentException("Not a valid direction");
		}
		this.direction = direction;
	}
	
	/**
	 * Notify all listeners that the scrollbar has moved.
	 */
	public void notifyScrollBarListener() {
		this.getScrollBarListeners().forEach(l->l.scrollBarMoved(this.getScrollBarRatio(),this.getDirection()));
	}
	
	/**
	 * Adds the given ScrollBarListener to the list of ScrollBarListeners
	 * @param listener
	 */
	public void addScrollBarListener(ScrollBarListener listener) {
		if(listener!=null) {
			this.getScrollBarListeners().add(listener);
		}
	}
	
	/**
	 * Removes the given ScrollBarListener from the list of ScrollBarListeners
	 * @param listener
	 */
	public void removeScrollBarListener(ScrollBarListener listener) {
		this.getScrollBarListeners().remove(listener);
	}

	/**
	 * Returns the list of SavePageListeners
	 * @return scrollListeners
	 */
	protected ArrayList<ScrollBarListener> getScrollBarListeners() {
		return scrollListeners;
	}

	/**
	 * Handles the key event of a scrollbar
	 */
	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {}

	/**
	 * Handles the unselection of a scrollbar, this means that the scrollbar was selected
	 * previously and now the user selected an other object
	 */
	@Override
	protected void handleUnselect() {}

	/**
	 * Handles the click of a scrollbar
	 */
	@Override
	public void handleClick() {}
}