package inputReader;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

import browsrhtml.BrowsrDocumentValidator;
import browsrhtml.HtmlLexer;
import browsrhtml.HtmlLexer.TokenType;
import canvaswindow.MyCanvasWindow;

public class InputReader {
	
	private MyCanvasWindow window;
	String input = "Nope";
	
	public InputReader(MyCanvasWindow window) {
		this.window = window;
	}
	
	public MyCanvasWindow getCanvasWindow() {
		return this.window;
	}
	
	public void readFile(String path) {
		//TODO remove this help function (used for testing) 
		path = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";

		//deze url => String komt van https://www.tutorialspoint.com/how-to-read-the-contents-of-a-webpage-into-a-string-in-java
		try {
			var url = new URL(path);
			var br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

	        var sb = new StringBuilder();

	        while ((line = br.readLine()) != null) {
	        	sb.append(line);
	            sb.append(System.lineSeparator());
	        }
	        
	        HTMLToLayout toLayout = new HTMLToLayout(sb.substring(0), 20, 40);
	        toLayout.createElements();

    	} catch (IOException e) {
    		File file = new File("src/error.txt");
    	    Scanner scan;
			try {
				scan = new Scanner(file);
				while (scan.hasNextLine()) {
	    	    	 String data = scan.nextLine();
	    	    	 handleLine(data);
	    	     }
	    	    
	    	    scan.close();
			} catch (FileNotFoundException e1) {
				System.out.print("Someone deleted the error file...");
			}
 
    	}		
	}

	private void handleLine(String data) {
		window.addTextElement(data);
	}	
}