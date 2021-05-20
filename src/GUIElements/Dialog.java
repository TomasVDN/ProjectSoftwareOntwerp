package GUIElements;

/**
 * Superclass for the dialog type. It is a container with mouse and keyboard handling.
 */
public abstract class Dialog extends Container {

	protected GUIElement elementWithKeyBoardFocus;
	private GUIElement pressedElement;
	
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
	 * Changes the elementWithKeyBoardFocus to the given element.
	 * @param elementWithKeyBoardFocus - new value of this.elementWithKeyBoardFocus
	 */
	public void setElementWithKeyBoardFocus(GUIElement elementWithKeyBoardFocus) {
		this.elementWithKeyBoardFocus = elementWithKeyBoardFocus;
	}
	
	/**
	 * This method changes the activeElement to the given element, and invokes element.handleClick.
	 * If the given element is already the activeElement, it only invokes element.handleClick.
	 * @param element - the new activeElement
	 */
	public void changeElementWithKeyboardFocus(GUIElement element) {
		GUIElement elementWithKeyboardFocus = this.getElementWithKeyBoardFocus();
		
		if(element!= elementWithKeyboardFocus) {
			//deactivate old activeElement
			if (elementWithKeyboardFocus != null && this.getElementWithKeyBoardFocus().isActive()) {
				elementWithKeyboardFocus.setActiveUnselect(false);
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

	/**
	 * Handle the left click. This will change the elementWithKeyBoardFocus to the element found at that position,
	 * and if there is one, call handleClick on it.
	 * @param x
	 * @param y
	 * @param clickCount
	 * @param modifiers
	 */
	public void handleClickLeftMouse(int x, int y, int clickCount, int modifiers)	{
		changeElementWithKeyboardFocus(this.getGUIAtPosition(x, y));
	}
	
	/**
	 * Transmits the key event to the elementWithKeyBoardFocus.
	 */
	@Override
	public void handleKeyEvent(int keyCode, char keyChar, int modifiersEx) {
		if (elementWithKeyBoardFocus != null) {
			elementWithKeyBoardFocus.handleKeyEvent(keyCode, keyChar, modifiersEx);
		}
	}

	/**
	 * Handle the left click press. This will change the pressedElement to the element found at that position,
	 * and if there is one, call handlePressClick on it.
	 * @param x
	 * @param y
	 * @param clickCount
	 * @param modifiers
	 */
	public void handlePressLeftMouse(int x, int y, int clickCount, int modifiers) {
		GUIElement guiPressed = this.getGUIAtPosition(x, y);
		this.setPressedElement(guiPressed);
		if(guiPressed!=null) {
			guiPressed.handlePressClick(x,y);
		}
		if(this.getElementWithKeyBoardFocus()!=null && this.getElementWithKeyBoardFocus()!= this.getPressedElement() ) {
			this.getElementWithKeyBoardFocus().handleUnselect();
		}
	}
	
	/**
	 * Handle the left click release. This will trigger the handleReleaseClick on the pressed element if the mouse is still on it.
	 * @param x
	 * @param y
	 * @param clickCount
	 * @param modifiers
	 */
	public void handleReleaseLeftMouse(int x, int y, int clickCount, int modifiers) {
		if(this.getPressedElement()!=null) {
			GUIElement releasedAt = this.getGUIAtPosition(x, y);
			if(this.getPressedElement() == releasedAt ) {
				this.getPressedElement().handleReleaseClick(true);
			}
			else {
				this.getPressedElement().handleReleaseClick(false);
			}
		}
		this.setPressedElement(null);
	}
	
	/**
	 * 
	 * @return this.pressedElement
	 */
	public GUIElement getPressedElement() {
		return pressedElement;
	}

	/**
	 * Set this.pressedElement to the given element.
	 * @param pressedElement
	 */
	public void setPressedElement(GUIElement pressedElement) {
		this.pressedElement = pressedElement;
	}

	/**
	 * Handles the drag operation on the pressed element
	 */
	@Override
	public void handleDragMouse(int x, int y, int clickCount, int modifiers) {
		if(this.getPressedElement()!=null) {
			this.getPressedElement().handleDragMouse(x, y, clickCount, modifiers);
		}
	}	
}
