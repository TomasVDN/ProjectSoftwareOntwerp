package main;
import java.io.*;
import java.net.URL;
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
		
		//TODO URL support && document validator
		try {
			 URL oracle = new URL(path);
		     BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		     String inputLine;
		     while ((inputLine = in.readLine()) != null) {
		    	 this.handleLine(inputLine);
		     }
		     in.close();
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