package domain;

import java.io.StringReader;
import java.util.ArrayList;

import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import htmlElement.*;

public class HTMLDecoder {

	private HtmlLexer lexer;
	private boolean insideForm= false;
	

	/**
	 * Constructor of the HTMLDecoder class (uses the included lexer).
	 * @param input - String to convert to HTMLElements
	 */
	public HTMLDecoder(String input) {
		if(input==null) {
			throw new IllegalArgumentException();
		}
		lexer = new HtmlLexer(new StringReader(input));
	}

	/**
	 * Method used to go trough the given string.
	 * @return ArrayList containing all HTMLElements of given string
	 */
	public ArrayList<ContentSpan> createElements(){
		ArrayList<ContentSpan> list = new ArrayList<>();
		while (lexer.getTokenType() != TokenType.END_OF_FILE) {
			list.add(createHTMLElement());
		}
		return list;
	}
	
	/**
	 * Method used to handle the Text, Hyperlink and table tokenTypes.
	 * @return HTMLElement
	 */
	private ContentSpan createHTMLElement() {
		if (lexer.getTokenType() == TokenType.TEXT) {
			return handleText();
		}
		
		switch (lexer.getTokenValue()) {
			case "a":
				return createHyperlink();
			case "table":
				return createTable();
			case "form":
				return createForm();
			case "input":
				return createInput();
			default:
				return null;
		}		
	}
	
	/**
	 * Creates a Hyperlink Element, with all its options.
	 * @return HTMLHyperlink element
	 */
	private HTMLHyperlink createHyperlink() {
		while (lexer.getTokenType() != TokenType.IDENTIFIER) {
			lexer.eatToken();
		}
		
		String url = "";
		if (lexer.getTokenValue().equals("href")) {
			lexer.eatToken();
			lexer.eatToken();
			if (lexer.getTokenType() == TokenType.QUOTED_STRING) {
				url = lexer.getTokenValue();
				lexer.eatToken(); // consumes url
			}
		}
		while (eat() != TokenType.CLOSE_TAG) {
		}
		
		HTMLText htmlText = handleText();
		lexer.eatToken();// consumes endtag
		lexer.eatToken();
		return new HTMLHyperlink(url, htmlText);
	}
	
	/**
	 * Eats one token and returns its type
	 * @return the type of the eaten token.
	 */
	private HtmlLexer.TokenType eat() {
		HtmlLexer.TokenType t = lexer.getTokenType();
		lexer.eatToken();
		return t;
	}
	
	/**
	 * Creates a Text Element, with all its options.
	 * @return HTMLText element
	 */
	private HTMLText handleText() {
		String content = "";
		content += lexer.getTokenValue();
		lexer.eatToken();
		while (lexer.getTokenType() == TokenType.TEXT) {
			content += " " + lexer.getTokenValue();
			lexer.eatToken();
		}
		
		return new HTMLText(content);
	}
	
	/**
	 * Creates a Cell Element, with all its options.
	 * @return HTMLCell element
	 */
	private HTMLTableCell createCell() {
		if (lexer.getTokenType() == TokenType.OPEN_START_TAG && lexer.getTokenValue().equals("td")){
			lexer.eatToken(); //consume td
			lexer.eatToken();
		}
		if ((lexer.getTokenType() == TokenType.OPEN_START_TAG && lexer.getTokenValue().equals("td")) || (lexer.getTokenType() == TokenType.OPEN_START_TAG && lexer.getTokenValue().equals("tr")) || (lexer.getTokenType() == TokenType.OPEN_END_TAG && lexer.getTokenValue().equals("table"))){
			return new HTMLTableCell(new HTMLText(""));
		}
		
		return new HTMLTableCell(createHTMLElement());	
	}
	
