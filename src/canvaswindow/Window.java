package canvaswindow;

import java.net.URL;

public class Window extends CanvasWindow {

	/*
	 * Constructor of window
	 */
	public Window(String title) {
		super(title);
		
	}

	/*
	 * When bar loses focus the window navigates to the typed URL
	 */
	public void navigateToURL(URL url) {
		
	}
	
	/*
	 * When ESC is pressed need to return to previous URL
	 */
	public void cancelURL() {
		
	}
	
	
	/*
	 * When there is an error in the given url , this method is called
	 */
	public void errorURL(URL url) {
		
	}
	
}
