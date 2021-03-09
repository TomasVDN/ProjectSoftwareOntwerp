package canvaswindow;

import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Singleton type class that can be called by all GUIElements to get the fontMetrics.
 */
public final class FontMetricGetter {
	
	
	private static final FontMetricGetter INSTANCE = new FontMetricGetter();

	private FontMetricGetter() {}

	public static FontMetricGetter getInstance() {
	        return INSTANCE;
	    }
	   
	private MyCanvasWindow window;
	
	public FontMetrics getFontMetric(Font font){
		return this.getwindow().getFontMetrics(font);
	}

	private MyCanvasWindow getwindow() {
		return window;
	}

	//should only be called by windowManager once!!!
	public void setWindow(MyCanvasWindow window) {
		if (this.window != null) {
			return;
		}
		this.window = window;
	}
}