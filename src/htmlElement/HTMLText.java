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
		if (text == null) {
			throw new IllegalArgumentException("The text of HTMLText can't be set to null.");
		}
		this.text = text;
	}

	public HTMLText(String text) {
		if (text == null) {
			throw new IllegalArgumentException("A HTMLText can't be constructed with null argument.");
		}
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
