package main;
import java.awt.Color;

import canvaswindow.MyCanvasWindow;
import htmlElement.Box;
import htmlElement.GUIElement;
import htmlElement.TextBox;

public class Main {
	public static void main(String[] args) {
	      java.awt.EventQueue.invokeLater(() -> {
	         MyCanvasWindow window = new MyCanvasWindow("Browsr");
	         //GUIElement box = new Box(20, 200, 100, 100,Color.red);
	         TextBox textBox = new TextBox(20, 200, 100, 100);
	         window.addTextBox(textBox);
	         window.addElement(textBox);
	         window.show();
	      });
	}
}
