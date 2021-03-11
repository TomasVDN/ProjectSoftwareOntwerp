package test;

import java.awt.Graphics;

import canvaswindow.CanvasWindow;
import canvaswindow.MyCanvasWindow;

public class TestAwt extends CanvasWindow{

	protected TestAwt(String title) {
		super("testAwt");
	}

	@Override
	protected void paint(Graphics g) {
		//Paint grid
		g.drawLine(0, 0, 0, 600);
		g.drawLine(100, 0, 100, 600);
		g.drawLine(200, 0, 200, 600);
		g.drawLine(300, 0, 300, 600);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(500, 0, 500, 600);
		g.drawLine(600, 0, 600, 600);
		
		g.drawLine(0, 0, 600, 0);
		g.drawLine(0, 100, 600, 100);
		g.drawLine(0, 200, 600, 200);
		g.drawLine(0, 300, 600, 300);
		g.drawLine(0, 400, 600, 400);
		g.drawLine(0, 500, 600, 500);
		g.drawLine(0, 600, 600, 600);
		
		//drawString and translate
		g.drawString("Test 1", 0, 0);
		g.translate(100, 100);
		g.drawString("Test 2", 0, 0);
		g.translate(100, 100);
		g.drawString("Test 3", 0, 0);
		g.translate(100, 100);
		g.drawString("Test 4", 0, 0);
		g.translate(100, 100);
		g.drawString("Test 5", 0, 0);
		g.translate(100, 100);
		g.drawString("Test 6", 0, 0);
		g.translate(100, 100);
		g.drawString("Test 7", 0, 0);
	}
	
	public static void main(String[] args) {
	      java.awt.EventQueue.invokeLater(() -> {
	    	  TestAwt window = new TestAwt("testAwt");
	         window.show();
	      });
}
}
