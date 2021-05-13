package domain;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import browsrhtml.BrowsrDocumentValidator;

public class InputReader {

	/**
	 * Opens the url, and return a String with the HTMLCode. If an error occur, return an array with Error 404 content.
	 * @param path - url to open
	 * @param saver - saver to send the HTML code to
	 * @return ArrayList of ContentSpan
	 */
	public String readFile(String path) {

		try {
			String sb = readURLToBuffer(path);
	        
	        //Check if valid browsr html file
	        try {
	        	BrowsrDocumentValidator.assertIsValidBrowsrDocument(sb);
	        } catch (RuntimeException r) {
	        	sb = "Error occured. Reason: not a valid Browsr document.\n";
	        }
	        return sb;	        	        
		} catch (IOException e) {
            String sb = "Error occured. Make sure you entered a valid URL.\n";
            return sb;
        }
	}

	/**
	 * Opens the url, and read it to a string.
	 * @param path - url to open
	 * @return String with the content of the page
	 * @throws MalformedURLException if not a valid URL
	 * @throws IOException if an error occurs during writing to the bufferedReader
	 */
	private String readURLToBuffer(String path) throws MalformedURLException, IOException {
		URL url = new URL(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String line;
		String sb ="";

		while ((line = br.readLine()) != null) {
			sb +=  line + "\n";
		}
		
		return sb;
	}
}