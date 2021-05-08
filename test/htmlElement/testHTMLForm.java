package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GUIElements.Button;
import GUIElements.Form;

class testHTMLForm {
	
	HTMLButton submitButton = new HTMLButton();

	@Test
	void testHTMLFormCont() {
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			HTMLForm form = new HTMLForm(null,null);
		});
		assertTrue(exception2.getMessage().contains("not a valid action"));
	}
	
	@Test
	void testHTMLFormCont2() {
		HTMLForm form = new HTMLForm("action",null);
		assertNull(form.getElement());
		assertEquals("action", form.getAction());
	}

	@Test
	void testTransformToGUI() {
		HTMLForm formHtml = new HTMLForm("action",submitButton);
		Form form = formHtml.transformToGUI(0, 0, 0, 0);
		assertTrue( form.getRootGui() instanceof Button);
		Button button = (Button) form.getRootGui();
		assertTrue(button.getListeners().contains(form));
	}

}
