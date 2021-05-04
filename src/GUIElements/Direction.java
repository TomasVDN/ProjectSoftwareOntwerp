package GUIElements;

public enum Direction {

	HORIZONTAL{
		public int[] getPosition(int[] position) {
			//Horizontal means that position is an y value
			return new int[]{0,position[1]};
		}

		@Override
		public int[] adjustWidthAndHeigth(Container cont, int smallSize) {
			return new int[]{cont.getWidth(),smallSize};
		}


    },
	VERTICAL{
    	public int[] getPosition(int[] position) {
			//Vertical means that position is an x value
			return new int[]{position[0],0};
		}

		@Override
		public int[] adjustWidthAndHeigth(Container cont, int smallSize) {
			return new int[]{smallSize,cont.getHeight()};
		}


    };
	
	public abstract int[] getPosition(int[] position);
	
/**
 * This method ask for the container and an integer, with this he is going to set the heigth or width
 * to the size of the container, smallSize is used when the container is not used 
 * @param cont
 * @param smallSize
 * @return
 */
	public abstract int[] adjustWidthAndHeigth(Container cont, int smallSize);
}
