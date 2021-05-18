package GUIElements;

public abstract class rootPane extends Pane {

	public rootPane(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public abstract rootPane copy() ;

	
}
