package htmlElement;

import GUIElements.Text;
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

	public Text transformToGUI(int x, int y, int width, int heigth) {
		return new Text(x, y, this.getText());
	}

}
