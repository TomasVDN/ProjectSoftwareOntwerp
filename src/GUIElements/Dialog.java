package GUIElements;

public abstract class Dialog extends Container {

	private GUIElement elementWithKeyBoardFocus;
	
	/**
	 * Constructor of the Dialog class. It extends the Container class.
	 * @param x - x coordinate of this Dialog
     * @param y - y coordinate of this Dialog
     * @param w - width of this Dialog
     * @param h - height of this Dialog
	 */
	public Dialog(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/**
	 * @return this.elementWithKeyBoardFocus
	 */
	public GUIElement getElementWithKeyBoardFocus() {
		return elementWithKeyBoardFocus;
	}

	/**
	 * @param elementWithKeyBoardFocus - new value of this.elementWithKeyBoardFocus
	 */
	public void setElementWithKeyBoardFocus(GUIElement elementWithKeyBoardFocus) {
		this.elementWithKeyBoardFocus = elementWithKeyBoardFocus;
	}
	
	/**
	 * This method changes the activeElement to the given element, and invokes element.handleClick. If the given element is already the activeElement, it only invokes element.handleClick.
	 * @param element - the new activeElement
	 */
	public void changeElementWithKeyboardFocus(GUIElement element) {
		GUIElement elementWithKeyboardFocus = this.getElementWithKeyBoardFocus();
		if(element!= elementWithKeyboardFocus) {
			//deactivate old activeElement
			if (elementWithKeyboardFocus != null && this.getElementWithKeyBoardFocus().isActive()) {
				elementWithKeyboardFocus.setActive(false);
			}
			
			//activate new activeElement
			this.setElementWithKeyBoardFocus(element);
			
			if (element != null) {
				element.handleClick();
			}
		}
		else {
			//if the given element is already the activeElement.
			if(element!=null) {
				element.handleClick();
			}
		}
	}
	
}
