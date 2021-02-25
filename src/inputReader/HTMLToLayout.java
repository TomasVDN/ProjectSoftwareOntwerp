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
	private HtmlLexer lexer;
	private boolean isTableActive = false;
	private boolean isTextActive = false;
	private String text = "";
	
	
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
	 * @return the isTableActive
	 */
	private boolean isTableActive() {
		return isTableActive;
	}

	/**
	 * @param isTableActive the isTableActive to set
	 */
	private void setTableActive(boolean isTableActive) {
		this.isTableActive = isTableActive;
	}

	/**
	 * @return the isTextActive
	 */
	private boolean isTextActive() {
		return isTextActive;
	}

	/**
	 * @param isTextActive the isTextActive to set
	 */
	private void setTextActive(boolean isTextActive) {
		this.isTextActive = isTextActive;
	}

	/**
	 * @return the text
	 */
	private String getText() {
		return text;
	}

	/**
	 * @param textToAdd the text to add to this.text
	 */
	private void addText(String textToAdd) {
		this.text = this.text + " " + textToAdd;
	}
	
	/**
	 * empties this.text
	 */
	private void emptyText() {
		this.text = "";
	}

	/**
	 * @return the textToDisplay
	 */
	private ArrayList<GUIElement> getListOfElements() {
		return listOfElements;
	}

	/**
	 * @param elementToAdd the GUIElement to add
	 */
	private void addToListOfElements(GUIElement elementToAdd) {
		this.listOfElements.add(elementToAdd);
	}
	
	/**
	 * Empties the arrayList listOfElements
	 */
	private void emptyListOfElements() {
		this.listOfElements.clear();;
	}
	
	public ArrayList<GUIElement> createListOfHTML() {
		this.emptyListOfElements();
		this.parser();
		return listOfElements;
	}
	
	private void parser() {
		while (lexer.getTokenType() != TokenType.END_OF_FILE) {
			if (this.isTextActive() && lexer.getTokenType() != TokenType.TEXT) {
				this.setTextActive(false);
				this.handleEndOfText();
			}
			switch (lexer.getTokenType()) {
			case OPEN_START_TAG:
				this.handleOpenStartTag();
				break;
			case TEXT:
				this.setTextActive(true);
				this.addText(lexer.getTokenValue());
			default:
				break;
			}
         	lexer.eatToken();
        }
	}
	//TODO ik werk hier nog met een increment -> niet dynamisch in functie van font groote
	private void handleOpenStartTag() {
		switch (lexer.getTokenValue()) {
		case "tr":
			this.addText("\n ");
			this.setMinY(this.getMinY() + 20);
			break;
		case "td":
			this.addText("     ");
			break;

		default:
			break;
		}
	}
	
	private void handleEndOfText() {
		Text textElement = new Text(this.getMinX(), this.getMinY(), 20, 20, this.getTextToDisplay());
		this.addToListOfElements(textElement);
		this.emptyText();
	}

}
