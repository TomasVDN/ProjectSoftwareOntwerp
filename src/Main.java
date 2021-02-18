//dit is een test

import java.io.*;

import browsrhtml.*;
import browsrhtml.HtmlLexer.TokenType;
//import java.net.URL.openStream;   Geeft error , weet niet waarom

public class Main {
    public static void main(String[] args) throws Exception {
    	String input = """
				<a>
				  <b x="foo"><cc xx="foo" yy="baz">Some text</c></b>
				</a>
				""";
    	HtmlLexer lexer = new HtmlLexer(new StringReader(input));
    	while (lexer.getTokenType() != TokenType.END_OF_FILE) {
    		System.out.println(lexer.getTokenType());
    		System.out.println(lexer.getTokenValue());
    		lexer.eatToken();
    	}

    }


}
