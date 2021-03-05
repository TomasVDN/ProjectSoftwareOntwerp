package htmlElement;

import GUIElements.GUIElement;
import GUIElements.Text;
import utils.FontMetricsHandle;
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
	public Text transformToGUI(int width, int heigth, int y, int x,FontMetricsHandle f) {
		return new Text(x,y,10,10,this.getText(), f);//TODO: aanpassen
	}

}
