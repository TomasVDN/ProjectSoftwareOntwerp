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
	
}
