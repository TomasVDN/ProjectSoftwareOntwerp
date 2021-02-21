package main;
import java.awt.Color;

import canvaswindow.MyCanvasWindow;
import htmlElement.Box;
import htmlElement.GUIElement;

public class Main {
	public static void main(String[] args) {
	      java.awt.EventQueue.invokeLater(() -> {
	         MyCanvasWindow window = new MyCanvasWindow("Browsr");
	         GUIElement box = new Box(20, 200, 100, 100,Color.red);
	         window.addElement(box);
	         window.show();
	      });
	}
}
