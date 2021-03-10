package canvaswindow;

public class Main {

	public static void main(String[] args) {
		      java.awt.EventQueue.invokeLater(() -> {
		         MyCanvasWindow window = new MyCanvasWindow("Browsr");
		         //window.show();
		         //window.recordSession("recordings/recording");
		         //MyCanvasWindow.replayRecording("recordings/recording", window);
		         //window.show();
		      });
	}
	
}
