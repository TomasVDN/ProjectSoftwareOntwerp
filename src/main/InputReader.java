package main;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
import canvaswindow.MyCanvasWindow;

public class InputReader {
	
	private MyCanvasWindow window;
	
	public InputReader(MyCanvasWindow window) {
		this.window = window;
	}
	
	public MyCanvasWindow getCanvasWindow() {
		return this.window;
	}
	
	public void readFile(String path) {
		String textToDisplay = "";
		
		try {
    	    File file = new File(path);
    	    Scanner scan = new Scanner(file);
    	    
    	    while (scan.hasNextLine()) {
    	    	 String data = scan.nextLine();
    	    	 //handleLine(data);
    	    	 textToDisplay += data + "\n";
    	     }
    	    handleLine(textToDisplay);
    	    
    	    scan.close();
    	} catch (FileNotFoundException e) {
    		File file = new File("src/error.txt");
    	    Scanner scan;
			try {
				scan = new Scanner(file);
				while (scan.hasNextLine()) {
	    	    	 String data = scan.nextLine();
	    	    	 //handleLine(data);
	    	    	 textToDisplay += data + "\n";
	    	     }
			   	   
	    	    handleLine(textToDisplay);
	    	    
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