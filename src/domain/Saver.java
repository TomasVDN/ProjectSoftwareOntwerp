package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class used to save string with HTML content to a file. The saveToFile method can be called to write the HTML code to a file.
 */
public class Saver {
	
	/**
	 * Saves the given HTML code to the file with the given name.
	 * @param filename - name of the output file. If this is null or empty, it defaults to output[time].html.
	 * @param htmlCode - The htmlCode given in a string to the saver
	 */
	public void saveToFile(String filename, String htmlCode) {
		if (notValidFilename(filename)) {
			filename = "output" + this.now();
		}
		
		if (!filename.contains(".html")) {
			filename += ".html";
		}
		
		File directory = new File("savedPages");
	    if (! directory.exists()){
	        directory.mkdir();
	    }
	    
		File yourFile = new File("savedPages/" + filename);
		
		try {
			writeHTMLCodeToFile(yourFile, htmlCode);
		} catch (IOException e) {
			System.out.print("Could not create file");
			e.printStackTrace();
		}
		
	}

	/**
	 * Writes the given htmlCode to the given File.
	 * @param yourFile - the file to write to.
	 * @param htmlCode - the string with the html code to write
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	private void writeHTMLCodeToFile(File yourFile, String htmlCode) throws IOException, FileNotFoundException {
		yourFile.createNewFile();
		FileOutputStream outputFile = new FileOutputStream(yourFile, false); 
		outputFile.write(htmlCode.getBytes());
		outputFile.close();
	}

	/**
	 * Checks if the given file name is not valid.
	 * @param filename - filename to check.
	 * @return true if filename == null or filename == ""
	 */
	private boolean notValidFilename(String filename) {
		return filename == null || filename == "";
	}
	
	private static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH-mm-ss";

	/**
	 *  Returns the string with as content the current time
	 * @return String with current time
	 */
	private String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
