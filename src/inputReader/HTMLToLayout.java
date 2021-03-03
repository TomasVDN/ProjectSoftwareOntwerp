package inputReader;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.io.StringReader;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import GUIElements.GUIElement;
import GUIElements.Hyperlink;
import GUIElements.Text;
import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;

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
	

	
	private String handleText() {
		String content = "";
		while (lexer.getTokenType() == TokenType.TEXT) {
			content += " " + lexer.getTokenValue();
			lexer.eatToken();
		}
		return content;
	}
	
	private String handleUrlExtract() {
		while (lexer.getTokenType() != TokenType.IDENTIFIER) {
			lexer.eatToken();
		}
		
		String url = "";
		if (lexer.getTokenValue() == "href") {
			lexer.eatToken();
			lexer.eatToken();
			if (lexer.getTokenType() == TokenType.QUOTED_STRING) {
				url = lexer.getTokenValue();
			}
		}
		
		while (lexer.getTokenType() != TokenType.CLOSE_TAG) {
			lexer.eatToken();
		}
		
		lexer.eatToken();
		
		return url;
	}
	

	
}
