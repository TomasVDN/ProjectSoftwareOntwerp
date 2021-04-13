package htmlElement;

import java.util.ArrayList;

import GUIElements.Form;
import GUIElements.GUIElement;
import events.EventReader;

public class HTMLForm extends ContentSpan {
	
	ContentSpan element;
	String action;
	
	public HTMLForm(String action,ContentSpan element) {
		this.action = action;
		this.element=element;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FORM < " +element.toString() + ">";
	}

	@Override
	public Form transformToGUI(int x, int y, int width, int height,EventReader eventReader) {
		GUIElement gui= element.transformToGUI(x, y, width, height,eventReader);
		return new Form(gui,x,y,action,eventReader);
	}

}
