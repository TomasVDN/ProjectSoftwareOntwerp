package domain;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import htmlElement.ContentSpan;

public class InputReader {
	
	private HTMLDecoder decoder;
	
	public InputReader() {
	}

	public ArrayList<ContentSpan> readFile(String path) {
		//this help function prevents typing the url each time (used for testing) 
		path = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";

		try {
			URL url = new URL(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

	        String sb = new String();

	        while ((line = br.readLine()) != null) {
	        	sb += line + "\n";
	        }
	        
	        decoder = new HTMLDecoder(sb);
	        return decoder.createElements();
    	} catch (IOException e) {
    		//TODO rewrite error handling
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
			return null; // if something wrong happened
    	}	
	}

}