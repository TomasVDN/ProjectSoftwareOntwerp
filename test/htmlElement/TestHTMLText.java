package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import GUIElements.Hyperlink;
import GUIElements.TableCellGUI;
import GUIElements.Text;

class TestHTMLText {
	
	String textValue = "text example";;
	HTMLText htmlText;
	Text guiText;
	Text guiTextReference;
	
	void setUpReference() {
		guiTextReference = new Text(10, 20, 30, 40, null, textValue);
	}
	
	@Test
	@DisplayName("Test the normal behaviour of HTMLText.")
	void testNormalBehaviour() {
		setUpReference();
		htmlText = new HTMLText(textValue);
		guiText = htmlText.transformToGUI(10, 20, 30, 40, null);
		System.out.println(guiText.getX());
		System.out.println(guiTextReference.getX());
		assert guiText.getX() == guiTextReference.getX();
		assert guiText.getY() == guiTextReference.getY();
		assert guiText.getWidth() == guiTextReference.getWidth();
		assert guiText.getHeight() == guiTextReference.getHeight();
		assertEquals(guiTextReference.getText(), guiText.getText());
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
	@DisplayName("Test the behaviour if an empty string is given in the constructor.")
	void testEmptyStringBehaviour() {
		htmlText = new HTMLText("");
		//TODO wat voor gedrag wordt er verwacht?
	}
}
