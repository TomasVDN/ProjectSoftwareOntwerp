package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import EventListeners.ActionListener;
import GUIElements.Button;


class testHTMLButton {
	
	public static class ActionListenerClass implements ActionListener {


		public boolean clicked = false;

		@Override
		public void clickButton() {
			clicked = true;
		}


	};
	
	ActionListenerClass actionListener = new ActionListenerClass();
	

	HTMLButton htmlButton = new HTMLButton();
	
	@Test
	void testTransformToGUI() {
		Button button = htmlButton.transformToGUI(0, 0, 0, 0);
		assertEquals("Submit",button.getText().getText());
		button.addListener(actionListener);
		button.handlePressClick(0,0);
		button.handleReleaseClick(true);
		assertTrue(actionListener.clicked);
	}

}
