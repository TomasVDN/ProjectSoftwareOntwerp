package htmlElement;

import java.awt.Color;

import GUIElements.Button;
import GUIElements.GUIElement;
import GUIElements.Text;
import events.EventReader;

public class HTMLButton extends HTMLInput{
	
	HTMLText textInButton = new HTMLText("Input");
	
	public HTMLButton() {
		
	}

	@Override
	public String toString() {
		return "BUTTON";
	}

	@Override
	public Button transformToGUI(int x, int y, int width, int height, EventReader eventReader) {
		Text guiText = textInButton.transformToGUI(x, y, width, height, eventReader);
		return new Button(x, y,guiText,true, Color.GRAY);
	}

}
