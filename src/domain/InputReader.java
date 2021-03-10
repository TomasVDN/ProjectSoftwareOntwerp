package domain;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import browsrhtml.BrowsrDocumentValidator;
import htmlElement.ContentSpan;

public class InputReader {
	
	private HTMLDecoder decoder;
	
	public InputReader() {
	}

	/**
	 * Opens the url, and return an array of ContentSpans describing the HTML layout. If an error occur, return an array with Error 404 content.
	 * @param path - url to open
	 * @return ArrayList<ContentSpan>
	 */
	public ArrayList<ContentSpan> readFile(String path) {
		//this prevents typing the url each time (used for testing) 
		//path = "https://people.cs.kuleuven.be/~bart.jacobs/browsrtest.html";

		try {
			//open url, and copy content to Buffered reader
			URL url = new URL(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			
			//read line/line until BufferedReader is empty. sb will contain the whole HTML file as a string.
			String line;
	        String sb = new String();

	        while ((line = br.readLine()) != null) {
	        	sb += line + "\n";
	        }
	        
	        //Check if valid browsr html file
	        try {
	        	BrowsrDocumentValidator.assertIsValidBrowsrDocument(sb);
	        } catch (RuntimeException r) {
	        	sb = "Error occured. Reason: not a valid Browsr document.\n";
	        }
	        
	        //create a new decoder and return the HTMLElements created through his functions.
	        decoder = new HTMLDecoder(sb);
	        return decoder.createElements();
	        
		} catch (IOException e) {
            String sb = "Error 404\n";
            decoder = new HTMLDecoder(sb);
            return decoder.createElements();
        }
			
	}

}