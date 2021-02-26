package inputReader;

import java.io.StringReader;
import java.util.ArrayList;

import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import htmlElement.GUIElement;
import htmlElement.Text;

public class HTMLToLayout {
	
	private String textToDisplay;
	private int minX, minY;
	private ArrayList<GUIElement> listOfElements = new ArrayList<GUIElement>();
	private static HtmlLexer lexer;
	
	public HTMLToLayout(String text, int minX, int minY) {
		this.setTextToDisplay(text);
		this.setMinX(minX);
		this.setMinY(minY);
		lexer = new HtmlLexer(new StringReader(this.getTextToDisplay()));
	}

	/**
	 * @return the textToDisplay
	 */
	private String getTextToDisplay() {
		return textToDisplay;
	}

	/**
	 * @param textToDisplay the textToDisplay to set
	 */
	private void setTextToDisplay(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

	/**
	 * @return the minX
	 */
	private int getMinX() {
		return minX;
	}

	/**
	 * @param minX the minX to set
	 */
	private void setMinX(int minX) {
		if (minX < 0) {
			this.minX = 0;
		}else {
			this.minX = minX;
		}
	}

	/**
	 * @return the minY
	 */
	private int getMinY() {
		return minY;
	}

	/**
	 * @param minY the minY to set
	 */
	private void setMinY(int minY) {
		if (minY < 0) {
			this.minY = 0;
		} else {
			this.minY = minY;
		}
	}

	/**
	 * Adds given element to listOfElements
	 * @param elementToAdd element to add to listOfElements
	 */
	private void addElementToList(GUIElement elementToAdd) {
		this.listOfElements.add(elementToAdd);
	}
	
	/**
	 * Returns the listOfElements of this class
	 * @return this.listOfElements
	 */
	private ArrayList<GUIElement> getListOfElements(){
		return this.listOfElements;
	}
	
	public ArrayList<GUIElement> createElements() {
		
		while (lexer.getTokenType() != TokenType.END_OF_FILE) {
			switch (lexer.getTokenType()) {
			case TEXT:	
				this.handleText();
				break;
			case OPEN_START_TAG:

			default:
				break;
			}
			
			lexer.eatToken();			
		}
		
		return this.getListOfElements();
		
	}
	
	private void handleText() {
		String content = "";
		while (lexer.getTokenType() == TokenType.TEXT) {
			content += " " + lexer.getTokenValue();
			lexer.eatToken();
		}
	}
	
	private void handleHyperlinks() {
		
	}
}
