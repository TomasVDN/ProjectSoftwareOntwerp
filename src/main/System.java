package main;

import canvaswindow.MyCanvasWindow;

public class System {

	public static void main(String[] args) {
	      java.awt.EventQueue.invokeLater(() -> {
	         MyCanvasWindow window = new MyCanvasWindow("BrowsrController");
	         window.show();
	      });
	}

}
