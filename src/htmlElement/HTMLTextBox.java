package htmlElement;

import GUIElements.GUIElement;
import GUIElements.TextBox;
import facades.BrowsrController;

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
	public GUIElement transformToGUI(int x, int y, int width, int height) {
		return new TextBox(x, y, WIDTH, HEIGHT,this.name);
	}

}
