package toNew;

import java.io.StringReader;
import java.util.ArrayList;

import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import guiElement.GUIElement;
import guiElement.Hyperlink;
import guiElement.Text;
import htmlelement.ContentSpan;
import htmlelement.HTMLHyperlink;

public class HTMLDecoder {

	private HtmlLexer lexer;
	
	public HTMLDecoder(String input) {
		lexer = new HtmlLexer(new StringReader(input));
	}

	public ArrayList<ContentSpan> createElements(){
		while (lexer.getTokenType() != TokenType.END_OF_FILE) {
//			System.out.print(lexer.getTokenType() + "\n");
//			System.out.print(lexer.getTokenValue() + "\n");

			switch (lexer.getTokenType()) {
			case TEXT:				
				break;
			case OPEN_START_TAG:
				createHTMLElement();
				break;
				
			default:
				break;
			}
			
			lexer.eatToken();
		}
		return null;
	}
	
	public ContentSpan createHTMLElement() {
		switch (lexer.getTokenValue()) {
		case "a":
			return createHyperlink();
			break;
		case "td":
			return createCell();
			break;
		case "tr":
			return createRow();
			break;
		case "table":
			return createTable();
			break;

		default:
			return null;
			break;
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
		
		while (lexer.getTokenType() != TokenType.CLOSE_TAG) {
			lexer.eatToken();
		}
		
		lexer.eatToken();
		
		return url;
	}
	
	
	public ArrayList<GUIElement> createElements() {
		
		while (lexer.getTokenType() != TokenType.END_OF_FILE) {
			System.out.print(lexer.getTokenType() + "\n");
			System.out.print(lexer.getTokenValue() + "\n");

			switch (lexer.getTokenType()) {
			case TEXT:
				Text text = new Text(minX, minY, 20, 20, this.handleText());
				this.addElementToList(text);
				break;
			case OPEN_START_TAG:
				this.handleOpenTag();
			default:
				break;
			}
			
			lexer.eatToken();
			this.setMinY(this.getMinY() + 10);
		}
		
		return this.getListOfElements();
	}
	
	private String eat() {
		String t = lexer.getTokenValue();
		lexer.eatToken();
		return t;
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
		
	}
	
	private void handleOpenTag() {
		switch (lexer.getTokenValue()) {
		case "a":
			String url = handleUrlExtract();
			String text = this.handleText();
			text = text.substring(1);
			this.addElementToList(new Hyperlink(minX, minY, 20, text, url));
			break;

		default:
			break;
		}
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