package canvaswindow;

public class Main {

	public static void main(String[] args) {
		      java.awt.EventQueue.invokeLater(() -> {
		         MyCanvasWindow window = new MyCanvasWindow("Browsr");
		         window.show();
		      });
	}
	
}
