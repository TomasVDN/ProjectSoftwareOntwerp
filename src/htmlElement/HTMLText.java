package htmlElement;

import GUIElements.GUIElement;
import GUIElements.Text;

public class HTMLText extends ContentSpan{

	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HTMLText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "TEXT: " + text;
	}

	@Override
	public Text transformToGUI(int width, int heigth, int y, int x) {
		return new Text(x,y,10,10,this.getText(), null);//TODO: aanpassen
	}

}
