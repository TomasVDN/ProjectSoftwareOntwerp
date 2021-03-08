package htmlElement;

import GUIElements.Text;
import facades.EventReader;
/**
 * The text of a html
 * @author kobe
 *
 */
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

	public Text transformToGUI(int x, int y, int width, int heigth, EventReader e) {
		return new Text(x, y, 10, 10, e, this.getText());//TODO weghalen grootte en breedte
	}

}
