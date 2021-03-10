package canvaswindow;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

/**
 * Singleton type class that can be called by all GUIElements to get the fontMetrics.
 */
public final class FontMetricGetter {
	
	
	private static final FontMetricGetter INSTANCE = new FontMetricGetter();

	private FontMetricGetter() {}

	public static FontMetricGetter getInstance() {
	        return INSTANCE;
	}
	
	public FontMetrics getFontMetric(Font font){
		return Toolkit.getDefaultToolkit().getFontMetrics(font);
	}
}