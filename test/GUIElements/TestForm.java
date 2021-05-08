package GUIElements;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import EventListeners.ActionListener;
import EventListeners.FormListener;

public class TestForm {

	public static class FormListenerClass implements FormListener {


		public String textForm;

		@Override
		public void runUrlAttribute(String action) {
			this.textForm=action;
		}
	};

	FormListenerClass formListener = new FormListenerClass();
	Text hyperText = new Text(0,0,"This is a hyperlink");
	Hyperlink hyper = new Hyperlink(0, 0, hyperText, "https://blabla.com");
	Button button = new Button(0, 0, hyperText, true, Color.black);
	Form noElementForm = new Form(null, 0, 0, "action");
	Form formWithButton = new Form(button, 0, 0, "action");
	Container cont = new Container(0, 0, 0, 0);
	TextBox streetNumber = new TextBox(0, 0, 0, 0, "streetNumber");
	TextBox postCode = new TextBox(0, 0, 0, 0, "postCode");
	Form streetForm = new Form(cont, 0, 0, "searchAdress");
	
	@Before
	public void init() {
		formWithButton.addFormListener(formListener);
		streetForm.addFormListener(formListener);
	}
	
	@Test
	public void testNoAction() {
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			Form guiCell = new Form(hyper, 0, 0, null);
		});
		assertTrue(exception2.getMessage().contains("not a valid action"));
	}
	

	@Test
	public void testGetGUIAtPositionNull() {
		assertNull(noElementForm.getGUIAtPosition(0, 0));
	}
	
	@Test
	public void testGetGUIAtPosition() {
		assertEquals(button, formWithButton.getGUIAtPosition(0, 0));
	}

	@Test
	public void testGetGuiClass() {
		ArrayList<Button> array = new ArrayList<Button>();
		assertEquals(0,noElementForm.getGuiClass(Button.class, array).size());
		assertEquals(1,formWithButton.getGuiClass(Button.class, array).size());
	}


	@Test
	public void testEmptyClickButton() {
		noElementForm.addFormListener(formListener);
		noElementForm.clickButton();
		assertEquals("action?",formListener.textForm);
	}
	
	@Test
	public void testClickButtonNoSpecial() {
		cont.addElement(streetNumber);
		cont.addElement(postCode);
		cont.addElement(button);
		button.addListener(streetForm);
		
		button.addSingleClickListener(() ->{
			for(ActionListener listener: button.getListeners()) {
				listener.clickButton();
			}
		});
		
		streetNumber.replaceBox("24");
		postCode.replaceBox("3000");
		
		//clicks on button
		button.handlePressClick();
		button.handleReleaseClick(true);
		
		assertEquals("searchAdress?streetNumber=24&postCode=3000", formListener.textForm);
	}
	
	
	@Test
	public void testClickButtonSpecial() {
		cont.addElement(streetNumber);
		cont.addElement(postCode);
		cont.addElement(button);
		button.addListener(streetForm);
		
		button.addSingleClickListener(() ->{
			for(ActionListener listener: button.getListeners()) {
				listener.clickButton();
			}
		});
		
		streetNumber.replaceBox("!!#");
		postCode.replaceBox("+,:");
		
		//clicks on button
		button.handlePressClick();
		button.handleReleaseClick(true);
		
		assertEquals("searchAdress?streetNumber=%21%21%23&postCode=%2B%2C%3A", formListener.textForm);
	}
	


	@Test
	public void testAddFormListenerEmpty() {
		noElementForm.addFormListener(null);
		assertEquals(0,noElementForm.getListeners().size());
	}
	
	@Test
	public void testAddFormListener() {
		noElementForm.addFormListener(formListener);
		assertEquals(1,noElementForm.getListeners().size());
	}
	

	@Test
	public void testRemoveFormListener() {
		noElementForm.addFormListener(formListener);
		assertEquals(1,noElementForm.getListeners().size());
		noElementForm.removeFormListener(formListener);
		assertEquals(0,noElementForm.getListeners().size());
	}

}
