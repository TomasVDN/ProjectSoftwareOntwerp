package htmlElement;

import GUIElements.Form;
import GUIElements.GUIElement;
import events.EventReader;

public class HTMLForm extends ContentSpan {
	
	ContentSpan element;
	
	public ContentSpan getElement() {
		return element;
	}

	public String getAction() {
		return action;
	}

	String action;
	
	public HTMLForm(String action,ContentSpan element) {
		if(action == null) {
			throw new IllegalArgumentException("not a valid action");
		}
		this.action = action;
		this.element=element;
	}

	@Override
	public String toString() {
		String result = "FORM < ";
		if(element!=null) {
			result+=element.toString();
		}
		return result + ">";
	}

	@Override
	public Form transformToGUI(int x, int y, int width, int height,EventReader eventReader) {
		GUIElement gui =null;
		if(this.element!=null) {
			gui= element.transformToGUI(x, y, width, height,eventReader);
		}
		return new Form(gui,x,y,action,eventReader);
	}

}
