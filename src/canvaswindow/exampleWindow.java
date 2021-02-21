	package canvaswindow;

	import java.awt.Font;
	import java.awt.FontMetrics;
	import java.awt.Graphics;

	/**
	 * This is an example of how you can use class CanvasWindow.
	 */


public class exampleWindow extends CanvasWindow {


		protected exampleWindow() {
			super("My Canvas Window");
			// TODO Auto-generated constructor stub
		}

		String text = "Example Text";
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

		public static void main(String[] args) {
		    java.awt.EventQueue.invokeLater(() -> {             
		        new exampleWindow().show();  
		    });                                                 
		}

	
}