	/**
	 * Creates a Row Element, with all its options.
	 * @return HTMLRowelement
	 */
	private HTMLTableRow createRow() {
		lexer.eatToken();
		lexer.eatToken();
		HTMLTableRow row = new HTMLTableRow();
		while (! (lexer.getTokenType() == TokenType.OPEN_START_TAG &&  lexer.getTokenValue().equals("tr")) && !(lexer.getTokenType() == TokenType.OPEN_END_TAG &&  lexer.getTokenValue().equals("table"))) {
			row.addCell(createCell());
		}
		return row;
	}
	
	/**
	 * Creates a Table Element, with all its options.
	 * @return HTMLTable element
	 */
	private HTMLTable createTable() {
		lexer.eatToken(); //consume <table>
		lexer.eatToken();
		HTMLTable table = new HTMLTable();
		while (!(lexer.getTokenType() == TokenType.OPEN_END_TAG &&  lexer.getTokenValue().equals("table"))) {
			table.addRow(createRow());
		}
		lexer.eatToken(); //remove End tag table
		lexer.eatToken();
		return table;	
	}
	
	/**
	 * Creates a form html Object
	 * @return
	 */
	private HTMLForm createForm() { 
		if(this.isInsideForm()) { // form inside a form
			throw new RuntimeException("A form cannot exist inside a form");
		}
		
		this.setInsideForm(true);
		this.eat();
		
		String action ="";
		if(lexer.getTokenValue().equals("action")) {
			while(lexer.getTokenType() != TokenType.QUOTED_STRING) {
				lexer.eatToken();
			}
			// lexer is on a token value
			action = lexer.getTokenValue();
		}
		
		while(this.eat() != TokenType.CLOSE_TAG) {} // eats untill close tag is encountered
		
		ContentSpan element = this.createHTMLElement();//this.innerContentSpan();
		lexer.eatToken(); //remove End tag form
		lexer.eatToken();
		
		this.setInsideForm(false);
		return new HTMLForm(action,element);
	}
	
	/**
	 * Eats the tokens until a given type.
	 * @param type - the type to stop eating when encountered
	 */
	private void eatUntillType(HtmlLexer.TokenType type) {
		while(lexer.getTokenType() != type) {
			lexer.eatToken();
		}
	}
	
	/**
	 * Creates an htmlElement corresponding to its input tag
	 * @return
	 */
	private ContentSpan createInput() {
		if(! lexer.getTokenValue().equals("input")) {
			throw new IllegalArgumentException("not an input");
		}
		this.eat(); // removes the start tag
		String type=null;
		String name=null;
		while(lexer.getTokenType()!=TokenType.CLOSE_TAG) {
			switch (lexer.getTokenValue()){
			case "type": 
				eatUntillType(TokenType.QUOTED_STRING);
				type = lexer.getTokenValue();
				lexer.eatToken(); // eats the string
				break;
			case "name": 
				eatUntillType(TokenType.QUOTED_STRING);
				name = lexer.getTokenValue();
				lexer.eatToken();
				break;
			default: 
				lexer.eatToken();// when not of the above it is not implemented
				break;
			}	
		}
		lexer.eatToken(); // eats the endTag
		return constructInput(name, type);
	}
	
	/**
	 * Makes an HTMLInput type HTMLelement.
	 * @param name - name of the HTMLInputElement
	 * @param type - type of the HTMLInputElement
	 * @return the HTMLElement if button or textBox, null otherwise
	 */
	private HTMLInput constructInput(String name,String type) {
		switch(type) {
		case "text":
			return new HTMLTextBox(name);
		case "submit":
			return new HTMLButton();
		default:
			return null;
		}
	}
	
	/**
	 * Checks if element is inside a Form
	 * @return this.insideForm
	 */
	private boolean isInsideForm() {
		return insideForm;
	}

	/**
	 * Sets the isInsideForm variable to the given value.
	 * @param insideForm - the new value of isInsideForm
	 */
	public void setInsideForm(boolean insideForm) {
		this.insideForm = insideForm;
	}
}