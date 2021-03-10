package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.TableCellGUI;
import GUIElements.Text;
import canvaswindow.MyCanvasWindow;

class TestHTMLText {
	
	String textValue = "text example";
	HTMLText htmlText;
	Text guiText;
	Text guiTextReference;
	
	void setUpReference() {
		guiTextReference = new Text(10, 20, textValue); //TODO heb dit aangepast ma ik weet nie of dat da juist is
	}
	
	@Test
	@DisplayName("Test the normal behaviour of HTMLText.")
	void testNormalBehaviour() {
		setUpReference();
		htmlText = new HTMLText(textValue);
		guiText = htmlText.transformToGUI(10, 20, 30, 40);
		System.out.println(guiText.getX());
		System.out.println(guiTextReference.getX());
		assert guiText.getX() == guiTextReference.getX();
		assert guiText.getY() == guiTextReference.getY();
		assert guiText.getWidth() == guiTextReference.getWidth();
		assert guiText.getHeight() == guiTextReference.getHeight();
		assertEquals(guiTextReference.getText(), guiText.getText());
		// assertEquals(guiTextReference, guiText);
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given in the constructor.")
	void testNullBehaviour() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new HTMLText(null);
		});
		assertTrue(exception.getMessage().contains("A HTMLText can't be constructed with null argument."));
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given in the setText method.")
	void testSetNullBehaviour() {
		htmlText = new HTMLText(textValue);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			htmlText.setText(null);
		});
		assertTrue(exception.getMessage().contains("The text of HTMLText can't be set to null."));
	}
}
