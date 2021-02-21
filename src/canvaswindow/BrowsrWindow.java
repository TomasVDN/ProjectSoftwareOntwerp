package canvaswindow;

import java.net.URL;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;


public class BrowsrWindow extends CanvasWindow {

	/*
	 * Constructor of window
	 */
	public BrowsrWindow(String title) {
		super(title);
		
	}
	
	String text = "Test";
	Font font = new Font(Font.DIALOG, Font.PLAIN, 40);
	FontMetrics metrics;
	int textWidth;
	
	@Override
	protected void handleShown() {
		metrics = getFontMetrics(font);
		textWidth = metrics.stringWidth(text);
		repaint();
	}
	
	@Override
	protected void paint(Graphics g) {
		g.setFont(font);
		g.drawString(text, (getWidth() - textWidth) / 2, (getHeight() - metrics.getHeight()) / 2 + metrics.getLeading() + metrics.getAscent());
	}
	
	@Override
	protected void handleResize() {
		repaint();
	}
	
	@Override
	protected void handleMouseEvent(int id, int x, int y, int clickCount) {
	}
	
	@Override
	protected void handleKeyEvent(int id, int keyCode, char keyChar) {
	}
	
	public static void main(String[] args) {
	    java.awt.EventQueue.invokeLater(() -> {             
	    	new BrowsrWindow("My Canvas Window").show(); 
	    });                                                 
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
