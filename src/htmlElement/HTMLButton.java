package htmlElement;

import java.awt.Color;

import EventListeners.ActionListener;
import GUIElements.Button;
import GUIElements.Text;
import events.*;

public class HTMLButton extends HTMLInput{
	
	private HTMLText textInButton = new HTMLText("Submit");
	
	public HTMLButton() {
		
	}

	@Override
	public String toString() {
		return "BUTTON";
	}

	@Override
	public Button transformToGUI(int x, int y, int width, int height, EventReader eventReader) {
		Text guiText = textInButton.transformToGUI(x, y, width, height, eventReader);
		Button button = new Button(x, y, guiText, true, Color.LIGHT_GRAY);
		button.addSingleClickListener(() ->{
			System.out.println("CLICK OP BUTTON");
			for(ActionListener listener: button.getListeners()) {
				listener.clickButton();
			}
		});
		
		return button;

	}

}
