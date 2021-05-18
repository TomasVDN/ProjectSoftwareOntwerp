package htmlElement;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.awt.FontMetrics;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import GUIElements.Hyperlink;
import canvaswindow.FontMetricGetter;
import canvaswindow.MyCanvasWindow;

import facades.BrowsrController;
import facades.WindowManager;

class TestHTMLHyperlink {
	
	String textValue = "text example";
	String url = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";
	HTMLText htmlText;
	HTMLHyperlink htmlHyperlink;
	Hyperlink guiHyperlink;
	Hyperlink guiHyperlinkReference;
	Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
	FontMetrics fontMetrics;
	
	void setUpReference() {
		htmlText = new HTMLText(textValue);
		guiHyperlinkReference = new Hyperlink(10, 20, htmlText.transformToGUI(10, 20, 30, 40), url);
	}

	@Test
	@DisplayName("Test the normal behaviour of HTMLText.")
	void testNormalBehaviour() {
		htmlText = new HTMLText(textValue);
		htmlHyperlink = new HTMLHyperlink(url, htmlText);
		guiHyperlink = htmlHyperlink.transformToGUI(10, 20, 30, 40);
		setUpReference();
		assert guiHyperlink.getX() == guiHyperlinkReference.getX();
		assert guiHyperlink.getY() == guiHyperlinkReference.getY();
		assert guiHyperlink.getWidth() == guiHyperlinkReference.getWidth();
		assert guiHyperlink.getHeight() == guiHyperlinkReference.getHeight();
		assertEquals(guiHyperlinkReference.getUrl(), guiHyperlink.getUrl());
		assertEquals(guiHyperlinkReference.getText(), guiHyperlink.getText());
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given for url in the constructor.")
	void testUrlNullBehaviour() {
		htmlText = new HTMLText(textValue);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new HTMLHyperlink(null, htmlText);
		});
		assertTrue(exception.getMessage().contains("The URL can't be null in the constructor of HTMLHyperlink."));
	}
	
	@Test
	@DisplayName("Test the behaviour if null is given for text in the constructor.")
	void testTextNullBehaviour() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new HTMLHyperlink(url, null);
		});
		assertTrue(exception.getMessage().contains("The text can't be null in the constructor of HTMLHyperlink."));
	}
}
