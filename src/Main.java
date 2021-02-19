//dit is een test

import java.io.*;

import browsrhtml.*;
import browsrhtml.HtmlLexer.TokenType;
//import java.net.URL.openStream;  Geeft error , want ongebruikt

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) throws Exception {
    	
    	try {
    	    File file = new File("src/input.txt");
    	    Scanner scan = new Scanner(file);
    	      while (scan.hasNextLine()) {
    	        String data = scan.nextLine();
    	        handleLine(data);
    	      }
    	      scan.close();
    	    } catch (FileNotFoundException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    }
    
    public static void handleLine(String input) {
    	HtmlLexer lexer = new HtmlLexer(new StringReader(input));
    	while (lexer.getTokenType() != TokenType.END_OF_FILE) {
    		System.out.println(lexer.getTokenType());
    		System.out.println(lexer.getTokenValue());
    		lexer.eatToken();
    	}
    }


}
