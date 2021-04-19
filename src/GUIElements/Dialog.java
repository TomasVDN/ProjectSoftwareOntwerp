package GUIElements;

public abstract class Dialog extends Container {

	private GUIElement keyBoardFocus;
	
	public Dialog(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public GUIElement getKeyBoardFocus() {
		return keyBoardFocus;
	}

	public void setKeyBoardFocus(GUIElement keyBoardFocus) {
		this.keyBoardFocus = keyBoardFocus;
	}
	
	/**
	 * This method changes the activeElement to the given element, and invokes element.handleClick. If the given element is already the activeElement, it only invokes element.handleClick.
	 * @param element - the new activeElement
	 */
	public void changeElementWithKeyboardFocus(GUIElement element) {
		GUIElement elementWithKeyboardFocus = this.getKeyBoardFocus();
		if(element!= elementWithKeyboardFocus) {
			//deactivate old activeElement
			if (elementWithKeyboardFocus != null && this.getKeyBoardFocus().isActive()) {
				elementWithKeyboardFocus.setActive(false);
			}
			
			//activate new activeElement
			this.setKeyBoardFocus(element);
			
			if (element != null) {
				element.handleClick();
				element.setActive(true);
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
