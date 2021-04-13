package htmlElement;

import GUIElements.GUIElement;
import GUIElements.TextBox;
import events.EventReader;

public class HTMLTextBox extends HTMLInput {

	String name;
	int WIDTH=80;
	int HEIGHT = 25;
	
	public HTMLTextBox(String name) {
		this.name=name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TEXTBOX[" + name + "]";
	}

	@Override
	public GUIElement transformToGUI(int x, int y, int width, int height, EventReader eventReader) {
		return new TextBox(x, y, WIDTH, HEIGHT,this.name);
	}

}
