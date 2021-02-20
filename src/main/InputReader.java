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
    	    File file = new File("src/input.txt");
    	    Scanner scan = new Scanner(file);
    	    
    	    while (scan.hasNextLine()) {
    	    	 String data = scan.nextLine();
    	    	 handleLine(data);
    	     }
    	    
    	    scan.close();
    	} catch (FileNotFoundException e) {
    		System.out.println("An error occurred.");
    	}
		
	}

	private void handleLine(String data) {
		window.addTextElement(data);
	}
}