package utils;

import java.awt.Font;
import java.awt.FontMetrics;

import canvaswindow.MyCanvasWindow;

public class FontMetricsHandle {

	private MyCanvasWindow window;
	
	public FontMetricsHandle(MyCanvasWindow window) {
		this.window = window;
	}
	
	public FontMetrics getFontMetrics(Font font) {
		return window.getFontMetrics(font);
	}

}
