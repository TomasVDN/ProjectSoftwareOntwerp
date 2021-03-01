package inputReader;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import browsrhtml.BrowsrDocumentValidator;
import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import canvaswindow.MyCanvasWindow;
import guiElement.GUIElement;
import toNew.HTMLDecoder;

public class InputReader {
	
	private HTMLDecoder decoder;
	
	public InputReader() {
	}

	public void readFile(String path) {
		//TODO remove this help function (used for testing) 
//		path = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";

		//deze url => String komt van https://www.tutorialspoint.com/how-to-read-the-contents-of-a-webpage-into-a-string-in-java
		try {
			URL url = new URL(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

	        String sb = new String();

	        while ((line = br.readLine()) != null) {
	        	sb += line + "\n";
	        }
	        
	        decoder = new HTMLDecoder(sb);
	        
    	} catch (IOException e) {
    		File file = new File("src/error.txt");
    	    Scanner scan;
			try {
				scan = new Scanner(file);
				while (scan.hasNextLine()) {
	    	    	 String data = scan.nextLine();
	    	     }
	    	    
	    	    scan.close();
			} catch (FileNotFoundException e1) {
				System.out.print("Someone deleted the error file...");
			}
 
    	}		
	}

}