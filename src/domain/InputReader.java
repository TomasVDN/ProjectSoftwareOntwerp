package domain;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import browsrhtml.BrowsrDocumentValidator;

/**
 * Class used to read the HTML file from an URL. His function is to return this HTML file,
 * and if an error occurs (illegal URL, non valid Browsr document) to return an error message.
 *
 */
public class InputReader {

	/**
	 * Opens the URL, and return a String with the HTMLCode. If an error occur, return a String with the error content.
	 * @param path - URL to open
	 * @return String htmlCode
	 */
	public String readFile(String path) {

		try {
			String sb = readURLToBuffer(path);
	        
	        try {
	        	BrowsrDocumentValidator.assertIsValidBrowsrDocument(sb);
	        	
	        } catch (RuntimeException r) { // Entered when the HTML code is not a valid BrowsrController document.
	        	sb = "Error occured. Reason: not a valid Browsr document.\n";
	        }
	        return sb;	        	        
	        
		} catch (IOException e) { // Entered when an URL error occurs.
            String sb = "Error occured. Make sure you entered a valid URL.\n";
            return sb;
        }
	}

	/**
	 * Opens the URL, and read it to a string.
	 * @param path - URL to open
	 *
	 * @return String with the content of the page
	 * 
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