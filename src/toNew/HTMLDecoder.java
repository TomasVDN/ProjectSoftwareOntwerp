package toNew;

import java.io.StringReader;
import java.util.ArrayList;

import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import guiElement.GUIElement;
import guiElement.Hyperlink;
import guiElement.Text;
import htmlElement.ContentSpan;
import htmlElement.HTMLHyperlink;
import htmlElement.HTMLTable;
import htmlElement.HTMLTableCell;
import htmlElement.HTMLTableRow;
import htmlElement.HTMLText;

public class HTMLDecoder {

	private HtmlLexer lexer;
	
	public HTMLDecoder(String input) {
		lexer = new HtmlLexer(new StringReader(input));
	}

	public ArrayList<ContentSpan> createElements(){
		ArrayList<ContentSpan> list = new ArrayList<>();
		while (lexer.getTokenType() != TokenType.END_OF_FILE) {
			list.add(createHTMLElement());
		}
		return list;
	}
	
	public ContentSpan createHTMLElement() {
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
	
	public HTMLHyperlink createHyperlink() {
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
		
		while (eat() != TokenType.CLOSE_TAG) {
		}
		
		HTMLText htmlText = handleText();
		
		return new HTMLHyperlink(url, htmlText);
	}
	
	private HtmlLexer.TokenType eat() {
		HtmlLexer.TokenType t = lexer.getTokenType();
		lexer.eatToken();
		return t;
	}
	
	private HTMLText handleText() {
		String content = "";
		while (lexer.getTokenType() == TokenType.TEXT) {
			content += " " + lexer.getTokenValue();
			lexer.eatToken();
		}
		
		return new HTMLText(content);
	}
	
	public HTMLTableCell createCell() {
		System.out.print(lexer.getTokenType() + "\n");
		System.out.print(lexer.getTokenValue() + "\n");
		System.out.println(lexer.getTokenType() == TokenType.OPEN_START_TAG);
		System.out.println(lexer.getTokenValue().equals(lexer.getTokenValue()));
		if (lexer.getTokenType() == TokenType.OPEN_START_TAG && lexer.getTokenValue() == "td"){
			lexer.eatToken(); //consume td
			lexer.eatToken();
		}
		System.out.print(lexer.getTokenType() + "\n");
		System.out.print(lexer.getTokenValue() + "\n");
		if ((lexer.getTokenType() == TokenType.OPEN_START_TAG && lexer.getTokenValue() == "td") || (lexer.getTokenType() == TokenType.OPEN_START_TAG && lexer.getTokenValue() == "tr") || (lexer.getTokenType() == TokenType.OPEN_END_TAG && lexer.getTokenValue() == "table")){
			return new HTMLTableCell(new HTMLText(""));
		}
		
		return new HTMLTableCell(createHTMLElement());	
	}
	
	public HTMLTableRow createRow() {
		lexer.eatToken();
		lexer.eatToken();
		HTMLTableRow row = new HTMLTableRow();
		while ((lexer.getTokenType() != TokenType.OPEN_START_TAG && lexer.getTokenValue() != "tr") || (lexer.getTokenType() != TokenType.OPEN_END_TAG && lexer.getTokenValue() != "table")) {
			row.addCell(createCell());
		}
		return row;
	}
	
	public HTMLTable createTable() {
		lexer.eatToken(); //consume <table>
		lexer.eatToken();
		HTMLTable table = new HTMLTable();
		while (lexer.getTokenType() != TokenType.OPEN_END_TAG && lexer.getTokenValue() != "table") {
			table.addRow(createRow());
		}
		lexer.eatToken(); //remove End tag table
		lexer.eatToken();
		return table;	
	}
}


//<table>
//<tr><td>HTML elements partially supported by Browsr:
//<tr><td>
//  <table>
//    <tr><td><a href="a.html">a</a><td>Hyperlink anchors
//    <tr><td><a href="table.html">table</a><td>Tables
//    <tr><td><a href="tr.html">tr</a><td>Table rows
//    <tr><td><a href="td.html">td</a><td>Table cells containing table data
//  </table>
//</table>