package domain;

import java.io.StringReader;
import java.util.ArrayList;

import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import htmlElement.ContentSpan;
import htmlElement.HTMLHyperlink;
import htmlElement.HTMLTable;
import htmlElement.HTMLTableCell;
import htmlElement.HTMLTableRow;
import htmlElement.HTMLText;

public class HTMLDecoder {

	private HtmlLexer lexer;
	
	/**
	 * Constructor of the HTMLDecoder class (uses the included lexer).
	 * @param input - String to convert to HTMLElements
	 */
	public HTMLDecoder(String input) {
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
}