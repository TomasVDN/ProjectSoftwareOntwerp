package htmlElement;

import java.awt.Color;

import EventListeners.ActionListener;
import GUIElements.Button;
import GUIElements.Text;

public class HTMLButton extends HTMLInput{
	
	private HTMLText textInButton = new HTMLText("Submit");
	
	public HTMLButton() {
		
	}

	@Override
	public String toString() {
		return "BUTTON";
	}

	@Override
	public Button transformToGUI(int x, int y, int width, int height) {
		Text guiText = textInButton.transformToGUI(0, 0, width, height);
		Button button = new Button(x, y, guiText, true, Color.LIGHT_GRAY);
		button.addSingleClickListener(() ->{
			for(ActionListener listener: button.getListeners()) {
				listener.clickButton();
			}
		});
		
		return button;

	}

}
