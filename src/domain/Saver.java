package domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Saver {

	private String htmlCode;
	
	public Saver() {
		this.htmlCode = "";
	}
	
	public void setHtmlCode(String htmlCode) {
		if (htmlCode == null) {
			return;
		}
		this.htmlCode = htmlCode;
	}

	public void saveToFile() {
		//TODO: ask for input for file naming
		File yourFile = new File("savedPages/output.html");
		try {
			yourFile.createNewFile();
			FileOutputStream outputFile = new FileOutputStream(yourFile, false); 
			outputFile.write(htmlCode.getBytes());
			outputFile.close();
		} catch (IOException e) {
			System.out.print("Could not create file");
			e.printStackTrace();
		}
		
	}
}
