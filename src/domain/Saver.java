package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Saver class, used to keep track of the last loaded HTML file. The saveToFile method can be called to write the saved HTML code to a file.
 * @author rpone
 *
 */
public class Saver {

	private String htmlCode;
	
	/**
	 * Constructor of Saver class.
	 */
	public Saver() {
		this.htmlCode = "";
	}
	
	/**
	 * Method to set the saved HTML code.
	 * @param htmlCode - the HTML code to save.
	 */
	public void setHtmlCode(String htmlCode) {
		if (htmlCode == null) {
			return;
		}
		this.htmlCode = htmlCode;
	}

	/**
	 * Saves the code saved in this.htmlCode to the file with the given name.
	 * @param filename - name of the ouptut file. If this is null or empty, it defaults to output.html.
	 */
	public void saveToFile(String filename) {
		if (notValidFilename(filename)) {
			filename = "output.html";
		}
		
		File yourFile = new File("savedPages/" + filename);
		
		try {
			writeHTMLCodeToFile(yourFile);
		} catch (IOException e) {
			System.out.print("Could not create file");
			e.printStackTrace();
		}
		
	}

	/**
	 * Writes this.htmlCode to the given File.
	 * @param yourFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void writeHTMLCodeToFile(File yourFile) throws IOException, FileNotFoundException {
		yourFile.createNewFile();
		FileOutputStream outputFile = new FileOutputStream(yourFile, false); 
		outputFile.write(htmlCode.getBytes());
		outputFile.close();
	}

	/**
	 * Checks if the given filename is a valid filename.
	 * @param filename - filename to check.
	 * @return true if filename != null && filename != ""
	 */
	private boolean notValidFilename(String filename) {
		return filename == null || filename == "";
	}
}
