package htmlElement;

import java.awt.Color;

import GUIElements.Button;
import GUIElements.GUIElement;
import GUIElements.Text;
import events.*;

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
		return new Button(x, y,guiText,true, Color.GRAY) {		
			@Override
			public void handleClick() {
				SubmitEvent event = new SubmitEvent();
				System.out.println("CLiCK OP BUTTON");
				for(EventListener listener: this.getListeners()) {
					listener.readEvent(event);
				}
			}
			
		};
				
				//Button(x, y,guiText,true, Color.GRAY);
	}

}
